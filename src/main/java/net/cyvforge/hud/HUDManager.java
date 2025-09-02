package net.cyvforge.hud;

import net.cyvforge.CyvForge;
import net.cyvforge.command.mpk.CommandMacro;
import net.cyvforge.gui.GuiMPK;
import net.cyvforge.gui.GuiModConfig;
import net.cyvforge.hud.labels.*;
import net.cyvforge.hud.nonlabels.DirectionHUD;
import net.cyvforge.hud.nonlabels.KeystrokesHUD;
import net.cyvforge.hud.nonlabels.TogglesprintHUD;
import net.cyvforge.hud.nonlabels.TurnHUD;
import net.cyvforge.hud.structure.DraggableHUDElement;
import net.cyvforge.hud.structure.ScreenPosition;
import net.cyvforge.util.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.settings.GameSettings;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.List;

public class HUDManager {
    public static List<DraggableHUDElement> registeredRenderers = new ArrayList<DraggableHUDElement>();
    private static Minecraft mc = Minecraft.getMinecraft();
    public HUDManager instance;

    public HUDManager() { //initialize and create eventlistener
        instance = this;
        registeredRenderers.add(new DirectionHUD());
        registeredRenderers.add(new TogglesprintHUD());
        registeredRenderers.add(new KeystrokesHUD());
        registeredRenderers.add(new TurnHUD());

        registeredRenderers.addAll(new LabelBundleCoordinates().labels);
        registeredRenderers.addAll(new LabelBundleHitCoords().labels);
        registeredRenderers.addAll(new LabelBundleJumpCoords().labels);
        registeredRenderers.addAll(new LabelBundleLandingCoords().labels);
        registeredRenderers.addAll(new LabelBundleLandingPB().labels);
        registeredRenderers.addAll(new LabelBundleLasts().labels);
        registeredRenderers.addAll(new LabelBundleMomentumOffsets().labels);
        registeredRenderers.addAll(new LabelBundleSpeedVector().labels);
        registeredRenderers.addAll(new LabelBundleSpeeds().labels);
        registeredRenderers.addAll(new LabelBundleTickTimings().labels);
        registeredRenderers.addAll(new LabelBundleTurningAngles().labels);
        registeredRenderers.addAll(new LabelBundleHitExtras().labels);
        registeredRenderers.addAll(new LabelBundleBlipsGrinds().labels);
        registeredRenderers.add(new LabelTime());

    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Pre.Chat e) {
        render(e.partialTicks);
    }


    public void render(float partialTicks) {
        if (mc.gameSettings.hideGUI) return;

        if (CommandMacro.macroRunning > 0) { //macrorunning
            ScaledResolution sr = new ScaledResolution(mc);
            GuiUtils.drawString("MACRO",
                    sr.getScaledWidth()/2 - mc.fontRendererObj.getStringWidth("MACRO") / 2,
                    sr.getScaledHeight()/5, 0xFFFF0000, false);
        }

        if (mc.currentScreen == null || mc.currentScreen instanceof GuiContainer ||
                mc.currentScreen instanceof GuiChat || mc.currentScreen instanceof GuiModConfig
                || mc.currentScreen instanceof GuiMPK) {

            for (DraggableHUDElement renderer : registeredRenderers) {
                if (mc.currentScreen instanceof GuiContainer && !renderer.renderInGui()) continue;
                if (mc.currentScreen instanceof GuiChat && !renderer.renderInChat()) continue;

                GameSettings gameSettings = mc.gameSettings;
                if (gameSettings.showDebugInfo && !renderer.renderInOverlay()) continue;

                callRenderer(renderer, partialTicks);
            }
        }
    }

    private static void callRenderer(DraggableHUDElement renderer, float partialTicks) {
        if (!renderer.isEnabled) return;
        if (!renderer.isVisible) return;

        ScreenPosition pos = renderer.load();

        if (pos == null) {
            pos = renderer.getDefaultPosition();
        }

        renderer.render(pos);

    }

}
