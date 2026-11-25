package org.geysermc.mcprotocollib.protocol.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.geysermc.mcprotocollib.protocol.data.game.chunk.BitStorage;
import org.geysermc.mcprotocollib.protocol.data.game.chunk.ChunkSection;
import org.geysermc.mcprotocollib.protocol.data.game.chunk.DataPalette;
import org.geysermc.mcprotocollib.protocol.data.game.chunk.palette.ListPalette;
import org.geysermc.mcprotocollib.protocol.data.game.chunk.palette.MapPalette;
import org.geysermc.mcprotocollib.protocol.data.game.chunk.palette.PaletteType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

    @Test
    void mapPaletteRejectsOutOfBoundsLengths() {
        for (int length : new int[]{-1, 9, Integer.MAX_VALUE}) {
            ByteBuf buf = Unpooled.buffer();
            try {
                MinecraftTypes.writeVarInt(buf, length);
                buf.writeZero(2); // fewer readable bytes than the declared entry count
                assertThrows(IllegalArgumentException.class, () -> new MapPalette(6, buf));
            } finally {
                buf.release();
            }
        }
    }

    @Test
    void listPaletteRejectsOutOfBoundsLengths() {
        for (int length : new int[]{-1, 9, Integer.MAX_VALUE}) {
            ByteBuf buf = Unpooled.buffer();
            try {
                MinecraftTypes.writeVarInt(buf, length);
                buf.writeZero(2);
                assertThrows(IllegalArgumentException.class, () -> new ListPalette(4, buf));
            } finally {
                buf.release();
            }
        }
    }

    @Test
    void directPaletteDecodersReadDeclaredEntries() {
        ByteBuf mapBuf = Unpooled.buffer();
        try {
            MinecraftTypes.writeVarInt(mapBuf, 3);
            MinecraftTypes.writeVarInt(mapBuf, 7);
            MinecraftTypes.writeVarInt(mapBuf, 42);
            MinecraftTypes.writeVarInt(mapBuf, 100);
            MapPalette map = new MapPalette(6, mapBuf);
            assertEquals(3, map.size());
            assertEquals(7, map.idToState(0));
            assertEquals(100, map.idToState(2));
        } finally {
            mapBuf.release();
        }

        ByteBuf listBuf = Unpooled.buffer();
        try {
            MinecraftTypes.writeVarInt(listBuf, 2);
            MinecraftTypes.writeVarInt(listBuf, 11);
            MinecraftTypes.writeVarInt(listBuf, 12);
            ListPalette list = new ListPalette(4, listBuf);
            assertEquals(2, list.size());
            assertEquals(11, list.idToState(0));
            assertEquals(12, list.idToState(1));
        } finally {
            listBuf.release();
        }
    }

    @Test
    void mapPaletteChunkSectionRoundTripKeepsIndexZeroAndExactDataArray() {
        MapPalette palette = new MapPalette(6);
        palette.stateToId(0); // id 0 -> air, so the all-zero data array has a palette entry
        palette.stateToId(42);
        DataPalette blocks = DataPalette.create(
            palette, new BitStorage(6, PaletteType.BLOCK_STATE.getStorageSize()), PaletteType.BLOCK_STATE, BLOCK_REGISTRY_SIZE);
        blocks.set(0, 0, 0, 42);
        blocks.set(1, 0, 0, 0);
        ChunkSection section = new ChunkSection(1, 0, blocks, DataPalette.createForBiome(0, BIOME_REGISTRY_SIZE));

        ByteBuf buf = Unpooled.buffer();
        try {
            MinecraftTypes.writeChunkSection(buf, section);
            ChunkSection decoded = MinecraftTypes.readChunkSection(buf, BLOCK_REGISTRY_SIZE, BIOME_REGISTRY_SIZE);
            assertFalse(buf.isReadable(), "chunk section must not carry extra longs");
            assertTrue(decoded.getBlockData().getPalette().size() >= 1);
            assertEquals(0, decoded.getBlockData().getPalette().idToState(0));
            assertEquals(42, decoded.getBlock(0, 0, 0));
            assertEquals(0, decoded.getBlock(1, 0, 0));
            assertEquals(new BitStorage(6, PaletteType.BLOCK_STATE.getStorageSize()).getData().length,
                decoded.getBlockData().getStorage().getData().length);
        } finally {
            buf.release();
        }
    }

    @Test
    void listPaletteChunkSectionRoundTripKeepsIndexZeroAndExactDataArray() {
        ListPalette palette = new ListPalette(4);
        palette.stateToId(0);
        palette.stateToId(9);
        DataPalette blocks = DataPalette.create(
            palette, new BitStorage(4, PaletteType.BLOCK_STATE.getStorageSize()), PaletteType.BLOCK_STATE, BLOCK_REGISTRY_SIZE);
        blocks.set(0, 0, 0, 9);
        ChunkSection section = new ChunkSection(1, 0, blocks, DataPalette.createForBiome(0, BIOME_REGISTRY_SIZE));

        ByteBuf buf = Unpooled.buffer();
        try {
            MinecraftTypes.writeChunkSection(buf, section);
            ChunkSection decoded = MinecraftTypes.readChunkSection(buf, BLOCK_REGISTRY_SIZE, BIOME_REGISTRY_SIZE);
            assertFalse(buf.isReadable(), "chunk section must not carry extra longs");
            assertTrue(decoded.getBlockData().getPalette().size() >= 1);
            assertEquals(0, decoded.getBlockData().getPalette().idToState(0));
            assertEquals(9, decoded.getBlock(0, 0, 0));
            assertEquals(0, decoded.getBlock(1, 0, 0));
            assertEquals(new BitStorage(4, PaletteType.BLOCK_STATE.getStorageSize()).getData().length,
                decoded.getBlockData().getStorage().getData().length);
        } finally {
            buf.release();
        }
    }

    @Test
    void emptyBlockPaletteIsNormalizedToHaveAnEntryAtIndexZero() {
        MapPalette empty = new MapPalette(6); // no states at all
        DataPalette blocks = DataPalette.create(
            empty, new BitStorage(6, PaletteType.BLOCK_STATE.getStorageSize()), PaletteType.BLOCK_STATE, BLOCK_REGISTRY_SIZE);
        ChunkSection section = new ChunkSection(0, 0, blocks, DataPalette.createForBiome(0, BIOME_REGISTRY_SIZE));

        ByteBuf buf = Unpooled.buffer();
        try {
            MinecraftTypes.writeChunkSection(buf, section);
            ChunkSection decoded = MinecraftTypes.readChunkSection(buf, BLOCK_REGISTRY_SIZE, BIOME_REGISTRY_SIZE);
            assertFalse(buf.isReadable());
            assertEquals(1, decoded.getBlockData().getPalette().size());
            assertEquals(0, decoded.getBlockData().getPalette().idToState(0));
            assertEquals(0, decoded.getBlock(0, 0, 0));
            assertEquals(new BitStorage(6, PaletteType.BLOCK_STATE.getStorageSize()).getData().length,
                decoded.getBlockData().getStorage().getData().length);
        } finally {
            buf.release();
        }
    }

    @Test
    void blockPaletteResizeGrowsIntoMapPalette() {
        DataPalette blocks = DataPalette.createForBlockState(0, BLOCK_REGISTRY_SIZE);
        for (int state = 0; state <= 16; state++) { // 17 distinct states exceed the list palette capacity
            blocks.set(state & 15, state >> 4, 0, state);
        }

        assertTrue(blocks.getPalette() instanceof MapPalette);
        assertEquals(17, blocks.getPalette().size());
        for (int state = 0; state <= 16; state++) {
            assertEquals(state, blocks.get(state & 15, state >> 4, 0));
        }
    }
}
