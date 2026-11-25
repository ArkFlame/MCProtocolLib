package org.geysermc.mcprotocollib.network.helper;

import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
}
