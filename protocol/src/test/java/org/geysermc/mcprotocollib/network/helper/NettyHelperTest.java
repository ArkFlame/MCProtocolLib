package org.geysermc.mcprotocollib.network.helper;

import io.netty.channel.ChannelHandler;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.proxy.ProxyHandler;
import org.geysermc.mcprotocollib.network.NetworkConstants;
import org.geysermc.mcprotocollib.network.ProxyInfo;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NettyHelperTest {
    @Test
    public void testProxyAddressKeepsUnresolvedHost() {
        SocketAddress original = InetSocketAddress.createUnresolved("sub.example.com", 25565);
        SocketAddress result = NettyHelper.proxyAddress(original);

        assertTrue(result instanceof InetSocketAddress);
        InetSocketAddress addressed = (InetSocketAddress) result;
        assertTrue(addressed.isUnresolved(), "The proxy must resolve the destination host");
        assertEquals("sub.example.com", addressed.getHostString());
        assertEquals(25565, addressed.getPort());
    }

    @Test
    public void testProxyAddressConvertsInternationalizedHost() {
        SocketAddress original = InetSocketAddress.createUnresolved("bücher.example", 25565);
        SocketAddress result = NettyHelper.proxyAddress(original);

        assertTrue(result instanceof InetSocketAddress);
        assertEquals("xn--bcher-kva.example", ((InetSocketAddress) result).getHostString());
    }

    @Test
    public void testProxyAddressPreservesIpv6Host() {
        SocketAddress original = InetSocketAddress.createUnresolved("[2001:db8::1]", 25566);
        SocketAddress result = NettyHelper.proxyAddress(original);

        assertTrue(result instanceof InetSocketAddress);
        InetSocketAddress addressed = (InetSocketAddress) result;
        assertEquals("2001:db8::1", addressed.getHostString());
        assertEquals(25566, addressed.getPort());
    }

    @Test
    public void testProxyHandshakeRoutePreservesDestinationHostForSocks4Socks5AndHttp() throws Exception {
        Map<ProxyInfo.Type, String> protocols = new LinkedHashMap<>();
        protocols.put(ProxyInfo.Type.SOCKS4, "socks4");
        protocols.put(ProxyInfo.Type.SOCKS5, "socks5");
        protocols.put(ProxyInfo.Type.HTTP, "http");

        for (Map.Entry<ProxyInfo.Type, String> entry : protocols.entrySet()) {
            ProxyInfo proxy = new ProxyInfo(entry.getKey(), "127.0.0.1", 1080);

            // Same route preparation the client performs when a proxy is configured: the
            // destination stays unresolved so the proxy resolves the host, not local DNS/SRV.
            SocketAddress prepared = NettyHelper.proxyAddress(
                InetSocketAddress.createUnresolved("proxy-target.example", 25565));
            assertTrue(((InetSocketAddress) prepared).isUnresolved());

            // Netty's proxy handler builds its handshake message from the channel's remote
            // address, which the bootstrap sets to the prepared address above.
            EmbeddedChannel channel = new EmbeddedChannel() {
                @Override
                public SocketAddress remoteAddress() {
                    return prepared;
                }
            };
            // EmbeddedChannel is active on construction; close it so the handler does not
            // start its handshake before we hand it the prepared destination address.
            channel.close();
            try {
                NettyHelper.addProxy(proxy, channel.pipeline());
                ChannelHandler handler = channel.pipeline().get(NetworkConstants.PROXY_NAME);
                assertNotNull(handler, "No proxy handshake handler for " + entry.getKey());
                assertTrue(handler instanceof ProxyHandler);
                ProxyHandler proxyHandler = (ProxyHandler) handler;
                assertEquals(entry.getValue(), proxyHandler.protocol());

                proxyHandler.connect(channel.pipeline().context(handler), prepared, null, channel.newPromise());
                SocketAddress destination = proxyHandler.destinationAddress();
                assertEquals(prepared, destination);
                assertTrue(((InetSocketAddress) destination).isUnresolved());
                assertEquals("proxy-target.example", ((InetSocketAddress) destination).getHostString());
                assertEquals(25565, ((InetSocketAddress) destination).getPort());
            } finally {
                channel.finishAndReleaseAll();
            }
        }
    }
}
