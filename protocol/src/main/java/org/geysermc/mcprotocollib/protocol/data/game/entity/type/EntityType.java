package org.geysermc.mcprotocollib.protocol.data.game.entity.type;

/**
 * Type of an entity. Implementations may be built-in ({@link BuiltinEntityType})
 * or custom ids supplied by mods ({@link Custom}).
 */
public interface EntityType {

    /** @deprecated use {@link BuiltinEntityType} constants directly. */
    EntityType ACACIA_BOAT = BuiltinEntityType.ACACIA_BOAT;
    EntityType ACACIA_CHEST_BOAT = BuiltinEntityType.ACACIA_CHEST_BOAT;
    EntityType ALLAY = BuiltinEntityType.ALLAY;
    EntityType AREA_EFFECT_CLOUD = BuiltinEntityType.AREA_EFFECT_CLOUD;
    EntityType ARMADILLO = BuiltinEntityType.ARMADILLO;
    EntityType ARMOR_STAND = BuiltinEntityType.ARMOR_STAND;
    EntityType ARROW = BuiltinEntityType.ARROW;
    EntityType AXOLOTL = BuiltinEntityType.AXOLOTL;
    EntityType BAMBOO_CHEST_RAFT = BuiltinEntityType.BAMBOO_CHEST_RAFT;
    EntityType BAMBOO_RAFT = BuiltinEntityType.BAMBOO_RAFT;
    EntityType BAT = BuiltinEntityType.BAT;
    EntityType BEE = BuiltinEntityType.BEE;
    EntityType BIRCH_BOAT = BuiltinEntityType.BIRCH_BOAT;
    EntityType BIRCH_CHEST_BOAT = BuiltinEntityType.BIRCH_CHEST_BOAT;
    EntityType BLAZE = BuiltinEntityType.BLAZE;
    EntityType BLOCK_DISPLAY = BuiltinEntityType.BLOCK_DISPLAY;
    EntityType BOGGED = BuiltinEntityType.BOGGED;
    EntityType BREEZE = BuiltinEntityType.BREEZE;
    EntityType BREEZE_WIND_CHARGE = BuiltinEntityType.BREEZE_WIND_CHARGE;
    EntityType CAMEL = BuiltinEntityType.CAMEL;
    EntityType CAMEL_HUSK = BuiltinEntityType.CAMEL_HUSK;
    EntityType CAT = BuiltinEntityType.CAT;
    EntityType CAVE_SPIDER = BuiltinEntityType.CAVE_SPIDER;
    EntityType CHERRY_BOAT = BuiltinEntityType.CHERRY_BOAT;
    EntityType CHERRY_CHEST_BOAT = BuiltinEntityType.CHERRY_CHEST_BOAT;
    EntityType CHEST_MINECART = BuiltinEntityType.CHEST_MINECART;
    EntityType CHICKEN = BuiltinEntityType.CHICKEN;
    EntityType COD = BuiltinEntityType.COD;
    EntityType COPPER_GOLEM = BuiltinEntityType.COPPER_GOLEM;
    EntityType COMMAND_BLOCK_MINECART = BuiltinEntityType.COMMAND_BLOCK_MINECART;
    EntityType COW = BuiltinEntityType.COW;
    EntityType CREAKING = BuiltinEntityType.CREAKING;
    EntityType CREEPER = BuiltinEntityType.CREEPER;
    EntityType CUSHION = BuiltinEntityType.CUSHION;
    EntityType DARK_OAK_BOAT = BuiltinEntityType.DARK_OAK_BOAT;
    EntityType DARK_OAK_CHEST_BOAT = BuiltinEntityType.DARK_OAK_CHEST_BOAT;
    EntityType DOLPHIN = BuiltinEntityType.DOLPHIN;
    EntityType DONKEY = BuiltinEntityType.DONKEY;
    EntityType DRAGON_FIREBALL = BuiltinEntityType.DRAGON_FIREBALL;
    EntityType DROWNED = BuiltinEntityType.DROWNED;
    EntityType EGG = BuiltinEntityType.EGG;
    EntityType ELDER_GUARDIAN = BuiltinEntityType.ELDER_GUARDIAN;
    EntityType ENDERMAN = BuiltinEntityType.ENDERMAN;
    EntityType ENDERMITE = BuiltinEntityType.ENDERMITE;
    EntityType ENDER_DRAGON = BuiltinEntityType.ENDER_DRAGON;
    EntityType ENDER_PEARL = BuiltinEntityType.ENDER_PEARL;
    EntityType END_CRYSTAL = BuiltinEntityType.END_CRYSTAL;
    EntityType EVOKER = BuiltinEntityType.EVOKER;
    EntityType EVOKER_FANGS = BuiltinEntityType.EVOKER_FANGS;
    EntityType EXPERIENCE_BOTTLE = BuiltinEntityType.EXPERIENCE_BOTTLE;
    EntityType EXPERIENCE_ORB = BuiltinEntityType.EXPERIENCE_ORB;
    EntityType EYE_OF_ENDER = BuiltinEntityType.EYE_OF_ENDER;
    EntityType FALLING_BLOCK = BuiltinEntityType.FALLING_BLOCK;
    EntityType FIREBALL = BuiltinEntityType.FIREBALL;
    EntityType FIREWORK_ROCKET = BuiltinEntityType.FIREWORK_ROCKET;
    EntityType FOX = BuiltinEntityType.FOX;
    EntityType FROG = BuiltinEntityType.FROG;
    EntityType FURNACE_MINECART = BuiltinEntityType.FURNACE_MINECART;
    EntityType GHAST = BuiltinEntityType.GHAST;
    EntityType HAPPY_GHAST = BuiltinEntityType.HAPPY_GHAST;
    EntityType GIANT = BuiltinEntityType.GIANT;
    EntityType GLOW_ITEM_FRAME = BuiltinEntityType.GLOW_ITEM_FRAME;
    EntityType GLOW_SQUID = BuiltinEntityType.GLOW_SQUID;
    EntityType GOAT = BuiltinEntityType.GOAT;
    EntityType GUARDIAN = BuiltinEntityType.GUARDIAN;
    EntityType HOGLIN = BuiltinEntityType.HOGLIN;
    EntityType HOPPER_MINECART = BuiltinEntityType.HOPPER_MINECART;
    EntityType HORSE = BuiltinEntityType.HORSE;
    EntityType HUSK = BuiltinEntityType.HUSK;
    EntityType ILLUSIONER = BuiltinEntityType.ILLUSIONER;
    EntityType INTERACTION = BuiltinEntityType.INTERACTION;
    EntityType IRON_GOLEM = BuiltinEntityType.IRON_GOLEM;
    EntityType ITEM = BuiltinEntityType.ITEM;
    EntityType ITEM_DISPLAY = BuiltinEntityType.ITEM_DISPLAY;
    EntityType ITEM_FRAME = BuiltinEntityType.ITEM_FRAME;
    EntityType JUNGLE_BOAT = BuiltinEntityType.JUNGLE_BOAT;
    EntityType JUNGLE_CHEST_BOAT = BuiltinEntityType.JUNGLE_CHEST_BOAT;
    EntityType LEASH_KNOT = BuiltinEntityType.LEASH_KNOT;
    EntityType LIGHTNING_BOLT = BuiltinEntityType.LIGHTNING_BOLT;
    EntityType LLAMA = BuiltinEntityType.LLAMA;
    EntityType LLAMA_SPIT = BuiltinEntityType.LLAMA_SPIT;
    EntityType MAGMA_CUBE = BuiltinEntityType.MAGMA_CUBE;
    EntityType MANGROVE_BOAT = BuiltinEntityType.MANGROVE_BOAT;
    EntityType MANGROVE_CHEST_BOAT = BuiltinEntityType.MANGROVE_CHEST_BOAT;
    EntityType MANNEQUIN = BuiltinEntityType.MANNEQUIN;
    EntityType MARKER = BuiltinEntityType.MARKER;
    EntityType MINECART = BuiltinEntityType.MINECART;
    EntityType MOOSHROOM = BuiltinEntityType.MOOSHROOM;
    EntityType MULE = BuiltinEntityType.MULE;
    EntityType NAUTILUS = BuiltinEntityType.NAUTILUS;
    EntityType OAK_BOAT = BuiltinEntityType.OAK_BOAT;
    EntityType OAK_CHEST_BOAT = BuiltinEntityType.OAK_CHEST_BOAT;
    EntityType OCELOT = BuiltinEntityType.OCELOT;
    EntityType OMINOUS_ITEM_SPAWNER = BuiltinEntityType.OMINOUS_ITEM_SPAWNER;
    EntityType PAINTING = BuiltinEntityType.PAINTING;
    EntityType PALE_OAK_BOAT = BuiltinEntityType.PALE_OAK_BOAT;
    EntityType PALE_OAK_CHEST_BOAT = BuiltinEntityType.PALE_OAK_CHEST_BOAT;
    EntityType PANDA = BuiltinEntityType.PANDA;
    EntityType PARCHED = BuiltinEntityType.PARCHED;
    EntityType PARROT = BuiltinEntityType.PARROT;
    EntityType PHANTOM = BuiltinEntityType.PHANTOM;
    EntityType PIG = BuiltinEntityType.PIG;
    EntityType PIGLIN = BuiltinEntityType.PIGLIN;
    EntityType PIGLIN_BRUTE = BuiltinEntityType.PIGLIN_BRUTE;
    EntityType PILLAGER = BuiltinEntityType.PILLAGER;
    EntityType POLAR_BEAR = BuiltinEntityType.POLAR_BEAR;
    EntityType POPLAR_BOAT = BuiltinEntityType.POPLAR_BOAT;
    EntityType POPLAR_CHEST_BOAT = BuiltinEntityType.POPLAR_CHEST_BOAT;
    EntityType SPLASH_POTION = BuiltinEntityType.SPLASH_POTION;
    EntityType LINGERING_POTION = BuiltinEntityType.LINGERING_POTION;
    EntityType PUFFERFISH = BuiltinEntityType.PUFFERFISH;
    EntityType RABBIT = BuiltinEntityType.RABBIT;
    EntityType RAVAGER = BuiltinEntityType.RAVAGER;
    EntityType SALMON = BuiltinEntityType.SALMON;
    EntityType SHEEP = BuiltinEntityType.SHEEP;
    EntityType SHULKER = BuiltinEntityType.SHULKER;
    EntityType SHULKER_BULLET = BuiltinEntityType.SHULKER_BULLET;
    EntityType SILVERFISH = BuiltinEntityType.SILVERFISH;
    EntityType SKELETON = BuiltinEntityType.SKELETON;
    EntityType SKELETON_HORSE = BuiltinEntityType.SKELETON_HORSE;
    EntityType SLIME = BuiltinEntityType.SLIME;
    EntityType SMALL_FIREBALL = BuiltinEntityType.SMALL_FIREBALL;
    EntityType SNIFFER = BuiltinEntityType.SNIFFER;
    EntityType SNOWBALL = BuiltinEntityType.SNOWBALL;
    EntityType SNOW_GOLEM = BuiltinEntityType.SNOW_GOLEM;
    EntityType SPAWNER_MINECART = BuiltinEntityType.SPAWNER_MINECART;
    EntityType SPECTRAL_ARROW = BuiltinEntityType.SPECTRAL_ARROW;
    EntityType SPIDER = BuiltinEntityType.SPIDER;
    EntityType SPRUCE_BOAT = BuiltinEntityType.SPRUCE_BOAT;
    EntityType SPRUCE_CHEST_BOAT = BuiltinEntityType.SPRUCE_CHEST_BOAT;
    EntityType SQUID = BuiltinEntityType.SQUID;
    EntityType STRAY = BuiltinEntityType.STRAY;
    EntityType STRIDER = BuiltinEntityType.STRIDER;
    EntityType SULFUR_CUBE = BuiltinEntityType.SULFUR_CUBE;
    EntityType TADPOLE = BuiltinEntityType.TADPOLE;
    EntityType TEXT_DISPLAY = BuiltinEntityType.TEXT_DISPLAY;
    EntityType TNT = BuiltinEntityType.TNT;
    EntityType TNT_MINECART = BuiltinEntityType.TNT_MINECART;
    EntityType TRADER_LLAMA = BuiltinEntityType.TRADER_LLAMA;
    EntityType TRIDENT = BuiltinEntityType.TRIDENT;
    EntityType TROPICAL_FISH = BuiltinEntityType.TROPICAL_FISH;
    EntityType TURTLE = BuiltinEntityType.TURTLE;
    EntityType VEX = BuiltinEntityType.VEX;
    EntityType VILLAGER = BuiltinEntityType.VILLAGER;
    EntityType VINDICATOR = BuiltinEntityType.VINDICATOR;
    EntityType WANDERING_TRADER = BuiltinEntityType.WANDERING_TRADER;
    EntityType WARDEN = BuiltinEntityType.WARDEN;
    EntityType WIND_CHARGE = BuiltinEntityType.WIND_CHARGE;
    EntityType WITCH = BuiltinEntityType.WITCH;
    EntityType WITHER = BuiltinEntityType.WITHER;
    EntityType WITHER_SKELETON = BuiltinEntityType.WITHER_SKELETON;
    EntityType WITHER_SKULL = BuiltinEntityType.WITHER_SKULL;
    EntityType WOLF = BuiltinEntityType.WOLF;
    EntityType ZOGLIN = BuiltinEntityType.ZOGLIN;
    EntityType ZOMBIE = BuiltinEntityType.ZOMBIE;
    EntityType ZOMBIE_HORSE = BuiltinEntityType.ZOMBIE_HORSE;
    EntityType ZOMBIE_NAUTILUS = BuiltinEntityType.ZOMBIE_NAUTILUS;
    EntityType ZOMBIE_VILLAGER = BuiltinEntityType.ZOMBIE_VILLAGER;
    EntityType ZOMBIFIED_PIGLIN = BuiltinEntityType.ZOMBIFIED_PIGLIN;
    EntityType PLAYER = BuiltinEntityType.PLAYER;
    EntityType FISHING_BOBBER = BuiltinEntityType.FISHING_BOBBER;

    int id();

    boolean isProjectile();

    /** Returns the id, retained for callers that previously used the enum ordinal. */
    default int ordinal() {
        return id();
    }

    /** Returns the built-in name, retained for callers that previously used the enum name. */
    default String name() {
        return toString();
    }

    /** Returns the built-in types, retained for callers that previously used the enum values method. */
    static EntityType[] values() {
        return BuiltinEntityType.VALUES;
    }

    /** Resolves a built-in type by name, retained for callers that previously used the enum valueOf method. */
    static EntityType valueOf(String name) {
        return BuiltinEntityType.valueOf(name);
    }

    static EntityType from(int id) {
        if (id < 0 || id >= BuiltinEntityType.VALUES.length) {
            return new Custom(id);
        }
        return BuiltinEntityType.VALUES[id];
    }

    default boolean is(EntityType type) {
        return id() == type.id();
    }

    record Custom(int id) implements EntityType {
        @Override
        public boolean isProjectile() {
            return false;
        }
    }
}
