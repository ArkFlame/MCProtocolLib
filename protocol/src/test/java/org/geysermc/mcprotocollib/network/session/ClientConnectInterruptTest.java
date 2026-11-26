package org.geysermc.mcprotocollib.network.session;

import org.geysermc.mcprotocollib.protocol.MinecraftProtocol;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** #925: a thread waiting in connect(true) must react to an interrupt. */
public class ClientConnectInterruptTest {
    @Test
    void waitingForTheConnectionCanBeInterrupted() throws Exception {
        ClientNetworkSession session = new ClientNetworkSession(
            InetSocketAddress.createUnresolved("example.invalid", 25565), new MinecraftProtocol(), (Executor) Runnable::run, null, null);
        CompletableFuture<Void> neverCompletes = new CompletableFuture<>();
        AtomicBoolean returned = new AtomicBoolean();
        AtomicBoolean flagKept = new AtomicBoolean();
        AtomicReference<Throwable> error = new AtomicReference<>();

        Thread waiter = new Thread(() -> {
            try {
                session.awaitConnect(neverCompletes);
                returned.set(true);
                flagKept.set(Thread.currentThread().isInterrupted());
            } catch (Throwable t) {
                error.set(t);
            }
        });
        waiter.start();
        waiter.interrupt();
        waiter.join(5000);

        assertFalse(waiter.isAlive(), "interrupt must end the wait");
        assertTrue(returned.get() && error.get() == null, String.valueOf(error.get()));
        assertTrue(flagKept.get(), "interrupt flag must be preserved");
        assertTrue(session.disconnected);
    }
}
