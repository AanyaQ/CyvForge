package net.cyvforge.hud.labels;

import net.cyvforge.config.CyvClientColorHelper;
import net.cyvforge.hud.structure.DraggableHUDElement;
import net.cyvforge.hud.structure.ScreenPosition;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LabelTime extends DraggableHUDElement {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public String getName() {
        return "ModSystemTime";
    }

    @Override
    public String getDisplayName() {
        return "System Time Label";
    }

    @Override
    public int getWidth() {
        FontRenderer font = Minecraft.getMinecraft().fontRendererObj;
        return font.getStringWidth("Time: 23:59:59");
    }

    @Override
    public int getHeight() {
        FontRenderer font = Minecraft.getMinecraft().fontRendererObj;
        return font.FONT_HEIGHT;
    }

    @Override
    public void render(ScreenPosition pos) {
        long color1 = CyvClientColorHelper.color1.getDrawColor();
        long color2 = CyvClientColorHelper.color2.getDrawColor();
        FontRenderer font = mc.fontRendererObj;

        LocalDateTime now = LocalDateTime.now();

        drawString("Time: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
        drawString(dtf.format(now), pos.getAbsoluteX() + 1 + font.getStringWidth("Time: "),
                pos.getAbsoluteY() + 1, color2);
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        long color1 = CyvClientColorHelper.color1.getDrawColor();
        long color2 = CyvClientColorHelper.color2.getDrawColor();
        FontRenderer font = mc.fontRendererObj;

        drawString("Time: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
        drawString("23:59:59", pos.getAbsoluteX() + 1 + font.getStringWidth("Time: "), pos.getAbsoluteY() + 1, color2);
    }

    @Override
    public ScreenPosition getDefaultPosition() {
        return new ScreenPosition(106, 54);
    }

    @Override
    public boolean enabledByDefault() {
        return false;
    }
}
