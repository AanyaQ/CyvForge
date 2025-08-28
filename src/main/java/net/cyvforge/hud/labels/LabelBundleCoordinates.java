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

public class LabelBundleCoordinates extends LabelBundle {

    public LabelBundleCoordinates() {
        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelFPS";}
            public String getDisplayName() {return "FPS";}
            public int getWidth() {return getLabelWidth(this.getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(0, 1);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;
                drawString("FPS: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(Minecraft.getDebugFPS(), pos.getAbsoluteX() + 1 + font.getStringWidth("FPS: ")
                        , pos.getAbsoluteY() + 1, color2);
            }
            public void renderDummy(ScreenPosition pos) {
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;
                drawString("FPS: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString("360", pos.getAbsoluteX() + 1 + font.getStringWidth("FPS: ")
                        , pos.getAbsoluteY() + 1, color2);
            }
        });

        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelX";}
            public String getDisplayName() {return "X Coord";}
            public int getWidth() {return getLabelWidth(this.getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(0, 10);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;
                DecimalFormat df = CyvForge.df;
                String x = df.format(ParkourTickListener.x);
                drawString("X: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(x, pos.getAbsoluteX() + 1 + font.getStringWidth("X: ")
                        , pos.getAbsoluteY() + 1, color2);
            }
            public void renderDummy(ScreenPosition pos) {
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;
                StringBuilder str = new StringBuilder("0.");
                for (int i=0; i<CyvClientConfig.getInt("df",5); i++) str.append("0");
                drawString("X: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(str.toString(), pos.getAbsoluteX() + 1 + font.getStringWidth("X: ")
                        , pos.getAbsoluteY() + 1, color2);
            }
        });

        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelY";}
            public String getDisplayName() {return "Y Coord";}
            public int getWidth() {return getLabelWidth(this.getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(0, 19);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;
                DecimalFormat df = CyvForge.df;
                String y = df.format(ParkourTickListener.y);
                drawString("Y: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(y, pos.getAbsoluteX() + 1 + font.getStringWidth("Y: ")
                        , pos.getAbsoluteY() + 1, color2);
            }
            public void renderDummy(ScreenPosition pos) {
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;
                StringBuilder str = new StringBuilder("0.");
                for (int i=0; i<CyvClientConfig.getInt("df",5); i++) str.append("0");
                drawString("Y: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(str.toString(), pos.getAbsoluteX() + 1 + font.getStringWidth("Y: ")
                        , pos.getAbsoluteY() + 1, color2);
            }
        });

        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelZ";}
            public String getDisplayName() {return "Z Coord";}
            public int getWidth() {return getLabelWidth(this.getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(0, 28);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;
                DecimalFormat df = CyvForge.df;
                String z = df.format(ParkourTickListener.z);
                drawString("Z: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(z, pos.getAbsoluteX() + 1 + font.getStringWidth("Z: ")
                        , pos.getAbsoluteY() + 1, color2);
            }
            public void renderDummy(ScreenPosition pos) {
                int d = CyvClientConfig.getInt("df",5);
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;
                StringBuilder str = new StringBuilder("0.");
                for (int i=0; i<CyvClientConfig.getInt("df",5); i++) str.append("0");
                drawString("Z: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(str.toString(), pos.getAbsoluteX() + 1 + font.getStringWidth("Z: ")
                        , pos.getAbsoluteY() + 1, color2);
            }
        });

        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelYaw";}
            public String getDisplayName() {return "Yaw";}
            public int getWidth() {return getLabelWidth(this.getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(0, 37);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;
                DecimalFormat df = CyvForge.df;
                String f;
                if (/*frameBasedFacing*/false) {
                    f = df.format(ParkourTickListener.formatYaw(mc.thePlayer.rotationYaw));
                } else {
                    f = (df.format((ParkourTickListener.lastTick == null) ? 0 : ParkourTickListener.formatYaw(ParkourTickListener.lastTick.f)))+"\u00B0";
                }

                drawString("F: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(f, pos.getAbsoluteX() + 1 + font.getStringWidth("F: ")
                        , pos.getAbsoluteY() + 1, color2);

                if (/*ModCoordinates.this.showAxis*/ false) {
                    float absFacing = Math.abs(ParkourTickListener.formatYaw(ParkourTickListener.lastTick.f));
                    drawString(((absFacing > 45 && absFacing < 135) ? " X" : " Z")
                            ,pos.getAbsoluteX() + 1 + font.getStringWidth("F: " + f), pos.getAbsoluteY() + 1, color1);
                }

            }
            public void renderDummy(ScreenPosition pos) {
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;
                StringBuilder str = new StringBuilder("0.");
                for (int i=0; i<CyvClientConfig.getInt("df",5); i++) str.append("0");
                drawString("F: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(str+"\u00B0", pos.getAbsoluteX() + 1 + font.getStringWidth("F: ")
                        , pos.getAbsoluteY() + 1, color2);

                if (/*ModCoordinates.this.showAxis*/ false) {
                    drawString(" Z",pos.getAbsoluteX() + 1 + font.getStringWidth("F: " + str+"\u00B0"), pos.getAbsoluteY() + 1, color1);
                }
            }
        });

        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelPitch";}
            public String getDisplayName() {return "Pitch";}
            public int getWidth() {return getLabelWidth(this.getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(0, 46);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;
                DecimalFormat df = CyvForge.df;
                String p;
                if (/*frameBasedFacing*/ false) {
                    p = df.format(ParkourTickListener.formatYaw(mc.thePlayer.rotationPitch));
                } else {
                    p = df.format((ParkourTickListener.lastTick == null) ? 0 : ParkourTickListener.lastTick.p)+"\u00B0";
                }
                drawString("Pitch: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(p, pos.getAbsoluteX() + 1 + font.getStringWidth("Pitch: ")
                        , pos.getAbsoluteY() + 1, color2);

            }
            public void renderDummy(ScreenPosition pos) {
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;
                StringBuilder str = new StringBuilder("0.");
                for (int i=0; i<CyvClientConfig.getInt("df",5); i++) str.append("0");
                drawString("Pitch: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(str+"\u00B0", pos.getAbsoluteX() + 1 + font.getStringWidth("Pitch: ")
                        , pos.getAbsoluteY() + 1, color2);
            }
        });
    }

}
