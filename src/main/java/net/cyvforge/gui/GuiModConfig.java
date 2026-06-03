package net.cyvforge.gui;

import net.cyvforge.CyvForge;
import net.cyvforge.config.ColorTheme;
import net.cyvforge.config.CyvClientColorHelper;
import net.cyvforge.config.CyvClientConfig;
import net.cyvforge.gui.config.ConfigPanel;
import net.cyvforge.gui.config.panels.*;
import net.cyvforge.util.defaults.CyvGui;
import net.cyvforge.util.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.ArrayList;

public class GuiModConfig extends CyvGui {
    public int sizeX = 350;
    public int sizeY = 175;
    ArrayList<ConfigPanel> panels = new ArrayList<ConfigPanel>();
    ConfigPanel selectedPanel;
    ScaledResolution sr;
    SubButton backButton;

    ColorTheme theme;
    float vScroll = 0;
    float scroll = 0;
    int maxScroll = 0;
    boolean scrollClicked = false;

    protected final boolean fromLabels;

    public GuiModConfig(boolean fromLabels) {
        super("Mod Config");
        mc = Minecraft.getMinecraft();
        sr = new ScaledResolution(mc);
        fontRendererObj = mc.fontRendererObj;
        this.fromLabels = fromLabels;

        this.backButton = new SubButton("Back", sr.getScaledWidth()/2-sizeX/2-4, sr.getScaledHeight()/2-sizeY/2-21);
        this.theme = CyvForge.theme;

        this.updatePanels();

        maxScroll = (int) Math.max(0, fontRendererObj.FONT_HEIGHT * 2 * Math.ceil(panels.size()) - (sizeY-20));
        if (scroll > maxScroll) scroll = maxScroll;
        if (scroll < 0) scroll = 0;
    }

    @Override
    public void initGui() {
    }

    @Override
    public void onResize(Minecraft mcIn, int w, int h) {
        mc.displayGuiScreen(null);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        //background
        this.drawDefaultBackground();
        this.theme = CyvForge.theme;

        //draw the menu background
        GuiUtils.drawRoundedRect(sr.getScaledWidth()/2-sizeX/2-4, sr.getScaledHeight()/2-sizeY/2-4,
                sr.getScaledWidth()/2+sizeX/2+14, sr.getScaledHeight()/2+sizeY/2+4, 10, theme.background1);

        //buttons
        this.backButton.draw(mouseX, mouseY);

        //begin scissoring (I am a very mature individual who does not have a dirty mind)
        int centerx = sr.getScaledWidth() * sr.getScaleFactor() / 2;
        int centery = sr.getScaledHeight() * sr.getScaleFactor() / 2;
        int scaleFactor = sr.getScaleFactor();
        GL11.glScissor(centerx - (sizeX*scaleFactor/2), centery - (sizeY*scaleFactor/2), sizeX*scaleFactor, sizeY*scaleFactor);
        GL11.glEnable(GL11.GL_SCISSOR_TEST);

        for (ConfigPanel p : this.panels) {
            p.draw(mouseX, mouseY + (int)scroll, (int)scroll);
        }

        GL11.glDisable(GL11.GL_SCISSOR_TEST);

        //draw scrollbar
        int scrollbarHeight = (int) ((sizeY - 8)/(0.01*maxScroll+1));
        if (scroll > maxScroll) scroll = maxScroll;
        if (scroll < 0) scroll = 0;

        int top = sr.getScaledHeight()/2-sizeY/2+4;
        int bottom = sr.getScaledHeight()/2+sizeY/2-4 - scrollbarHeight;
        int amount = (int) (top + (bottom - top) * ((float) scroll/maxScroll));

        if (maxScroll == 0) amount = top;

        //color
        int color = theme.border2;
        if (mouseX > sr.getScaledWidth()/2+sizeX/2+2 && mouseX < sr.getScaledWidth()/2+sizeX/2+8 &&
                mouseY > amount && mouseY < amount+scrollbarHeight) {
            color = theme.border1;
        }

        GuiUtils.drawRoundedRect(sr.getScaledWidth()/2+sizeX/2+2, amount,
                sr.getScaledWidth()/2+sizeX/2+8, amount+scrollbarHeight, 3, color);
    }

    @Override
    public void updateScreen() {
        if (this.selectedPanel != null) this.selectedPanel.update();

        //smooth scrolling
        this.scroll += this.vScroll;
        this.vScroll *= 0.75;

        if (scroll > maxScroll) scroll = maxScroll;
        if (scroll < 0) scroll = 0;
    }

    private void updatePanels() {
        this.panels.clear();
        this.scroll = 0;

        // globals
        panels.add(new ConfigPanelOptionSwitcher<String>(panels, "color1", "Color 1", CyvClientColorHelper.colorStrings, this) {
            public void onValueChange() {CyvClientColorHelper.setColor1(CyvClientConfig.getString("color1", "aqua"));}});
        panels.add(new ConfigPanelOptionSwitcher<String>(panels, "color2", "Color 2", CyvClientColorHelper.colorStrings, this){
            public void onValueChange() {CyvClientColorHelper.setColor2(CyvClientConfig.getString("color2", "aqua"));}});
        panels.add(new ConfigPanelOptionSwitcher<String>(panels, "theme", "Color Theme", ColorTheme.getThemes(), this) {
            public void onValueChange() {
                CyvForge.theme = ColorTheme.valueOf(CyvClientConfig.getString("theme", "CYVISPIRIA"));}
        });
        panels.add(new ConfigPanelToggle(panels, "whiteChat", "Color2 always white in chat", this));
        panels.add(new ConfigPanelIntegerSlider(panels, "df", "Decimal Precision", 1, 16, this) {
            public void onValueChange() {
                CyvForge.df.setMaximumFractionDigits(CyvClientConfig.getInt("df", 5));}});
        panels.add(new ConfigPanelToggle(panels, "trimZeroes", "Trim Zeroes", this) {
            public void onValueChange() {
                if (CyvClientConfig.getBoolean("trimZeroes", true)) CyvForge.df.setMinimumFractionDigits(0);
                else CyvForge.df.setMinimumFractionDigits(CyvClientConfig.getInt("df",5));
        }});
        panels.add(new ConfigPanelEmptySpace(panels, this));

        // mpk
        panels.add(new ConfigPanelToggle(panels, "showMilliseconds", "Show Millisecond Timings", this));
        panels.add(new ConfigPanelToggle(panels, "sendLbChatOffset", "Send Landing Offset", this));
        panels.add(new ConfigPanelToggle(panels, "sendMmChatOffset", "Send Momentum Offset", this));
        panels.add(new ConfigPanelToggle(panels, "highlightLanding", "Highlight Landing Blocks", this));
        panels.add(new ConfigPanelToggle(panels, "highlightLandingCond", "Highlight Landing Conditions", this));
        panels.add(new ConfigPanelToggle(panels, "momentumPbCancelling", "Momentum PB Cancelling", this));
        panels.add(new ConfigPanelEmptySpace(panels, this));

        // label specific
        panels.add(new ConfigPanelToggle(panels, "showFacingAxis", "Show Facing Axis", this));
        panels.add(new ConfigPanelToggle(panels, "frameBased", "Frame Based Facing", this));
        panels.add(new ConfigPanelToggle(panels, "WADdisplay", "Last Input WAD Display ", this));
        panels.add(new ConfigPanelToggle(panels, "simpleBlip", "Simplified Blip", this));
        panels.add(new ConfigPanelToggle(panels, "detectWobble", "Wobble Timing", this));
        panels.add(new ConfigPanelToggle(panels, "detectStrafejam", "Strafejam Timing", this));
        panels.add(new ConfigPanelToggle(panels, "strafejamJamOnly", "Strafejam only after Jam", this));
        panels.add(new ConfigPanelIntegerSlider(panels, "turnHUDAngleMin", "Turn HUD Angle Min", 1, 12, this));
        panels.add(new ConfigPanelIntegerSlider(panels, "turnHUDAngleMax", "Turn HUD Angle Max", 1, 12, this));

        // macro
        panels.add(new ConfigPanelEmptySpace(panels, this));
        panels.add(new ConfigPanelToggle(panels, "smoothMacro", "Smooth Macro", this));

        // inertia
        panels.add(new ConfigPanelToggle(panels, "inertiaEnabled", "Inertia Listener Enabled", this));
        panels.add(new ConfigPanelIntegerSlider(panels, "inertiaTick", "Air tick", 1, 12, this));
        panels.add(new ConfigPanelDecimalEntry(panels, "inertiaMin", "Min Speed", this));
        panels.add(new ConfigPanelDecimalEntry(panels, "inertiaMax", "Max Speed", this));
        panels.add(new ConfigPanelOptionSwitcher<>(panels, "inertiaAxis", "Inertia Axis", new Character[] {'x', 'z'}, this));
        panels.add(new ConfigPanelOptionSwitcher<>(panels, "inertiaGroundType", "Ground Type", new String[] {"normal", "ice", "slime"}, this));

        // position checker
        panels.add(new ConfigPanelEmptySpace(panels, this));
        panels.add(new ConfigPanelToggle(panels, "positionCheckerEnabled", "Position Checker Enabled", this));
        panels.add(new ConfigPanelIntegerSlider(panels, "positionCheckerTick", "Air tick", 1, 12, this));
        panels.add(new ConfigPanelDecimalEntry(panels, "positionCheckerMinX", "Min X", this));
        panels.add(new ConfigPanelDecimalEntry(panels, "positionCheckerMaxX", "Max X", this));
        panels.add(new ConfigPanelDecimalEntry(panels, "positionCheckerMinZ", "Min Z", this));
        panels.add(new ConfigPanelDecimalEntry(panels, "positionCheckerMaxZ", "Max Z", this));
        panels.add(new ConfigPanelToggle(panels, "positionCheckerZNeo", "Z Neo Mode", this));

        // checkpoints
        panels.add(new ConfigPanelEmptySpace(panels, this));
        panels.add(new ConfigPanelToggle(panels, "antiCP", "Anti-Checkpoint", this));
        panels.add(new ConfigPanelIntegerSlider(panels, "antiCPDelay", "Anti-CP Delay (s)", 1, 10, this));
        panels.add(new ConfigPanelToggle(panels, "singleplayerCheckpointsEnabled", "Custom Checkpoints Enabled", this));
        panels.add(new ConfigPanelIntegerSlider(panels, "generatorDyeColor", "Generator Dye Color", 0, 15, this));
        panels.add(new ConfigPanelIntegerSlider(panels, "generatorItemSlot", "Generator Hotbar Slot", 0, 8, this));

        maxScroll = (int) Math.max(0, fontRendererObj.FONT_HEIGHT * 2 * Math.ceil(panels.size()) - (sizeY-20));
        if (scroll > maxScroll) scroll = maxScroll;
        if (scroll < 0) scroll = 0;
    }

    @Override
    public void mouseClickMove(int x, int y, int mouseButton, long time) {
        if (this.scrollClicked) {
            int scrollbarHeight = (int) ((sizeY - 8)/(0.01*maxScroll+1));
            int top = sr.getScaledHeight()/2-sizeY/2+4;
            int bottom = sr.getScaledHeight()/2+sizeY/2-4 - scrollbarHeight;

            scroll = (int) ((float) (y - (sr.getScaledHeight()/2-this.sizeY/2) - scrollbarHeight/2) /(bottom - top) * maxScroll);

            if (scroll > maxScroll) scroll = maxScroll;
            if (scroll < 0) scroll = 0;
        }

        if (this.selectedPanel != null) {
            this.selectedPanel.mouseDragged(x, y);
        }


    }

    @Override
    public void handleMouseInput() {
        try {
            super.handleMouseInput();
        } catch (IOException e) {}

        int eventDWheel = Mouse.getDWheel();

        if ((!scrollClicked || !Mouse.isButtonDown(0)) && eventDWheel != 0) {
            vScroll -= eventDWheel * 0.03;
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        int scrollbarHeight = (int) ((sizeY - 8)/(0.01*maxScroll+1));
        int top = sr.getScaledHeight()/2-sizeY/2+4;
        int bottom = sr.getScaledHeight()/2+sizeY/2-4 - scrollbarHeight;
        int amount = (int) (top + (bottom - top) * ((float) scroll/maxScroll));

        if (mouseX > sr.getScaledWidth()/2+sizeX/2+2 && mouseX < sr.getScaledWidth()/2+sizeX/2+8 &&
                mouseY > amount && mouseY < amount+scrollbarHeight) {
            this.scrollClicked = true;
            return;
        } else {
            this.scrollClicked = false;
        }

        if (this.backButton.clicked(mouseX, mouseY, mouseButton)) {
            if (fromLabels) Minecraft.getMinecraft().displayGuiScreen(new GuiMPK());
            else Minecraft.getMinecraft().displayGuiScreen(null);
            return;
        }

        if (mouseX < sr.getScaledWidth()/2-sizeX/2-4 || mouseX > sr.getScaledWidth()/2+sizeX/2+14 ||
                mouseY < sr.getScaledHeight()/2-sizeY/2-4 || mouseY > sr.getScaledHeight()/2+sizeY/2+4) {
            this.selectedPanel = null;
            return;
        }

        for (ConfigPanel p : this.panels) {
            if (p.mouseInBounds(mouseX, mouseY+(int)scroll)) {
                if (this.selectedPanel != null) this.selectedPanel.unselect();

                p.mouseClicked(mouseX, mouseY+(int)scroll, mouseButton);
                this.selectedPanel = p;
                p.select();
                return;
            }
        }

        this.selectedPanel = null;
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == Keyboard.KEY_ESCAPE) { //exit the gui
            if (fromLabels) Minecraft.getMinecraft().displayGuiScreen(new GuiMPK());
            else Minecraft.getMinecraft().displayGuiScreen(null);
            return;
        }

        if (this.selectedPanel != null) this.selectedPanel.keyTyped(typedChar, keyCode);
    }

    @Override
    public void onGuiClosed() {
        for (ConfigPanel p : this.panels) p.save();
        this.updatePanels();

    }

    class SubButton {
        String text;
        int x, y;
        int sizeX = 80;
        int sizeY = 15;

        SubButton(String text, int x, int y) {
            this.text = text;
            this.x = x;
            this.y = y;
        }

        void draw(int mouseX, int mouseY) {
            boolean mouseDown = (mouseX > x && mouseX < x + sizeX && mouseY > y && mouseY < y + sizeY);
            GuiUtils.drawRoundedRect(x, y, x+sizeX, y+sizeY, 5, mouseDown ? theme.highlight : theme.background1);
            GuiUtils.drawCenteredString(this.text, x+sizeX/2, y+sizeY/2-fontRendererObj.FONT_HEIGHT/2, 0xFFFFFFFF, true);
        }

        boolean clicked(double mouseX, double mouseY, int mouseButton) {
            if (!(mouseX > x && mouseX < x+sizeX && mouseY > y && mouseY < y+sizeY && mouseButton == 0)) return false;
            else return true;
        }

    }
}
