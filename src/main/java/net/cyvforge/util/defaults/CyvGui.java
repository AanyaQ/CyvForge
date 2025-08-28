package net.cyvforge.util.defaults;

import net.cyvforge.CyvForge;
import net.cyvforge.config.ColorTheme;
import net.cyvforge.util.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;

public class CyvGui extends GuiScreen {
    public String name;

    public CyvGui(String name) {
        super();
        mc = Minecraft.getMinecraft();
        this.name = name;
    }

    @Override //called upon GUI initialization or resizing
    public void initGui() {}

    @Override //called each frame, put the drawScreen things here.
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override //called every tick
    public void updateScreen() {
        super.updateScreen();
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
    }

    @Override //called when the mouse is scrolled
    public void mouseClickMove(int x, int y, int mouseButton, long time) {
        super.mouseClickMove(x, y, mouseButton, time);
    }

    @Override //called upon GUI closing
    public void onGuiClosed() {
        super.onGuiClosed();
    }

    protected class SubButton {
        private boolean enabled;
        private String text;
        private int x, y;
        private int sizeX = 150;
        private int sizeY = 15;

        public SubButton(String text, int x, int y) {
            this.text = text;
            this.x = x;
            this.y = y;
        }

        public void draw(int mouseX, int mouseY) {
            boolean mouseDown = (mouseX > x && mouseX < x + sizeX && mouseY > y && mouseY < y + sizeY);
            ColorTheme theme = CyvForge.theme;
            GuiUtils.drawRoundedRect(x, y, x+sizeX, y+sizeY, 5, enabled ? (mouseDown ? theme.main1 : theme.main2) : theme.secondary1);
            GuiUtils.drawCenteredString(this.text, x+sizeX/2, y+sizeY/2-fontRendererObj.FONT_HEIGHT/2, 0xFFFFFFFF, true);
        }

        public boolean clicked(int mouseX, int mouseY, int mouseButton) {
            if (!this.enabled) return false;
            if (!(mouseX > x && mouseX < x+sizeX && mouseY > y && mouseY < y+sizeY && mouseButton == 0)) return false;
            else return true;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public void setSizeX(int sizeX) {
            this.sizeX = sizeX;
        }

        public void setSizeY(int sizeY) {
            this.sizeY = sizeY;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public void setText(String text) {
            this.text = text;
        }

    }

}
