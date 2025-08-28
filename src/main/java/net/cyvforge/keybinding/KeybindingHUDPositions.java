package net.cyvforge.keybinding;

import net.cyvforge.event.events.GuiHandler;
import net.cyvforge.gui.GuiHUDPositions;
import net.cyvforge.util.defaults.CyvKeybinding;
import org.lwjgl.input.Keyboard;

public class KeybindingHUDPositions extends CyvKeybinding {
    public KeybindingHUDPositions() {
        super("key.openhudpositions.desc", Keyboard.KEY_P);
    }

    @Override
    public void onTickEnd(boolean isPressed) {
        if (isPressed) {
            GuiHandler.setScreen(new GuiHUDPositions(false));
        }
    }
}
