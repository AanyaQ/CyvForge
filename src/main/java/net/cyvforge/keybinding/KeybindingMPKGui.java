package net.cyvforge.keybinding;

import net.cyvforge.event.events.GuiHandler;
import net.cyvforge.gui.GuiMPK;
import net.cyvforge.util.defaults.CyvKeybinding;
import org.lwjgl.input.Keyboard;

public class KeybindingMPKGui extends CyvKeybinding {
    public KeybindingMPKGui() {
        super("key.openmpkgui.desc", Keyboard.KEY_RSHIFT);
    }

    @Override
    public void onTickEnd(boolean isPressed) {
        if (isPressed) {
            GuiHandler.setScreen(new GuiMPK());
        }
    }
}
