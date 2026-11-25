package org.geysermc.mcprotocollib.protocol.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.geysermc.mcprotocollib.protocol.data.game.chunk.BitStorage;
import org.geysermc.mcprotocollib.protocol.data.game.chunk.ChunkSection;
import org.geysermc.mcprotocollib.protocol.data.game.chunk.DataPalette;
import org.geysermc.mcprotocollib.protocol.data.game.chunk.palette.ListPalette;
import org.geysermc.mcprotocollib.protocol.data.game.chunk.palette.PaletteType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChunkPaletteRegressionTest {
    private static final int BLOCK_REGISTRY_SIZE = 1000;
    private static final int BIOME_REGISTRY_SIZE = 64;

    @Test
    void truncatedPaletteEntryCountIsRejected() {
        ByteBuf buf = Unpooled.buffer();
        buf.writeByte(4);
        MinecraftTypes.writeVarInt(buf, 2);
        MinecraftTypes.writeVarInt(buf, 1);
        assertThrows(IllegalArgumentException.class,
            () -> MinecraftTypes.readDataPalette(buf, PaletteType.BLOCK_STATE, BLOCK_REGISTRY_SIZE));
        buf.release();
    }

    @Test
    void truncatedPaletteDataArrayIsRejected() {
        ByteBuf buf = Unpooled.buffer();
        buf.writeByte(4);
        MinecraftTypes.writeVarInt(buf, 1);
        MinecraftTypes.writeVarInt(buf, 1);
        buf.writeLong(0L);
        assertThrows(IllegalArgumentException.class,
            () -> MinecraftTypes.readDataPalette(buf, PaletteType.BLOCK_STATE, BLOCK_REGISTRY_SIZE));
        buf.release();
    }

    @Test
    void writtenBiomePaletteAlwaysHasAnEntryAtIndexZero() {
        DataPalette emptyBiomePalette = DataPalette.create(
            new ListPalette(1), new BitStorage(1, 64), PaletteType.BIOME, BIOME_REGISTRY_SIZE);
        ChunkSection section = new ChunkSection(
            1, 0, DataPalette.createForBlockState(0, BLOCK_REGISTRY_SIZE), emptyBiomePalette);
        section.setBlock(0, 0, 0, 2);

        ByteBuf buf = Unpooled.buffer();
        MinecraftTypes.writeChunkSection(buf, section);
        ChunkSection decoded = MinecraftTypes.readChunkSection(buf, BLOCK_REGISTRY_SIZE, BIOME_REGISTRY_SIZE);

        assertTrue(decoded.getBiomeData().getPalette().size() >= 1);
        assertEquals(0, decoded.getBiomeData().get(0, 0, 0));
        assertEquals(2, decoded.getBlock(0, 0, 0));
        buf.release();
    }
}
