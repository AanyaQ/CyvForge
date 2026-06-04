package net.cyvforge.gui;

import net.cyvforge.CyvForge;
import net.cyvforge.config.CyvClientConfig;
import net.cyvforge.util.GuiUtils;
import net.cyvforge.util.defaults.CyvGui;
import net.cyvforge.util.parkour.LandingAxis;
import net.cyvforge.util.parkour.LandingBlock;
import net.cyvforge.util.parkour.LandingMode;
import net.minecraft.client.Minecraft;

import java.io.IOException;

public class GuiLb extends CyvGui {
    LandingBlock lb;
    SubButton landingModeButton;
    SubButton axisButton;
    SubButton bbVisibleButton;
    SubButton condVisibleButton;
    SubButton calculateWalls;
    SubButton resetWalls;
    SubButton neoAndBlock;

    public GuiLb(LandingBlock b) {
        super("Landing Block GUI");
        this.lb = b;
        mc = Minecraft.getMinecraft();
    }

    @Override
    public void initGui() {
        if (lb == null) {
            CyvForge.sendChatMessage("NULL LB");
            mc.displayGuiScreen(null);
        }

        // init buttons
        this.landingModeButton = new SubButton("Landing Mode: " + lb.mode.toString(), this.width - 160, 10, 150, 20);
        this.axisButton = new SubButton( "Axis: " + lb.axis.toString(), this.width - 160, 35, 150, 20);
        this.bbVisibleButton = new SubButton( "BB Visible: " + CyvClientConfig.getBoolean("highlightLanding", false), this.width - 160, 60, 150, 20);
        this.condVisibleButton = new SubButton( "Cond Visible: " + CyvClientConfig.getBoolean("highlightLandingCond", false), this.width - 160, 85, 150, 20);
        this.calculateWalls = new SubButton("Calculate Walls", this.width - 160, 110, 150, 20);
        this.resetWalls = new SubButton("Reset Walls", this.width - 160, 135, 150, 20);
        this.neoAndBlock = new SubButton("Neo & Landing: " + lb.neoAndNormal, this.width - 160, 160, 150, 20);

        this.landingModeButton.setEnabled(true);
        this.axisButton.setEnabled(true);
        this.bbVisibleButton.setEnabled(true);
        this.condVisibleButton.setEnabled(true);
        this.calculateWalls.setEnabled(true);
        this.resetWalls.setEnabled(true);

        if (lb.axis.equals(LandingAxis.both)) {
            this.axisButton.setText("Axis: Both");
        } else if (lb.axis.equals(LandingAxis.z)) {
            this.axisButton.setText("Axis: Z");
        } else {
            lb.axis = LandingAxis.x;
            this.axisButton.setText("Axis: X");
        }

        if (lb.mode.equals(LandingMode.landing)) {
            this.landingModeButton.setText("Landing Mode: Landing");
        } else if (lb.mode.equals(LandingMode.hit)) {
            this.landingModeButton.setText("Landing Mode: Hit");
        } else if (lb.mode.equals(LandingMode.z_neo)) {
            this.landingModeButton.setText("Landing Mode: Z Neo");
        } else {
            lb.mode = LandingMode.enter;
            this.landingModeButton.setText("Landing Mode: Enter");
        }

        this.neoAndBlock.setEnabled(lb.mode == LandingMode.z_neo);

    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseEvent) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseEvent);

        if (this.landingModeButton.clicked(mouseX, mouseY, mouseEvent)) {
            LandingMode mode = lb.mode;
            if (mode.equals(LandingMode.landing)) {
                lb.mode = LandingMode.hit;
                this.landingModeButton.setText("Landing Mode: Hit");
            } else if (mode.equals(LandingMode.hit)) {
                lb.mode = LandingMode.z_neo;
                this.landingModeButton.setText("Landing Mode: Z Neo");
            } else if (mode.equals(LandingMode.z_neo)) {
                lb.mode = LandingMode.enter;
                this.landingModeButton.setText("Landing Mode: Enter");
            } else {
                lb.mode = LandingMode.landing;
                this.landingModeButton.setText("Landing Mode: Landing");
            }

            this.neoAndBlock.setEnabled(lb.mode == LandingMode.z_neo);
            return;
        }

        if (this.axisButton.clicked(mouseX, mouseY, mouseEvent)) {
            LandingAxis mode = lb.axis;
            if (mode.equals(LandingAxis.both)) {
                lb.axis = LandingAxis.z;
                this.axisButton.setText("Axis: Z");
            } else if (mode.equals(LandingAxis.z)) {
                lb.axis = LandingAxis.x;
                this.axisButton.setText("Axis: X");
            } else {
                lb.axis = LandingAxis.both;
                this.axisButton.setText("Axis: Both");
            }
            return;
        }

        if (this.bbVisibleButton.clicked(mouseX, mouseY, mouseEvent)) {
            CyvClientConfig.set("highlightLanding", !CyvClientConfig.getBoolean("highlightLanding", false));
            this.bbVisibleButton.setText("BB Visible: " + CyvClientConfig.getBoolean("highlightLanding", false));
            return;
        }

        if (this.condVisibleButton.clicked(mouseX, mouseY, mouseEvent)) {
            CyvClientConfig.set("highlightLandingCond", !CyvClientConfig.getBoolean("highlightLandingCond", false));
            this.condVisibleButton.setText("Cond Visible: " + CyvClientConfig.getBoolean("highlightLandingCond", false));
            return;
        }

        if (this.calculateWalls.clicked(mouseX, mouseY, mouseEvent)) {
            lb.calculateWalls();
            return;
        }

        if (this.resetWalls.clicked(mouseX, mouseY, mouseEvent)) {
            lb.resetWalls();
            return;
        }

        if (this.neoAndBlock.clicked(mouseX, mouseY, mouseEvent)){
            lb.neoAndNormal = !lb.neoAndNormal;
            this.neoAndBlock.setText("Neo & Landing: " + lb.neoAndNormal);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawDefaultBackground();

        // draw background
        final int BUTTON_X = this.width - 160;
        final int BUTTON_SIZE = 150;
        final int BUTTON_COUNT = 7;
        GuiUtils.drawRoundedRect(BUTTON_X - 4, 6,
                BUTTON_X + BUTTON_SIZE + 4, 10 + BUTTON_COUNT * 25,
                5, CyvForge.theme.background1);

        // draw buttons
        this.landingModeButton.draw(mouseX, mouseY);
        this.axisButton.draw(mouseX, mouseY);
        this.bbVisibleButton.draw(mouseX, mouseY);
        this.condVisibleButton.draw(mouseX, mouseY);
        this.calculateWalls.draw(mouseX, mouseY);
        this.resetWalls.draw(mouseX, mouseY);
        this.neoAndBlock.draw(mouseX, mouseY);
    }

    @Override
    public void updateScreen() {
        if (lb == null) mc.displayGuiScreen(null);
    }

}