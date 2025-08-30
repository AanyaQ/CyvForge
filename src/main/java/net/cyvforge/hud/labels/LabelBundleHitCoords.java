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

public class LabelBundleHitCoords extends LabelBundle {

    public LabelBundleHitCoords() {
        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelHitX";}
            public String getDisplayName() {return "Hit X";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(0, 131);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.getDrawColor();
                long color2 = CyvClientColorHelper.color2.getDrawColor();
                FontRenderer font = mc.fontRendererObj;

                DecimalFormat df = CyvForge.df;
                String x = df.format(ParkourTickListener.hx);

                drawString("Hit X: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(x, pos.getAbsoluteX() + 1 + font.getStringWidth("Hit X: ")
                        , pos.getAbsoluteY() + 1, color2);
            }
            public void renderDummy(ScreenPosition pos) {
                long color1 = CyvClientColorHelper.color1.getDrawColor();
                long color2 = CyvClientColorHelper.color2.getDrawColor();
                FontRenderer font = mc.fontRendererObj;

                StringBuilder str = new StringBuilder("0.");
                for (int i=0; i<CyvClientConfig.getInt("df",5); i++) str.append("0");

                drawString("Hit X: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(str.toString(), pos.getAbsoluteX() + 1 + font.getStringWidth("Hit X: ")
                        , pos.getAbsoluteY() + 1, color2);
            }
        });

        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelHitY";}
            public String getDisplayName() {return "Hit Y";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(0, 140);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.getDrawColor();
                long color2 = CyvClientColorHelper.color2.getDrawColor();
                FontRenderer font = mc.fontRendererObj;

                DecimalFormat df = CyvForge.df;
                String y = df.format(ParkourTickListener.hy);

                drawString("Hit Y: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(y, pos.getAbsoluteX() + 1 + font.getStringWidth("Hit Y: "),
                        pos.getAbsoluteY() + 1, color2);
            }
            public void renderDummy(ScreenPosition pos) {
                long color1 = CyvClientColorHelper.color1.getDrawColor();
                long color2 = CyvClientColorHelper.color2.getDrawColor();
                FontRenderer font = mc.fontRendererObj;

                StringBuilder str = new StringBuilder("0.");
                for (int i=0; i<CyvClientConfig.getInt("df",5); i++) str.append("0");

                drawString("Hit Y: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(str.toString(), pos.getAbsoluteX() + 1 + font.getStringWidth("Hit Y: "),
                        pos.getAbsoluteY() + 1, color2);
            }
        });

        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelHitZ";}
            public String getDisplayName() {return "Hit Z";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(0, 149);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.getDrawColor();
                long color2 = CyvClientColorHelper.color2.getDrawColor();
                FontRenderer font = mc.fontRendererObj;

                DecimalFormat df = CyvForge.df;
                String z = df.format(ParkourTickListener.hz);

                drawString("Hit Z: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(z, pos.getAbsoluteX() + 1 + font.getStringWidth("Hit Z: "),
                        pos.getAbsoluteY() + 1, color2);
            }
            public void renderDummy(ScreenPosition pos) {
                long color1 = CyvClientColorHelper.color1.getDrawColor();
                long color2 = CyvClientColorHelper.color2.getDrawColor();
                FontRenderer font = mc.fontRendererObj;

                StringBuilder str = new StringBuilder("0.");
                for (int i=0; i<CyvClientConfig.getInt("df",5); i++) str.append("0");

                drawString("Hit Z: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(str.toString(), pos.getAbsoluteX() + 1 + font.getStringWidth("Hit Z: "),
                        pos.getAbsoluteY() + 1, color2);
            }
        });


    }

}
