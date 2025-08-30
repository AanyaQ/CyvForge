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

public class LabelBundleSpeedVector extends LabelBundle {

    public LabelBundleSpeedVector() {
        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelIndividualSpeeds";}
            public String getDisplayName() {return "Speeds";}
            public int getWidth() {
                FontRenderer font = mc.fontRendererObj;
                StringBuilder num = new StringBuilder("000000.");
                for (int i=0; i<CyvClientConfig.getInt("df",5); i++) num.append("0");
                return font.getStringWidth("Speeds: " + num + "/" + num + "/" + num);
            }
            public int getHeight() {return getLabelHeight();}
            public boolean enabledByDefault() {return false;}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(0, 214);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.getDrawColor();
                long color2 = CyvClientColorHelper.color2.getDrawColor();
                FontRenderer font = mc.fontRendererObj;
                DecimalFormat df = CyvForge.df;
                String x = df.format(ParkourTickListener.vx);
                String y = df.format(ParkourTickListener.vy);
                String z = df.format(ParkourTickListener.vz);
                drawString("Speeds: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(x, pos.getAbsoluteX() + 1 + font.getStringWidth("Speeds: ")
                        , pos.getAbsoluteY() + 1, color2);
                drawString("/", pos.getAbsoluteX() + 1 + font.getStringWidth("Speeds: " + x)
                        , pos.getAbsoluteY() + 1, color1);
                drawString(y, pos.getAbsoluteX() + 1 + font.getStringWidth("Speeds: " + x + "/")
                        , pos.getAbsoluteY() + 1, color2);
                drawString("/", pos.getAbsoluteX() + 1 + font.getStringWidth("Speeds: " + x + "/" + y)
                        , pos.getAbsoluteY() + 1, color1);
                drawString(z, pos.getAbsoluteX() + 1 + font.getStringWidth("Speeds: " + x + "/" + y + "/")
                        , pos.getAbsoluteY() + 1, color2);
            }
            public void renderDummy(ScreenPosition pos) {
                long color1 = CyvClientColorHelper.color1.getDrawColor();
                long color2 = CyvClientColorHelper.color2.getDrawColor();
                FontRenderer font = mc.fontRendererObj;
                StringBuilder str = new StringBuilder("0.");
                for (int i = 0; i<Integer.parseInt(CyvForge.config.configFields.get("df").value.toString()); i++) str.append("0");
                drawString("Speeds: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(str.toString(), pos.getAbsoluteX() + 1 + font.getStringWidth("Speeds: ")
                        , pos.getAbsoluteY() + 1, color2);
                drawString("/", pos.getAbsoluteX() + 1 + font.getStringWidth("Speeds: " + str)
                        , pos.getAbsoluteY() + 1, color1);
                drawString(str.toString(), pos.getAbsoluteX() + 1 + font.getStringWidth("Speeds: " + str + "/")
                        , pos.getAbsoluteY() + 1, color2);
                drawString("/", pos.getAbsoluteX() + 1 + font.getStringWidth("Speeds: " + str + "/" + str)
                        , pos.getAbsoluteY() + 1, color1);
                drawString(str.toString(), pos.getAbsoluteX() + 1 + font.getStringWidth("Speeds: " + str + "/" + str + "/")
                        , pos.getAbsoluteY() + 1, color2);
            }
        });

        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelSpeedVector";}
            public String getDisplayName() {return "Speed Vector";}
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
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(0, 223);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.getDrawColor();
                long color2 = CyvClientColorHelper.color2.getDrawColor();
                FontRenderer font = mc.fontRendererObj;

                DecimalFormat df = CyvForge.df;
                String speed = df.format(Math.hypot(ParkourTickListener.vx, ParkourTickListener.vz));
                String angle = df.format(Math.toDegrees(Math.atan2((ParkourTickListener.vx == 0) ? 0 : -ParkourTickListener.vx, ParkourTickListener.vz)));

                drawString("Speed Vector: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(speed, pos.getAbsoluteX() + 1 + font.getStringWidth("Speed Vector: ")
                        , pos.getAbsoluteY() + 1, color2);
                drawString("/", pos.getAbsoluteX() + 1 + font.getStringWidth("Speed Vector: " + speed)
                        , pos.getAbsoluteY() + 1, color1);
                drawString(angle+"\u00B0", pos.getAbsoluteX() + 1 + font.getStringWidth("Speed Vector: " + speed + "/")
                        , pos.getAbsoluteY() + 1, color2);
            }
            public void renderDummy(ScreenPosition pos) {
                long color1 = CyvClientColorHelper.color1.getDrawColor();
                long color2 = CyvClientColorHelper.color2.getDrawColor();
                FontRenderer font = mc.fontRendererObj;

                StringBuilder str = new StringBuilder("0.");
                for (int i = 0; i<Integer.parseInt(CyvForge.config.configFields.get("df").value.toString()); i++) str.append("0");
                drawString("Speed Vector: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(str.toString(), pos.getAbsoluteX() + 1 + font.getStringWidth("Speed Vector: ")
                        , pos.getAbsoluteY() + 1, color2);
                drawString("/", pos.getAbsoluteX() + 1 + font.getStringWidth("Speed Vector: " + str)
                        , pos.getAbsoluteY() + 1, color1);
                drawString(str+"\u00B0", pos.getAbsoluteX() + 1 + font.getStringWidth("Speed Vector: " + str + "/")
                        , pos.getAbsoluteY() + 1, color2);
            }
        });

    }

}