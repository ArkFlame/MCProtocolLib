package org.geysermc.mcprotocollib.protocol.data.game.entity.type;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.geysermc.mcprotocollib.protocol.data.game.item.component.ItemTypes;
import org.geysermc.mcprotocollib.protocol.packet.PacketTest;
import org.geysermc.mcprotocollib.protocol.packet.ingame.clientbound.entity.ClientboundAddEntityPacket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomEntityTypeTest extends PacketTest {
    @BeforeEach
    public void setup() {
        // A modded entity id outside the built-in range must survive a packet
        // round-trip instead of throwing an array index error.
        this.setPackets(new ClientboundAddEntityPacket(1, new UUID(1, 2), EntityType.from(1000),
                0, 0, 0, 0, 0, 0));
    }

    @Test
    public void customEntityIdsArePreserved() {
        EntityType custom = EntityType.from(1000);
        assertEquals(1000, custom.id());
        assertEquals(1000, custom.ordinal());
        assertEquals(custom, EntityType.from(1000));
        assertTrue(custom.is(EntityType.from(1000)));
        assertFalse(custom.isProjectile());

        EntityType negative = EntityType.from(-1);
        assertEquals(-1, negative.id());

        ByteBuf buf = Unpooled.buffer();
        ItemTypes.writeEntityType(buf, custom);
        assertEquals(custom, ItemTypes.readEntityType(buf));
    }

    @Test
    public void customPaintingIdsArePreserved() {
        PaintingType custom = PaintingType.from(2000);
        assertEquals(2000, custom.id());
        assertEquals(2000, custom.ordinal());
        assertEquals(custom, PaintingType.from(2000));

        PaintingType negative = PaintingType.from(-1);
        assertEquals(-1, negative.id());
    }
}
