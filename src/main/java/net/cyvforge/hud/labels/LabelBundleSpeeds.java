package net.cyvforge.hud.labels;

import net.cyvforge.CyvForge;
import net.cyvforge.config.CyvClientColorHelper;
import net.cyvforge.event.events.ParkourTickListener;
import net.cyvforge.hud.LabelBundle;
import net.cyvforge.hud.structure.DraggableHUDElement;
import net.cyvforge.hud.structure.ScreenPosition;
import net.minecraft.client.gui.FontRenderer;

import java.text.DecimalFormat;

public class LabelBundleSpeeds extends LabelBundle {

    public LabelBundleSpeeds() {
        labels.add(new DraggableHUDElement() {
            public String getName() {return "labelXSpeed";}
            public String getDisplayName() {return "X Speed";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public boolean enabledByDefault() {return false;}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(0, 187);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;
                DecimalFormat df = CyvForge.df;
                String x = df.format(ParkourTickListener.vx);
                drawString("X Speed: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(x, pos.getAbsoluteX() + 1 + font.getStringWidth("X Speed: ")
                        , pos.getAbsoluteY() + 1, color2);
            }
            public void renderDummy(ScreenPosition pos) {
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;
                StringBuilder str = new StringBuilder("0.");
                for (int i = 0; i<Integer.parseInt(CyvForge.config.configFields.get("df").value.toString()); i++) str.append("0");
                drawString("X Speed: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(str.toString(), pos.getAbsoluteX() + 1 + font.getStringWidth("X Speed: ")
                        , pos.getAbsoluteY() + 1, color2);
            }
        });

        labels.add(new DraggableHUDElement() {
            public String getName() {return "labelYSpeed";}
            public String getDisplayName() {return "Y Speed";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public boolean enabledByDefault() {return false;}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(0, 196);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;
                DecimalFormat df = CyvForge.df;
                String y = df.format(ParkourTickListener.vy);
                drawString("Y Speed: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(y, pos.getAbsoluteX() + 1 + font.getStringWidth("Y Speed: ")
                        , pos.getAbsoluteY() + 1, color2);
            }
            public void renderDummy(ScreenPosition pos) {
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;
                StringBuilder str = new StringBuilder("0.");
                for (int i = 0; i<Integer.parseInt(CyvForge.config.configFields.get("df").value.toString()); i++) str.append("0");
                drawString("Y Speed: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(str.toString(), pos.getAbsoluteX() + 1 + font.getStringWidth("Y Speed: ")
                        , pos.getAbsoluteY() + 1, color2);
            }
        });

        labels.add(new DraggableHUDElement() {
            public String getName() {return "labelZSpeed";}
            public String getDisplayName() {return "Z Speed";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public boolean enabledByDefault() {return false;}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(0, 205);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;
                DecimalFormat df = CyvForge.df;
                String z = df.format(ParkourTickListener.vz);
                drawString("Z Speed: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(z, pos.getAbsoluteX() + 1 + font.getStringWidth("Z Speed: ")
                        , pos.getAbsoluteY() + 1, color2);
            }
            public void renderDummy(ScreenPosition pos) {
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;
                StringBuilder str = new StringBuilder("0.");
                for (int i = 0; i<Integer.parseInt(CyvForge.config.configFields.get("df").value.toString()); i++) str.append("0");
                drawString("Z Speed: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(str.toString(), pos.getAbsoluteX() + 1 + font.getStringWidth("Z Speed: ")
                        , pos.getAbsoluteY() + 1, color2);
            }
        });

    }
}
