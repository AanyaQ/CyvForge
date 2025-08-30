package net.cyvforge.gui.config.panels;

import net.cyvforge.CyvForge;
import net.cyvforge.config.CyvClientConfig;
import net.cyvforge.gui.GuiModConfig;
import net.cyvforge.gui.config.ConfigPanel;
import net.cyvforge.util.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

import java.util.ArrayList;

public class ConfigPanelToggleModes implements ConfigPanel {
    public boolean sliderValue;
    public String configOption;
    public String displayString;
    public final int index;
    public GuiModConfig screenIn;

    public final String s1, s2;

    private int xPosition;
    private int yPosition;
    private int sizeX;
    private int sizeY;

    public ConfigPanelToggleModes(ArrayList<ConfigPanel> array, String configOption, String displayString, String s1, String s2, GuiModConfig screenIn) {
        this.index = array.size();
        this.displayString = displayString;
        this.configOption = configOption;
        this.screenIn = screenIn;

        this.s1 = s1;
        this.s2 = s2;

        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        sizeX = screenIn.sizeX-20;
        sizeY = Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT*3/2;
        this.xPosition = sr.getScaledWidth()/2-screenIn.sizeX/2+10;
        this.yPosition = sr.getScaledHeight()/2-screenIn.sizeY/2+10 + (index * Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT * 2);
        this.sliderValue = CyvClientConfig.getBoolean(configOption, false);

    }

    @Override
    public void draw(int mouseX, int mouseY, int scroll) {
        //text label
        GuiUtils.drawString(this.displayString, this.xPosition, this.yPosition+this.sizeY/2-Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT/2+1-scroll, 0xFFFFFFFF, true);

        //bg
        GuiUtils.drawRoundedRect(this.xPosition+this.sizeX/2, this.yPosition-scroll, this.xPosition+this.sizeX, this.yPosition+this.sizeY-scroll, 3, this.mouseInBounds(mouseX, mouseY) ? CyvForge.theme.accent1 : CyvForge.theme.accent2);

        //amount
        GuiUtils.drawCenteredString(this.sliderValue ? s1 : s2, this.xPosition+this.sizeX*3/4, this.yPosition+this.sizeY/2-Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT/2+1-scroll, 0xFFFFFFFF, true);

    }

    @Override
    public void mouseDragged(int mouseX, int mouseY) {

    }

    @Override
    public boolean mouseInBounds(int mouseX, int mouseY) {
        if (mouseX > this.xPosition+this.sizeX/2 && mouseY > this.yPosition
                && mouseX < this.xPosition+this.sizeX && mouseY < this.yPosition+this.sizeY) return true;
        return false;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        this.sliderValue = !this.sliderValue;
        CyvClientConfig.set(this.configOption, this.sliderValue);
        onValueChange();

    }


    @Override
    public void keyTyped(char typedChar, int keyCode) {
        // TODO Auto-generated method stub

    }

    @Override
    public void save() {
        CyvClientConfig.set(this.configOption, this.sliderValue);
    }

}