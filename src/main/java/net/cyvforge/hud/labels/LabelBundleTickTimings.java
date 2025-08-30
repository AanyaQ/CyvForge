package net.cyvforge.hud.labels;

import net.cyvforge.config.CyvClientColorHelper;
import net.cyvforge.config.CyvClientConfig;
import net.cyvforge.event.events.ParkourTickListener;
import net.cyvforge.hud.LabelBundle;
import net.cyvforge.hud.structure.DraggableHUDElement;
import net.cyvforge.hud.structure.ScreenPosition;
import net.minecraft.client.gui.FontRenderer;

public class LabelBundleTickTimings extends LabelBundle {

    public LabelBundleTickTimings() {
        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelLastTiming";}
            public String getDisplayName() {return "Last Timing";}
            public int getWidth() {
                FontRenderer font = mc.fontRendererObj;
                return font.getStringWidth("Last Timing: [Timing & numbers here]");
            }
            public int getHeight() {return getLabelHeight();}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(0, 56);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.getDrawColor();
                long color2 = CyvClientColorHelper.color2.getDrawColor();
                FontRenderer font = mc.fontRendererObj;

                String timing = ParkourTickListener.lastTiming;
                drawString("Last Timing: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(timing, pos.getAbsoluteX() + 1 + font.getStringWidth("Last Timing: ")
                        , pos.getAbsoluteY() + 1, color2);
            }
            public void renderDummy(ScreenPosition pos) {
                long color1 = CyvClientColorHelper.color1.getDrawColor();
                long color2 = CyvClientColorHelper.color2.getDrawColor();
                FontRenderer font = mc.fontRendererObj;

                drawString("Last Timing: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString("[Timing]", pos.getAbsoluteX() + 1 + font.getStringWidth("Last Timing: ")
                        , pos.getAbsoluteY() + 1, color2);
            }
        });

        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelAirtime";}
            public String getDisplayName() {return "Airtime";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(0, 65);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.getDrawColor();
                long color2 = CyvClientColorHelper.color2.getDrawColor();
                FontRenderer font = mc.fontRendererObj;

                int airtime = ParkourTickListener.lastAirtime;

                drawString("Airtime: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(airtime, pos.getAbsoluteX() + 1 + font.getStringWidth("Airtime: "),
                        pos.getAbsoluteY() + 1, color2);
            }
            public void renderDummy(ScreenPosition pos) {
                long color1 = CyvClientColorHelper.color1.getDrawColor();
                long color2 = CyvClientColorHelper.color2.getDrawColor();
                FontRenderer font = mc.fontRendererObj;

                drawString("Airtime: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(0, pos.getAbsoluteX() + 1 + font.getStringWidth("Airtime: "),
                        pos.getAbsoluteY() + 1, color2);
            }
        });

        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelTier";}
            public String getDisplayName() {return "Tier";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public boolean enabledByDefault() {return false;}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(177, 137);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.getDrawColor();
                long color2 = CyvClientColorHelper.color2.getDrawColor();
                FontRenderer font = mc.fontRendererObj;

                int airtime = 12 - ParkourTickListener.lastAirtime;

                drawString("Tier: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(airtime, pos.getAbsoluteX() + 1 + font.getStringWidth("Tier: "),
                        pos.getAbsoluteY() + 1, color2);
            }
            public void renderDummy(ScreenPosition pos) {
                long color1 = CyvClientColorHelper.color1.getDrawColor();
                long color2 = CyvClientColorHelper.color2.getDrawColor();
                FontRenderer font = mc.fontRendererObj;

                drawString("Tier: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(0, pos.getAbsoluteX() + 1 + font.getStringWidth("Tier: "),
                        pos.getAbsoluteY() + 1, color2);
            }
        });
    }

}
