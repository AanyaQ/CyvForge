package net.cyvforge.hud.nonlabels;

import net.cyvforge.config.CyvClientColorHelper;
import net.cyvforge.event.events.ParkourTickListener;
import net.cyvforge.hud.structure.DraggableHUDElement;
import net.cyvforge.hud.structure.ScreenPosition;
import net.cyvforge.util.GuiUtils;
import net.minecraft.client.settings.KeyBinding;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class KeystrokesHUD extends DraggableHUDElement {
    public KeystrokesHUD() {
    }

    public List<Key> keys;
    public int size = 66;

    @Override
    public ScreenPosition getDefaultPosition() {
        return new ScreenPosition(250, 0);
    }

    @Override
    public String getName() {
        return "keystrokes";
    }

    @Override
    public String getDisplayName() {
        return "Keystrokes";
    }

    @Override
    public int getWidth() {
        return size;
    }

    @Override
    public int getHeight() {
        return (int)(getWidth()*1.05);
    }

    @Override
    public void render(ScreenPosition pos) {
        if (this.keys == null || this.keys.isEmpty()) refreshKeys();

        for (Key key : keys) {
            String displayString = key.name;
            float scale = 1;

            GuiUtils.drawRoundedRect(pos.getAbsoluteX() + key.x, pos.getAbsoluteY() + key.y,
                    pos.getAbsoluteX() + key.x + key.width, pos.getAbsoluteY() + key.y + key.height, 2,
                    key.isDown() ? new Color(255, 255, 255, 102).getRGB() : new Color(20, 20, 20, 102).getRGB());

            GuiUtils.drawCenteredString(displayString, pos.getAbsoluteX() + key.x + key.width/2,
                    pos.getAbsoluteY() + key.y + key.height/2 - (int)(mc.fontRendererObj.FONT_HEIGHT * (size*scale/66.0F)/2) + 1,
                    key.isDown() ? (int) CyvClientColorHelper.color1.getDrawColor() : Color.white.getRGB(), true);
        }
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        if (!this.isVisible) return;

        this.render(pos);
    }

    private static class Key {
        public final String name;
        public final KeyBinding keyBind;
        public final int x, y, width, height;
        int index = -1;

        public Key(String name, KeyBinding keyBind, int x, int y, int width, int height) {
            this(name, keyBind, x, y, width, height, -1);
        }

        public Key(String name, KeyBinding keyBind, int x, int y, int width, int height, int index) {
            this.name = name;
            this.keyBind = keyBind;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.index = index;
        }

        public boolean isDown() {
            if (this.index == -1) return keyBind.isPressed();
            else {
                if (this.index == 5 || this.index == 6) {
                    return ParkourTickListener.lastTick.keys[this.index];
                } else return ParkourTickListener.lastTick.keys[this.index];
            }
        }

    }

    void refreshKeys() {
        int sq = this.getWidth()/3; // third
        int srl = this.getWidth()/2;
        int srh = this.getWidth()/5; // fifth
        int spc = this.getWidth()/5; //spacebar

        this.keys = new ArrayList<Key>();
        keys.add(new Key("W", mc.gameSettings.keyBindForward, sq + 1, 1, sq - 2, sq - 2, 0));
        keys.add(new Key("A", mc.gameSettings.keyBindLeft, 1, sq + 1, sq - 2, sq - 2, 1));
        keys.add(new Key("S", mc.gameSettings.keyBindBack, sq + 1, sq + 1, sq - 2, sq - 2, 2));
        keys.add(new Key("D", mc.gameSettings.keyBindRight, (2 * sq) + 1, sq + 1, sq - 2, sq - 2, 3));
        keys.add(new Key("-----", mc.gameSettings.keyBindJump, 1, (2 * sq) + srh + 1, this.getWidth() - 2, spc - 2, 4));

        keys.add(new Key("Spr", mc.gameSettings.keyBindSprint, 1, (2 * sq) + 1, srl - 2, srh - 2, 5));
        keys.add(new Key("Snk", mc.gameSettings.keyBindSneak, srl + 1, (2 * sq) + 1, srl - 2, srh - 2, 6));
    }

}
