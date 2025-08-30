package net.cyvforge.hud.nonlabels;

import net.cyvforge.CyvForge;
import net.cyvforge.config.CyvClientColorHelper;
import net.cyvforge.config.CyvClientConfig;
import net.cyvforge.event.events.ParkourTickListener;
import net.cyvforge.hud.structure.DraggableHUDElement;
import net.cyvforge.hud.structure.ScreenPosition;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import java.text.DecimalFormat;

public class TurnHUD extends DraggableHUDElement {
    FontRenderer font = Minecraft.getMinecraft().fontRendererObj;

    @Override
    public String getName() {
        return "turnHUD";
    }

    @Override
    public String getDisplayName() {
        return "Turning HUD";
    }

    @Override
    public boolean enabledByDefault() {
        return false;
    }

    @Override
    public ScreenPosition getDefaultPosition() {
        return new ScreenPosition(250, 100);
    }

    @Override
    public int getWidth() {
        return getLabelWidth("12", true);
    }

    @Override
    public int getHeight() {
        int rows = Math.max(1, Math.min(CyvClientConfig.getInt("turnHUDAngles", 12), 12));
        return 10 * rows;
    }

    @Override
    public void render(ScreenPosition pos) {
        long color1 = CyvClientColorHelper.color1.getDrawColor();
        long color2 = CyvClientColorHelper.color2.getDrawColor();
        DecimalFormat df = CyvForge.df;

        int a = Math.max(1, Math.min(CyvClientConfig.getInt("turnHUDAngleMin", 1), 12));
        int b = Math.max(1, Math.min(CyvClientConfig.getInt("turnHUDAngleMax", 12), 12));

        for (int i = Math.min(a, b) - 1; i < Math.max(a, b); i++) {
            String angle = df.format(ParkourTickListener.formatYaw(ParkourTickListener.turningAngles[i]));
            drawString((i + 1) + ": ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1 + (i * 10), color1);
            drawString(angle + "\u00B0", pos.getAbsoluteX() + 1 + font.getStringWidth((i + 1) + ": "),
                    pos.getAbsoluteY() + 1 + (i * 10), color2);
        }

    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        if (!this.isVisible) return;
        long color1 = CyvClientColorHelper.color1.getDrawColor();
        long color2 = CyvClientColorHelper.color2.getDrawColor();
        int a = Math.max(1, Math.min(CyvClientConfig.getInt("turnHUDAngleMin", 12), 12));
        int b = Math.max(1, Math.min(CyvClientConfig.getInt("turnHUDAngleMax", 12), 12));

        StringBuilder str = new StringBuilder("0.");
        for (int i=0; i<CyvClientConfig.getInt("df",5); i++) str.append("0");

        for (int i = Math.min(a, b) - 1; i < Math.max(a, b); i++) {
            drawString((i + 1) + ": ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1 + (i * 10), color1);
            drawString(str+"\u00B0", pos.getAbsoluteX() + 1 + font.getStringWidth((i + 1) + ": "),
                    pos.getAbsoluteY() + 1 + (i * 10), color2);
        }

    }

    public int getLabelWidth(String s, boolean angle) {
        font = Minecraft.getMinecraft().fontRendererObj;

        StringBuilder str;
        if (angle) str = new StringBuilder(s + ": 000.");
        else str = new StringBuilder(s + ": 000000.");
        for (int i = 0; i< CyvClientConfig.getInt("df",5); i++) str.append("0");
        if (angle) str.append("\u00B0");
        return font.getStringWidth(str.toString());
    }

    public int getLabelHeight() {
        return 9;
    }

}
