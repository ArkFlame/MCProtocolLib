package org.geysermc.mcprotocollib.protocol.data.game.inventory;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;

public enum MoveToHotbarAction implements ContainerAction {
    SLOT_1(0),
    SLOT_2(1),
    SLOT_3(2),
    SLOT_4(3),
    SLOT_5(4),
    SLOT_6(5),
    SLOT_7(6),
    SLOT_8(7),
    SLOT_9(8),
    // Buttons 9 to 39 swap with arbitrary inventory slots.
    SLOT_10(9),
    SLOT_11(10),
    SLOT_12(11),
    SLOT_13(12),
    SLOT_14(13),
    SLOT_15(14),
    SLOT_16(15),
    SLOT_17(16),
    SLOT_18(17),
    SLOT_19(18),
    SLOT_20(19),
    SLOT_21(20),
    SLOT_22(21),
    SLOT_23(22),
    SLOT_24(23),
    SLOT_25(24),
    SLOT_26(25),
    SLOT_27(26),
    SLOT_28(27),
    SLOT_29(28),
    SLOT_30(29),
    SLOT_31(30),
    SLOT_32(31),
    SLOT_33(32),
    SLOT_34(33),
    SLOT_35(34),
    SLOT_36(35),
    SLOT_37(36),
    SLOT_38(37),
    SLOT_39(38),
    SLOT_40(39),
    OFF_HAND(40);

    private final int id;

    MoveToHotbarAction(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    private static final Int2ObjectMap<MoveToHotbarAction> VALUES = new Int2ObjectOpenHashMap<>();

    public static MoveToHotbarAction from(int id) {
        return VALUES.get(id);
    }

    static {
        for (MoveToHotbarAction action : values()) {
            VALUES.putIfAbsent(action.id, action);
        }
    }
}
