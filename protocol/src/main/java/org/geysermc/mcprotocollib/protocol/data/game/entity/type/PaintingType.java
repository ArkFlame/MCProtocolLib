package org.geysermc.mcprotocollib.protocol.data.game.entity.type;

/**
 * Type of a painting. Implementations may be built-in ({@link BuiltinPaintingType})
 * or custom ids supplied by mods ({@link Custom}).
 */
public interface PaintingType {

    PaintingType KEBAB = BuiltinPaintingType.KEBAB;
    PaintingType AZTEC = BuiltinPaintingType.AZTEC;
    PaintingType ALBAN = BuiltinPaintingType.ALBAN;
    PaintingType AZTEC2 = BuiltinPaintingType.AZTEC2;
    PaintingType BOMB = BuiltinPaintingType.BOMB;
    PaintingType PLANT = BuiltinPaintingType.PLANT;
    PaintingType WASTELAND = BuiltinPaintingType.WASTELAND;
    PaintingType POOL = BuiltinPaintingType.POOL;
    PaintingType COURBET = BuiltinPaintingType.COURBET;
    PaintingType SEA = BuiltinPaintingType.SEA;
    PaintingType SUNSET = BuiltinPaintingType.SUNSET;
    PaintingType CREEBET = BuiltinPaintingType.CREEBET;
    PaintingType WANDERER = BuiltinPaintingType.WANDERER;
    PaintingType GRAHAM = BuiltinPaintingType.GRAHAM;
    PaintingType MATCH = BuiltinPaintingType.MATCH;
    PaintingType BUST = BuiltinPaintingType.BUST;
    PaintingType STAGE = BuiltinPaintingType.STAGE;
    PaintingType VOID = BuiltinPaintingType.VOID;
    PaintingType SKULL_AND_ROSES = BuiltinPaintingType.SKULL_AND_ROSES;
    PaintingType WITHER = BuiltinPaintingType.WITHER;
    PaintingType FIGHTERS = BuiltinPaintingType.FIGHTERS;
    PaintingType POINTER = BuiltinPaintingType.POINTER;
    PaintingType PIGSCENE = BuiltinPaintingType.PIGSCENE;
    PaintingType BURNING_SKULL = BuiltinPaintingType.BURNING_SKULL;
    PaintingType SKELETON = BuiltinPaintingType.SKELETON;
    PaintingType EARTH = BuiltinPaintingType.EARTH;
    PaintingType WIND = BuiltinPaintingType.WIND;
    PaintingType WATER = BuiltinPaintingType.WATER;
    PaintingType FIRE = BuiltinPaintingType.FIRE;
    PaintingType DONKEY_KONG = BuiltinPaintingType.DONKEY_KONG;
    PaintingType BAROQUE = BuiltinPaintingType.BAROQUE;
    PaintingType HUMBLE = BuiltinPaintingType.HUMBLE;
    PaintingType MEDITATIVE = BuiltinPaintingType.MEDITATIVE;
    PaintingType PRAIRIE_RIDE = BuiltinPaintingType.PRAIRIE_RIDE;
    PaintingType UNPACKED = BuiltinPaintingType.UNPACKED;
    PaintingType BACKYARD = BuiltinPaintingType.BACKYARD;
    PaintingType BOUQUET = BuiltinPaintingType.BOUQUET;
    PaintingType CAVEBIRD = BuiltinPaintingType.CAVEBIRD;
    PaintingType CHANGING = BuiltinPaintingType.CHANGING;
    PaintingType COTAN = BuiltinPaintingType.COTAN;
    PaintingType ENDBOSS = BuiltinPaintingType.ENDBOSS;
    PaintingType FERN = BuiltinPaintingType.FERN;
    PaintingType FINDING = BuiltinPaintingType.FINDING;
    PaintingType LOWMIST = BuiltinPaintingType.LOWMIST;
    PaintingType ORB = BuiltinPaintingType.ORB;
    PaintingType OWLEMONS = BuiltinPaintingType.OWLEMONS;
    PaintingType PASSAGE = BuiltinPaintingType.PASSAGE;
    PaintingType POND = BuiltinPaintingType.POND;
    PaintingType SUNFLOWERS = BuiltinPaintingType.SUNFLOWERS;
    PaintingType TIDES = BuiltinPaintingType.TIDES;

    int id();

    /** Returns the id, retained for callers that previously used the enum ordinal. */
    default int ordinal() {
        return id();
    }

    /** Returns the built-in name, retained for callers that previously used the enum name. */
    default String name() {
        return toString();
    }

    /** Returns the built-in types, retained for callers that previously used the enum values method. */
    static PaintingType[] values() {
        return BuiltinPaintingType.VALUES;
    }

    /** Resolves a built-in type by name, retained for callers that previously used the enum valueOf method. */
    static PaintingType valueOf(String name) {
        return BuiltinPaintingType.valueOf(name);
    }

    static PaintingType from(int id) {
        if (id < 0 || id >= BuiltinPaintingType.values().length) {
            return new Custom(id);
        }
        return BuiltinPaintingType.VALUES[id];
    }

    record Custom(int id) implements PaintingType {
    }
}
