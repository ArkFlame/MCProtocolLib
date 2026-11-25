package org.geysermc.mcprotocollib.protocol.data;

import org.geysermc.mcprotocollib.protocol.data.game.inventory.FillStackAction;
import org.geysermc.mcprotocollib.protocol.data.game.inventory.MoveToHotbarAction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ContainerActionValuesTest {
    @Test
    public void testAllMoveToHotbarButtonsAreKnown() {
        for (int id = 0; id <= 40; id++) {
            MoveToHotbarAction action = MoveToHotbarAction.from(id);
            assertNotNull(action, "Missing move-to-hotbar action for button " + id);
            assertEquals(id, action.getId());
        }
    }

    @Test
    public void testAllFillStackButtonsAreKnown() {
        int[] buttons = {0, 1, 2, 4, 5, 6, 8, 9, 10};
        for (int id : buttons) {
            FillStackAction action = FillStackAction.from(id);
            assertNotNull(action, "Missing fill-stack action for button " + id);
            assertEquals(id, action.getId());
        }
    }
}
