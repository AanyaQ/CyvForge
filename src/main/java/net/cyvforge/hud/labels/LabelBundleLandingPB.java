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

public class LabelBundleLandingPB extends LabelBundle {

    public LabelBundleLandingPB() {
        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelPB";}
            public String getDisplayName() {return "PB";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(106, 0);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;
                
                DecimalFormat df = CyvForge.df;
                String pb;
                if (ParkourTickListener.landingBlock == null) {
                    pb = "NaN";
                } else {
                    pb = (ParkourTickListener.landingBlock.pb == null) ? "NaN" : df.format(ParkourTickListener.landingBlock.pb);
                }

                drawString("PB: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(pb, pos.getAbsoluteX() + 1 + font.getStringWidth("PB: ")
                        , pos.getAbsoluteY() + 1, color2);
            }
            public void renderDummy(ScreenPosition pos) {
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;
                
                StringBuilder str = new StringBuilder("0.");
                for (int i=0; i<CyvClientConfig.getInt("df",5); i++) str.append("0");

                drawString("PB: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(str.toString(), pos.getAbsoluteX() + 1 + font.getStringWidth("PB: ")
                        , pos.getAbsoluteY() + 1, color2);
            }
        });

        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelXOffset";}
            public String getDisplayName() {return "X Offset";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(106, 9);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                DecimalFormat df = CyvForge.df;
                String x;
                if (ParkourTickListener.landingBlock == null) {
                    x = "NaN";
                } else {
                    x = (ParkourTickListener.landingBlock.lastOffsetX == null) ? "NaN" : df.format(ParkourTickListener.landingBlock.lastOffsetX.doubleValue());
                }

                drawString("X Offset: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(x, pos.getAbsoluteX() + 1 + font.getStringWidth("X Offset: "),
                        pos.getAbsoluteY() + 1, color2);
            }
            public void renderDummy(ScreenPosition pos) {
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                StringBuilder str = new StringBuilder("0.");
                for (int i=0; i<CyvClientConfig.getInt("df",5); i++) str.append("0");

                drawString("X Offset: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(str.toString(), pos.getAbsoluteX() + 1 + font.getStringWidth("X Offset: "),
                        pos.getAbsoluteY() + 1, color2);
            }
        });

        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelZOffset";}
            public String getDisplayName() {return "Z Offset";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(106, 18);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                DecimalFormat df = CyvForge.df;
                String z;
                if (ParkourTickListener.landingBlock == null) {
                    z = "NaN";
                } else {
                    z = (ParkourTickListener.landingBlock.lastOffsetZ == null) ? "NaN" : df.format(ParkourTickListener.landingBlock.lastOffsetZ.doubleValue());
                }

                drawString("Z Offset: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(z, pos.getAbsoluteX() + 1 + font.getStringWidth("Z Offset: "),
                        pos.getAbsoluteY() + 1, color2);
            }
            public void renderDummy(ScreenPosition pos) {
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                StringBuilder str = new StringBuilder("0.");
                for (int i=0; i<CyvClientConfig.getInt("df",5); i++) str.append("0");

                drawString("Z Offset: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(str.toString(), pos.getAbsoluteX() + 1 + font.getStringWidth("Z Offset: "),
                        pos.getAbsoluteY() + 1, color2);
            }
        });

        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelTotalOffset";}
            public String getDisplayName() {return "Total Offset";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public boolean enabledByDefault() {return false;}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(106, 45);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                DecimalFormat df = CyvForge.df;
                String pb;
                if (ParkourTickListener.landingBlock == null) {
                    pb = "NaN";
                } else {
                    pb = (ParkourTickListener.landingBlock.lastPb == null) ? "NaN" : df.format(ParkourTickListener.landingBlock.lastPb);
                }

                drawString("Total Offset: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(pb, pos.getAbsoluteX() + 1 + font.getStringWidth("Total Offset: ")
                        , pos.getAbsoluteY() + 1, color2);
            }
            public void renderDummy(ScreenPosition pos) {
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                StringBuilder str = new StringBuilder("0.");
                for (int i=0; i<CyvClientConfig.getInt("df",5); i++) str.append("0");

                drawString("Total Offset: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(str.toString(), pos.getAbsoluteX() + 1 + font.getStringWidth("Total Offset: ")
                        , pos.getAbsoluteY() + 1, color2);
            }
        });

        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelXPB";}
            public String getDisplayName() {return "X PB";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public boolean enabledByDefault() {return false;}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(106, 27);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                DecimalFormat df = CyvForge.df;
                String x;
                if (ParkourTickListener.landingBlock == null) {
                    x = "NaN";
                } else {
                    x = (ParkourTickListener.landingBlock.pbX == null) ? "NaN" : df.format(ParkourTickListener.landingBlock.pbX.doubleValue());
                }

                drawString("X PB: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(x, pos.getAbsoluteX() + 1 + font.getStringWidth("X PB: "),
                        pos.getAbsoluteY() + 1, color2);
            }
            public void renderDummy(ScreenPosition pos) {
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                StringBuilder str = new StringBuilder("0.");
                for (int i=0; i<CyvClientConfig.getInt("df",5); i++) str.append("0");

                drawString("X PB: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(str.toString(), pos.getAbsoluteX() + 1 + font.getStringWidth("X PB: "),
                        pos.getAbsoluteY() + 1, color2);
            }
        });

        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelZPB";}
            public String getDisplayName() {return "Z PB";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public boolean enabledByDefault() {return false;}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(106, 36);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                DecimalFormat df = CyvForge.df;
                String z;
                if (ParkourTickListener.landingBlock == null) {
                    z = "NaN";
                } else {
                    z = (ParkourTickListener.landingBlock.pbZ == null) ? "NaN" : df.format(ParkourTickListener.landingBlock.pbZ.doubleValue());
                }

                drawString("Z PB: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(z, pos.getAbsoluteX() + 1 + font.getStringWidth("Z PB: "),
                        pos.getAbsoluteY() + 1, color2);
            }
            public void renderDummy(ScreenPosition pos) {
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                StringBuilder str = new StringBuilder("0.");
                for (int i=0; i<CyvClientConfig.getInt("df",5); i++) str.append("0");

                drawString("Z PB: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString(str.toString(), pos.getAbsoluteX() + 1 + font.getStringWidth("Z PB: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
        });
    }

}
