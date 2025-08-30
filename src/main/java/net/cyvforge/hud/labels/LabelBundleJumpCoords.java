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

public class LabelBundleJumpCoords extends LabelBundle {

    public LabelBundleJumpCoords() {
        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelJumpX";}
            public String getDisplayName() {return "Jump X";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public boolean enabledByDefault() {return false;}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(0, 159);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.getDrawColor();
                long color2 = CyvClientColorHelper.color2.getDrawColor();
                FontRenderer font = mc.fontRendererObj;
                
                DecimalFormat df = CyvForge.df;
                String x = df.format(ParkourTickListener.jx);

                drawString("Jump X: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(x, pos.getAbsoluteX() + 1 + font.getStringWidth("Jump X: ")
                        , pos.getAbsoluteY() + 1, color2);
            }
            public void renderDummy(ScreenPosition pos) {
                long color1 = CyvClientColorHelper.color1.getDrawColor();
                long color2 = CyvClientColorHelper.color2.getDrawColor();
                FontRenderer font = mc.fontRendererObj;
                
                StringBuilder str = new StringBuilder("0.");
                for (int i=0; i<CyvClientConfig.getInt("df",5); i++) str.append("0");

                drawString("Jump X: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(str.toString(), pos.getAbsoluteX() + 1 + font.getStringWidth("Jump X: ")
                        , pos.getAbsoluteY() + 1, color2);
            }
        });

        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelJumpY";}
            public String getDisplayName() {return "Jump Y";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public boolean enabledByDefault() {return false;}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(0, 168);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.getDrawColor();
                long color2 = CyvClientColorHelper.color2.getDrawColor();
                FontRenderer font = mc.fontRendererObj;

                DecimalFormat df = CyvForge.df;
                String y = df.format(ParkourTickListener.jy);

                drawString("Jump Y: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(y, pos.getAbsoluteX() + 1 + font.getStringWidth("Jump Y: "),
                        pos.getAbsoluteY() + 1, color2);
            }
            public void renderDummy(ScreenPosition pos) {
                long color1 = CyvClientColorHelper.color1.getDrawColor();
                long color2 = CyvClientColorHelper.color2.getDrawColor();
                FontRenderer font = mc.fontRendererObj;

                StringBuilder str = new StringBuilder("0.");
                for (int i=0; i<CyvClientConfig.getInt("df",5); i++) str.append("0");

                drawString("Jump Y: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(str.toString(), pos.getAbsoluteX() + 1 + font.getStringWidth("Jump Y: "),
                        pos.getAbsoluteY() + 1, color2);
            }
        });

        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelJumpZ";}
            public String getDisplayName() {return "Jump Z";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public boolean enabledByDefault() {return false;}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(0, 177);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.getDrawColor();
                long color2 = CyvClientColorHelper.color2.getDrawColor();
                FontRenderer font = mc.fontRendererObj;

                DecimalFormat df = CyvForge.df;
                String z = df.format(ParkourTickListener.jz);

                drawString("Jump Z: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(z, pos.getAbsoluteX() + 1 + font.getStringWidth("Jump Z: "),
                        pos.getAbsoluteY() + 1, color2);
            }
            public void renderDummy(ScreenPosition pos) {
                long color1 = CyvClientColorHelper.color1.getDrawColor();
                long color2 = CyvClientColorHelper.color2.getDrawColor();
                FontRenderer font = mc.fontRendererObj;

                StringBuilder str = new StringBuilder("0.");
                for (int i=0; i<CyvClientConfig.getInt("df",5); i++) str.append("0");

                drawString("Jump Z: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(str.toString(), pos.getAbsoluteX() + 1 + font.getStringWidth("Jump Z: "),
                        pos.getAbsoluteY() + 1, color2);
            }
        });
    }

}
