package net.cyvforge.hud.labels;

import net.cyvforge.CyvForge;
import net.cyvforge.config.CyvClientColorHelper;
import net.cyvforge.config.CyvClientConfig;
import net.cyvforge.event.events.ParkourTickListener;
import net.cyvforge.hud.LabelBundle;
import net.cyvforge.hud.structure.DraggableHUDElement;
import net.cyvforge.hud.structure.ScreenPosition;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import java.text.DecimalFormat;

public class LabelBundleBlipsGrinds extends LabelBundle {

    public LabelBundleBlipsGrinds() {
        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelBlips";}
            public String getDisplayName() {return "Blips";}
            public int getWidth() {
                FontRenderer font = Minecraft.getMinecraft().fontRendererObj;
                if (CyvClientConfig.getBoolean("simpleBlip", false)) {
                    return font.getStringWidth("Blips: 00000");
                }
                StringBuilder str = new StringBuilder("000000.");
                for (int i=0; i<CyvClientConfig.getInt("df",5); i++) str.append("0");
                return font.getStringWidth("Blips: 00000 chained / Y: " + str.toString());
            }
            public int getHeight() {return getLabelHeight();}
            public boolean enabledByDefault() {return false;}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(177, 164);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.getDrawColor();
                long color2 = CyvClientColorHelper.color2.getDrawColor();
                DecimalFormat df = CyvForge.df;
                FontRenderer font = Minecraft.getMinecraft().fontRendererObj;

                drawString("Blips: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString("" + ParkourTickListener.blips, pos.getAbsoluteX() + 1 + font.getStringWidth("Blips: ")
                        , pos.getAbsoluteY() + 1, color2);

                if (!CyvClientConfig.getBoolean("simpleBlip", false)) {
                    String midText = " chained / Y: ";
                    int offset = font.getStringWidth("Blips: " + ParkourTickListener.blips);

                    drawString(midText, pos.getAbsoluteX() + 1 + offset,
                            pos.getAbsoluteY() + 1, color1);
                    drawString(df.format(ParkourTickListener.lastBlipHeight), pos.getAbsoluteX() + 1 + offset + font.getStringWidth(midText),
                            pos.getAbsoluteY() + 1, color2);
                }
            }
            public void renderDummy(ScreenPosition pos) {
                long color1 = CyvClientColorHelper.color1.getDrawColor();
                long color2 = CyvClientColorHelper.color2.getDrawColor();
                FontRenderer font = Minecraft.getMinecraft().fontRendererObj;

                StringBuilder str = new StringBuilder("0.");
                for (int i=0; i<CyvClientConfig.getInt("df",5); i++) str.append("0");

                drawString("Blips: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString("0", pos.getAbsoluteX() + 1 + font.getStringWidth("Blips: ")
                        , pos.getAbsoluteY() + 1, color2);

                if (!CyvClientConfig.getBoolean("simpleBlip", false)) {

                    String midText = " chained / Y: ";
                    drawString(midText, pos.getAbsoluteX() + 1 + font.getStringWidth("Blips: 0"),
                            pos.getAbsoluteY() + 1, color1);
                    drawString("" + str, pos.getAbsoluteX() + 1 +
                                    font.getStringWidth("Blips: 0" + midText),
                            pos.getAbsoluteY() + 1, color2);
                }
            }
        });

        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelGrinds";}
            public String getDisplayName() {return "Grinds";}
            public int getWidth() {return Minecraft.getMinecraft().fontRendererObj
                    .getStringWidth(this.getDisplayName()+": 000");}
            public int getHeight() {return getLabelHeight();}
            public boolean enabledByDefault() {return false;}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(177, 173);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.getDrawColor();
                long color2 = CyvClientColorHelper.color2.getDrawColor();
                FontRenderer font = Minecraft.getMinecraft().fontRendererObj;

                drawString("Grinds: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString("" + ParkourTickListener.grinds, pos.getAbsoluteX() + 1 + font.getStringWidth("Grinds: ")
                        , pos.getAbsoluteY() + 1, color2);
            }
            public void renderDummy(ScreenPosition pos) {
                long color1 = CyvClientColorHelper.color1.getDrawColor();
                long color2 = CyvClientColorHelper.color2.getDrawColor();
                FontRenderer font = Minecraft.getMinecraft().fontRendererObj;

                drawString("Grinds: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString("0", pos.getAbsoluteX() + 1 + font.getStringWidth("Grinds: ")
                        , pos.getAbsoluteY() + 1, color2);
            }
        });
    }

}
