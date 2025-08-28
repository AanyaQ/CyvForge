package net.cyvforge.gui;

import mcpk.Parser;
import mcpk.Player;
import net.cyvforge.CyvForge;
import net.cyvforge.util.defaults.CyvGui;
import net.cyvforge.util.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiTextField;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

//gui for the in-game movement simulator
public class GuiSimulate extends CyvGui {
    public static ArrayList<String> chatHistory = new ArrayList<>();

    GuiTextField input;
    SubButton button;
    int chatHistoryIndex = 0;

    public GuiSimulate() {
        super("Movement Simulator");
    }

    @Override
    public void initGui() {
        super.initGui();
        System.out.println(width + " " + height);
        input = new GuiTextField(0, fontRendererObj, width/2 - 184, height / 2 - 45, 368, 20);
        button = new SubButton("Calculate", width/2-50, height*3/5-10, 100, 15);
        button.setEnabled(true);

        input.setMaxStringLength(65536);
        this.chatHistoryIndex = 0;
        this.input.setFocused(true);

        Keyboard.enableRepeatEvents(true);

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawDefaultBackground(); //background tint
        GuiUtils.drawRoundedRect(width / 2 - 200, height / 2 - 65, width / 2 + 200, height / 2,
                7, CyvForge.theme.background1); //black box

        input.drawTextBox();

        GuiUtils.drawRoundedRect(width/2-54, height*3/5-14, width/2+54, height*3/5+9, 5, CyvForge.theme.background1);
        button.draw(mouseX, mouseY);
        GuiUtils.drawCenteredString("Movement Simulator", width / 2 - 150, height / 2 - 80, 0xFFFFFFFF, true);
    }

    public void updateScreen() {
        input.updateCursorCounter();
        super.updateScreen();
    }

    public void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == Keyboard.KEY_RETURN) {
            Minecraft.getMinecraft().thePlayer.closeScreen(); //close the gui
            String text = input.getText(); //parser shit

            if (text.isEmpty() || text.equals(" ")) {

            } else {
                if (chatHistory.isEmpty()) {
                    chatHistory.add(text);

                } else {
                    if (!chatHistory.get(chatHistory.size()-1).equals(text)) {
                        chatHistory.add(text);
                    }
                }

                output(text);
                return;
            }

            return;
        } else if (keyCode == 200) { //scroll up
            if (chatHistoryIndex < chatHistory.size()) {
                chatHistoryIndex++;
                input.setText(chatHistory.get(chatHistory.size()-chatHistoryIndex));
                return;
            }

        } else if (keyCode == 208) { //scroll down
            if (chatHistoryIndex > 0) {
                chatHistoryIndex--;
                if (chatHistoryIndex == 0) {
                    input.setText("");
                } else {
                    input.setText(chatHistory.get(chatHistory.size()-chatHistoryIndex));
                }
                return;
            }

        }

        input.textboxKeyTyped(typedChar, keyCode);
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        input.mouseClicked(mouseX, mouseY, mouseButton);
        if (button.clicked(mouseX, mouseY, mouseButton)) {
            Minecraft.getMinecraft().thePlayer.closeScreen(); //close the gui
            String text = input.getText(); //parser shit

            if (text.isEmpty() || text.equals(" ")) {
            } else {
                if (chatHistory.isEmpty()) {
                    chatHistory.add(text);

                } else {
                    if (!chatHistory.get(chatHistory.size()-1).equals(text)) {
                        chatHistory.add(text);
                    }
                }
                output(text);
            }}
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    private void output(String text) {
        new Thread(() -> {
            Player player = new Player();
            DecimalFormat df = CyvForge.df;
            Player.df = (byte) df.getMaximumFractionDigits();
            Parser parser = new Parser();

            try {
                parser.parse(player, text);
            } catch (Exception e) {
                CyvForge.sendChatMessage("Parsing failed.");
                return;
            }

            double z = player.z;
            double vz = player.vz;
            double x = player.x;
            double vx = player.vx;

            double vector = Math.sqrt(vx*vx + vz*vz);
            double angle = Math.atan2(-vx,vz) * 180d/Math.PI;

            CyvForge.sendChatMessage("Simulated parsed string: \247o" + text + "\n\247r"
                    + "z: " + df.format(z) + "\n"
                    + "vz: " + df.format(vz) + "\n"
                    + "x: " + df.format(x) + "\n"
                    + "vx: " + df.format(vx) + "\n"
                    + "Speed Vector: " + df.format(vector) + ", " + df.format(angle) + "°");
        }).start();

    }

    @Override
    public void handleKeyboardInput() throws IOException {
        super.handleKeyboardInput();

    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
        Keyboard.enableRepeatEvents(false);
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();

    }

    @Override
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);

    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);

    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
