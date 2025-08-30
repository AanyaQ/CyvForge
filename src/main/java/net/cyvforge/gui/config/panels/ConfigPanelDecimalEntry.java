package net.cyvforge.gui.config.panels;

import net.cyvforge.CyvForge;
import net.cyvforge.config.CyvClientConfig;
import net.cyvforge.gui.GuiModConfig;
import net.cyvforge.gui.config.ConfigPanel;
import net.cyvforge.util.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.MathHelper;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

public class ConfigPanelDecimalEntry implements ConfigPanel {
    public GuiTextField field;
    public String configOption;
    public String displayString;
    public final int index;
    public GuiModConfig screenIn;

    private int xPosition;
    private int yPosition;
    private int sizeX;
    private int sizeY;

    private double minBound = -Double.MAX_VALUE;
    private double maxBound = Double.MAX_VALUE;

    public ConfigPanelDecimalEntry(ArrayList<ConfigPanel> array, String configOption, String displayString, double min, double max, GuiModConfig screenIn) {
        this(array, configOption, displayString, screenIn);
        this.minBound = min;
        this.maxBound = max;
    }

    public ConfigPanelDecimalEntry(ArrayList<ConfigPanel> array, String configOption, String displayString, GuiModConfig screenIn) {
        this.index = array.size();
        this.displayString = displayString;
        this.configOption = configOption;
        this.screenIn = screenIn;

        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        sizeX = screenIn.sizeX-20;
        sizeY = Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT*3/2;
        this.xPosition = sr.getScaledWidth()/2-screenIn.sizeX/2+10;
        this.yPosition = sr.getScaledHeight()/2-screenIn.sizeY/2+10 + (index * Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT * 2);

        this.field = new GuiTextField(0, Minecraft.getMinecraft().fontRendererObj, this.xPosition+this.sizeX/2+2, this.yPosition+this.sizeY/2-Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT/2+1, this.sizeX/2-4, Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT/2);
        this.field.setText(CyvClientConfig.getDouble(configOption, 0)+"");
        this.field.setEnableBackgroundDrawing(false);
        Keyboard.enableRepeatEvents(true);
    }

    @Override
    public void draw(int mouseX, int mouseY, int scroll) {
        //text label
        GuiUtils.drawString(this.displayString, this.xPosition, this.yPosition+this.sizeY/2-Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT/2+1-scroll, 0xFFFFFFFF, true);
        //bg
        GuiUtils.drawRoundedRect(this.xPosition+this.sizeX/2, this.yPosition-scroll, this.xPosition+this.sizeX, this.yPosition+this.sizeY-scroll, 3, this.mouseInBounds(mouseX, mouseY) ? CyvForge.theme.shade1 : CyvForge.theme.shade2);

        this.field.yPosition = this.yPosition+this.sizeY/2-Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT/2 + 1 - scroll;
        this.field.drawTextBox();


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
        this.field.mouseClicked(mouseX, mouseY, mouseButton);

        if (!(mouseX >= field.xPosition && mouseX <= field.xPosition + field.width && mouseY >= field.yPosition && mouseY <= field.yPosition + field.height)) {
            this.unselect();
        }
    }


    @Override
    public void keyTyped(char typedChar, int keyCode) {
        this.field.textboxKeyTyped(typedChar, keyCode);

    }

    @Override
    public void save() {
        double val = 0;
        try {
            val = MathHelper.clamp_double(Double.valueOf(this.field.getText()), this.minBound, this.maxBound);
            this.field.setText(val+"");
            CyvClientConfig.set(this.configOption, val);
        } catch (Exception e) {}
    }

    @Override
    public void update() {
        this.field.updateCursorCounter();
    }

    @Override
    public void select() {
        this.field.setFocused(true);
    }

    @Override
    public void unselect() {
        this.field.setFocused(false);
        try {
            double val = MathHelper.clamp_double(Double.valueOf(this.field.getText()), this.minBound, this.maxBound);
            this.field.setText(val+"");
            CyvClientConfig.set(this.configOption, val);
        } catch (Exception e) {}
        onValueChange();
    }

}