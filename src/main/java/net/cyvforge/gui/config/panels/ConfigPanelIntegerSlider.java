package net.cyvforge.gui.config.panels;

import net.cyvforge.CyvForge;
import net.cyvforge.config.CyvClientConfig;
import net.cyvforge.gui.GuiModConfig;
import net.cyvforge.gui.config.ConfigPanel;
import net.cyvforge.util.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.MathHelper;

import java.util.ArrayList;

public class ConfigPanelIntegerSlider implements ConfigPanel {
    public int sliderValue;
    public String configOption;
    public String displayString;
    public final int minValue;
    private final int maxValue;
    public final int index;
    public GuiModConfig screenIn;

    private int xPosition;
    private int yPosition;
    private int sizeX;
    private int sizeY;

    public ConfigPanelIntegerSlider(ArrayList<ConfigPanel> array, String configOption, String displayString, int minValue, int maxValue, GuiModConfig screenIn) {
        this.index = array.size();
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.displayString = displayString;
        this.configOption = configOption;
        this.screenIn = screenIn;

        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        sizeX = screenIn.sizeX-20;
        sizeY = Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT*3/2;
        this.xPosition = sr.getScaledWidth()/2-screenIn.sizeX/2+10;
        this.yPosition = sr.getScaledHeight()/2-screenIn.sizeY/2+10 + (index * Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT * 2);
        this.sliderValue = CyvClientConfig.getInt(configOption, 0);
        MathHelper.clamp_int(this.sliderValue, minValue, maxValue);

    }

    @Override
    public void draw(int mouseX, int mouseY, int scroll) {
        //text label
        GuiUtils.drawString(this.displayString, this.xPosition, this.yPosition+this.sizeY/2-Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT/2+1-scroll, 0xFFFFFFFF, true);
        //bg
        GuiUtils.drawRoundedRect(this.xPosition+this.sizeX/2, this.yPosition-scroll, this.xPosition+this.sizeX, this.yPosition+this.sizeY-scroll, 3, this.mouseInBounds(mouseX, mouseY) ? CyvForge.theme.shade1 : CyvForge.theme.shade2);
        //slider
        GuiUtils.drawRoundedRect(this.xPosition+this.sizeX/2+(int)(sizeX/2 * (sliderValue-minValue)/(maxValue-minValue))-3, this.yPosition-1-scroll,
                this.xPosition+this.sizeX/2+(int)(sizeX/2 * (sliderValue-minValue)/(maxValue-minValue))+3, this.yPosition+this.sizeY+1-scroll, 1, CyvForge.theme.mainBase());
        //amount
        GuiUtils.drawCenteredString(" "+this.sliderValue, this.xPosition+this.sizeX*3/4, this.yPosition+this.sizeY/2-Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT/2+1-scroll, 0xFFFFFFFF, true);

    }

    @Override
    public void mouseDragged(int mouseX, int mouseY) {
        this.sliderValue = (int)((mouseX+2-(this.xPosition+this.sizeX/2))/(float)(this.sizeX/2) * (this.maxValue - this.minValue)) + this.minValue;
        this.sliderValue = (int) MathHelper.clamp_double(this.sliderValue, this.minValue, this.maxValue);
        CyvClientConfig.set(this.configOption, this.sliderValue);
        onValueChange();
    }

    @Override
    public boolean mouseInBounds(int mouseX, int mouseY) {
        if (mouseX > this.xPosition+this.sizeX/2 && mouseY > this.yPosition
                && mouseX < this.xPosition+this.sizeX && mouseY < this.yPosition+this.sizeY) return true;
        return false;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        // TODO Auto-generated method stub

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