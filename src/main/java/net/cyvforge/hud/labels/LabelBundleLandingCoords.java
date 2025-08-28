package net.cyvforge.hud.labels;

import net.cyvforge.CyvForge;
import net.cyvforge.config.CyvClientColorHelper;
import net.cyvforge.config.CyvClientConfig;
import net.cyvforge.event.events.ParkourTickListener;
import net.cyvforge.hud.LabelBundle;
import net.cyvforge.hud.structure.DraggableHUDElement;
import net.cyvforge.hud.structure.ScreenPosition;
import net.minecraft.client.gui.FontRenderer;

import java.text.DecimalFormat;

public class LabelBundleLandingCoords extends LabelBundle {

    public LabelBundleLandingCoords() {
        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelLandingX";}
            public String getDisplayName() {return "Last Landing X";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(0, 75);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;
                
                DecimalFormat df = CyvForge.df;
                String x = df.format(ParkourTickListener.lx);

                drawString("Last Landing X: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(x, pos.getAbsoluteX() + 1 + font.getStringWidth("Last Landing X: ")
                        , pos.getAbsoluteY() + 1, color2);
            }
            public void renderDummy(ScreenPosition pos) {
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;
                
                StringBuilder str = new StringBuilder("0.");
                for (int i=0; i<CyvClientConfig.getInt("df",5); i++) str.append("0");

                drawString("Last Landing X: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(str.toString(), pos.getAbsoluteX() + 1 + font.getStringWidth("Last Landing X: ")
                        , pos.getAbsoluteY() + 1, color2);
            }
        });

        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelLandingY";}
            public String getDisplayName() {return "Last Landing Y";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(0, 84);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                DecimalFormat df = CyvForge.df;
                String y = df.format(ParkourTickListener.ly);

                drawString("Last Landing Y: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(y, pos.getAbsoluteX() + 1 + font.getStringWidth("Last Landing Y: "),
                        pos.getAbsoluteY() + 1, color2);
            }
            public void renderDummy(ScreenPosition pos) {
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                StringBuilder str = new StringBuilder("0.");
                for (int i=0; i<CyvClientConfig.getInt("df",5); i++) str.append("0");

                drawString("Last Landing Y: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(str.toString(), pos.getAbsoluteX() + 1 + font.getStringWidth("Last Landing Y: "),
                        pos.getAbsoluteY() + 1, color2);
            }
        });

        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelLandingZ";}
            public String getDisplayName() {return "Last Landing Z";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(0, 93);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                DecimalFormat df = CyvForge.df;
                String z = df.format(ParkourTickListener.lz);

                drawString("Last Landing Z: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(z, pos.getAbsoluteX() + 1 + font.getStringWidth("Last Landing Z: "),
                        pos.getAbsoluteY() + 1, color2);
            }
            public void renderDummy(ScreenPosition pos) {
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                StringBuilder str = new StringBuilder("0.");
                for (int i=0; i<CyvClientConfig.getInt("df",5); i++) str.append("0");

                drawString("Last Landing Z: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(str.toString(), pos.getAbsoluteX() + 1 + font.getStringWidth("Last Landing Z: "),
                        pos.getAbsoluteY() + 1, color2);
            }
        });
    }

}
