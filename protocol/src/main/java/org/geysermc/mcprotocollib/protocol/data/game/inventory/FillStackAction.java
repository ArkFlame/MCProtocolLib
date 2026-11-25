package org.geysermc.mcprotocollib.protocol.data.game.inventory;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;

public enum FillStackAction implements ContainerAction {
    // Starting a drag (slot must be -999).
    FILL(0),
    START_RIGHT_DRAG(4),
    START_MIDDLE_DRAG(8),
    // Adding slots to a drag.
    ADD_LEFT_DRAG(1),
    ADD_RIGHT_DRAG(5),
    ADD_MIDDLE_DRAG(9),
    // Ending a drag (slot must be -999).
    END_LEFT_DRAG(2),
    END_RIGHT_DRAG(6),
    END_MIDDLE_DRAG(10);

    private final int id;

    FillStackAction(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return this.id;
    }

    private static final Int2ObjectMap<FillStackAction> VALUES = new Int2ObjectOpenHashMap<>();

    public static FillStackAction from(int id) {
        return VALUES.get(id);
    }

    static {
        for (FillStackAction action : values()) {
            VALUES.put(action.id, action);
        }
    }
}
