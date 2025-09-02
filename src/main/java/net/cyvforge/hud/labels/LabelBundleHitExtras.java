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

public class LabelBundleHitExtras extends LabelBundle {

    public LabelBundleHitExtras() {
        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelHitAngle";}
            public String getDisplayName() {return "Hit Angle";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public boolean enabledByDefault() {return false;}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(177, 146);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.getDrawColor();
                long color2 = CyvClientColorHelper.color2.getDrawColor();
                FontRenderer font = mc.fontRendererObj;

                DecimalFormat df = CyvForge.df;
                String z = df.format(ParkourTickListener.formatYaw(ParkourTickListener.hf))+"\u00B0";

                drawString("Hit Angle: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(z, pos.getAbsoluteX() + 1 + font.getStringWidth("Hit Angle: "),
                        pos.getAbsoluteY() + 1, color2);
            }
            public void renderDummy(ScreenPosition pos) {
                long color1 = CyvClientColorHelper.color1.getDrawColor();
                long color2 = CyvClientColorHelper.color2.getDrawColor();
                FontRenderer font = mc.fontRendererObj;

                StringBuilder str = new StringBuilder("0.");
                for (int i=0; i<CyvClientConfig.getInt("df",5); i++) str.append("0");

                drawString("Hit Angle: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(str+"\u00B0", pos.getAbsoluteX() + 1 + font.getStringWidth("Hit Angle: "),
                        pos.getAbsoluteY() + 1, color2);
            }
        });

        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelHitVector";}
            public String getDisplayName() {return "Hit Vector";}
            public int getWidth() {
                FontRenderer font = mc.fontRendererObj;
                StringBuilder str = new StringBuilder(getDisplayName() + ": 00000.");
                for (int i=0; i<CyvClientConfig.getInt("df",5); i++) str.append("0");
                str.append("/000.");
                for (int i=0; i<CyvClientConfig.getInt("df",5); i++) str.append("0");
                str.append("\u00B0");
                return font.getStringWidth(str.toString());
            }
            public int getHeight() {return getLabelHeight();}
            public boolean enabledByDefault() {return false;}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(177, 155);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.getDrawColor();
                long color2 = CyvClientColorHelper.color2.getDrawColor();
                FontRenderer font = mc.fontRendererObj;

                DecimalFormat df = CyvForge.df;
                String speed = df.format(Math.hypot(ParkourTickListener.hvx, ParkourTickListener.hvz));
                String angle = df.format(Math.toDegrees(Math.atan2((ParkourTickListener.hvx == 0) ? 0 : -ParkourTickListener.hvx, ParkourTickListener.hvz)));

                drawString("Hit Vector: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(speed, pos.getAbsoluteX() + 1 + font.getStringWidth("Hit Vector: ")
                        , pos.getAbsoluteY() + 1, color2);
                drawString("/", pos.getAbsoluteX() + 1 + font.getStringWidth("Hit Vector: " + speed)
                        , pos.getAbsoluteY() + 1, color1);
                drawString(angle+"\u00B0", pos.getAbsoluteX() + 1 + font.getStringWidth("Hit Vector: " + speed + "/")
                        , pos.getAbsoluteY() + 1, color2);
            }
            public void renderDummy(ScreenPosition pos) {
                long color1 = CyvClientColorHelper.color1.getDrawColor();
                long color2 = CyvClientColorHelper.color2.getDrawColor();
                FontRenderer font = mc.fontRendererObj;

                StringBuilder str = new StringBuilder("0.");
                for (int i = 0; i<Integer.parseInt(CyvForge.config.configFields.get("df").value.toString()); i++) str.append("0");
                drawString("Hit Vector: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(str.toString(), pos.getAbsoluteX() + 1 + font.getStringWidth("Hit Vector: ")
                        , pos.getAbsoluteY() + 1, color2);
                drawString("/", pos.getAbsoluteX() + 1 + font.getStringWidth("Hit Vector: " + str)
                        , pos.getAbsoluteY() + 1, color1);
                drawString(str+"\u00B0", pos.getAbsoluteX() + 1 + font.getStringWidth("Hit Vector: " + str + "/")
                        , pos.getAbsoluteY() + 1, color2);
            }
        });
    }

}
