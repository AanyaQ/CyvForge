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

public class LabelBundleTurningAngles extends LabelBundle {

    public LabelBundleTurningAngles() {
        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelJumpAngle";}
            public String getDisplayName() {return "Jump Angle";}
            public int getWidth() {return getLabelWidth(getDisplayName(), true);}
            public int getHeight() {return getLabelHeight();}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(0, 103);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                DecimalFormat df = CyvForge.df;
                String ja = df.format(ParkourTickListener.formatYaw(ParkourTickListener.jf));
                drawString("Jump Angle: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(ja+"\u00B0", pos.getAbsoluteX() + 1 + font.getStringWidth("Jump Angle: ")
                        , pos.getAbsoluteY() + 1, color2);
            }
            public void renderDummy(ScreenPosition pos) {
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;
                StringBuilder str = new StringBuilder("0.");
                for (int i=0; i<CyvClientConfig.getInt("df",5); i++) str.append("0");

                drawString("Jump Angle: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(str+"\u00B0", pos.getAbsoluteX() + 1 + font.getStringWidth("Jump Angle: ")
                        , pos.getAbsoluteY() + 1, color2);
            }
        });

        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelSecondTurn";}
            public String getDisplayName() {return "Second Turn";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(0, 112);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                DecimalFormat df = CyvForge.df;
                String st = df.format(ParkourTickListener.formatYaw(ParkourTickListener.sf - ParkourTickListener.jf));

                drawString("Second Turn: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(st, pos.getAbsoluteX() + 1 + font.getStringWidth("Second Turn: "),
                        pos.getAbsoluteY() + 1, color2);
            }
            public void renderDummy(ScreenPosition pos) {
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                StringBuilder str = new StringBuilder("0.");
                for (int i=0; i<CyvClientConfig.getInt("df",5); i++) str.append("0");

                drawString("Second Turn: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(str.toString(), pos.getAbsoluteX() + 1 + font.getStringWidth("Second Turn: "),
                        pos.getAbsoluteY() + 1, color2);
            }
        });

        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelPreturn";}
            public String getDisplayName() {return "Preturn";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(0, 121);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                DecimalFormat df = CyvForge.df;
                String pt = df.format(ParkourTickListener.formatYaw(ParkourTickListener.pf));

                drawString("Preturn: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(pt+"\u00B0", pos.getAbsoluteX() + 1 + font.getStringWidth("Preturn: "),
                        pos.getAbsoluteY() + 1, color2);
            }
            public void renderDummy(ScreenPosition pos) {
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                StringBuilder str = new StringBuilder("0.");
                for (int i=0; i<CyvClientConfig.getInt("df",5); i++) str.append("0");

                drawString("Preturn: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(str+"\u00B0", pos.getAbsoluteX() + 1 + font.getStringWidth("Preturn: "),
                        pos.getAbsoluteY() + 1, color2);
            }
        });
    }

}
