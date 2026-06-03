package net.cyvforge.gui;

import net.cyvforge.CyvForge;
import net.cyvforge.config.ColorTheme;
import net.cyvforge.event.ConfigLoader;
import net.cyvforge.hud.HUDManager;
import net.cyvforge.hud.structure.DraggableHUDElement;
import net.cyvforge.util.defaults.CyvGui;
import net.cyvforge.util.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiTextField;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.ArrayList;

public class GuiMPK extends CyvGui {
    int sizeX = 100;
    int sizeY = 250;

    ArrayList<LabelLine> labelLines;
    int selectedIndex = -1;

    float vScroll = 0;
    float scroll = 0;
    int maxScroll = 0;
    boolean scrollClicked = false;

    GuiTextField searchBar;
    SubButton guiEditButton;
    SubButton settingsButton;
    SubButton macroButton;

    public GuiMPK() {
        super("MPK Gui");
    }

    @Override
    public void onResize(Minecraft mcIn, int w, int h) {
        mc.displayGuiScreen(null);
    }

    @Override
    public void initGui() {
        this.labelLines = new ArrayList<>();
        this.guiEditButton = new SubButton("Edit Positions", sr.getScaledWidth() / 2 + sizeX / 2 + 50,
                sr.getScaledHeight() / 2 - sizeY / 2, 100, 15);
        this.guiEditButton.setEnabled(true);

        this.settingsButton = new SubButton("Settings", sr.getScaledWidth() / 2 + sizeX / 2 + 50,
                sr.getScaledHeight() / 2 - sizeY / 2 + 20, 100, 15);
        this.settingsButton.setEnabled(true);

        this.macroButton = new SubButton("Open Macro", sr.getScaledWidth() / 2 + sizeX / 2 + 50,
                sr.getScaledHeight() / 2 - sizeY / 2 + 40, 100, 15);
        this.macroButton.setEnabled(Minecraft.getMinecraft().isSingleplayer());

        this.updateLabels(false);

        maxScroll = (int) Math.max(0, Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT * 2 * Math.ceil(labelLines.size()) - (sizeY-20));
        if (scroll > maxScroll) scroll = maxScroll;
        if (scroll < 0) scroll = 0;

        this.searchBar = new GuiTextField(0, Minecraft.getMinecraft().fontRendererObj,
                sr.getScaledWidth()/2-sizeX/2 - 12,
                sr.getScaledHeight()/2-sizeY/2 - 10 - Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT,
                75,
                Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT) {
            @Override
            public boolean textboxKeyTyped(char p_146201_1_, int p_146201_2_) {
                if (super.textboxKeyTyped(p_146201_1_, p_146201_2_)) {
                    updateLabels(true);
                    return true;
                } else {
                    return false;
                }
            }
        };
        this.searchBar.setEnableBackgroundDrawing(false);
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == Keyboard.KEY_ESCAPE) { //exit the gui
            if (this.searchBar.isFocused()) {
                this.searchBar.setFocused(false);
                this.searchBar.setText("");
                updateLabels(true);
            } else this.mc.displayGuiScreen(null);
        } else {
            if (!this.searchBar.isFocused()) this.searchBar.setFocused(true);
            this.searchBar.textboxKeyTyped(typedChar, keyCode);
        }
    }

    public void updateLabels(boolean fromSearch) {
        this.labelLines.clear();

        for (DraggableHUDElement l : HUDManager.registeredRenderers) {
            if (l.getDisplayName() == null || l.getDisplayName().trim().isEmpty()) {
                continue;
            }

            if (!fromSearch || l.getDisplayName().toLowerCase().contains(this.searchBar.getText().toLowerCase())
                    || l.getName().toLowerCase().contains(this.searchBar.getText().toLowerCase()))
                labelLines.add(new LabelLine(l));
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawDefaultBackground();

        maxScroll = (int) Math.max(0, Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT * 2 * Math.ceil(labelLines.size()) - (sizeY-20));
        if (scroll > maxScroll) scroll = maxScroll;
        if (scroll < 0) scroll = 0;

        // draw main background
        GuiUtils.drawRoundedRect(sr.getScaledWidth()/2 - sizeX/2 - 15, sr.getScaledHeight()/2 - sizeY/2 - 4,
                sr.getScaledWidth()/2 + sizeX/2 + 14, sr.getScaledHeight()/2 + sizeY/2 + 4, 5, CyvForge.theme.background1);

        int centerx = sr.getScaledWidth() * sr.getScaleFactor() / 2;
        int centery = sr.getScaledHeight() * sr.getScaleFactor() / 2;
        int scaleFactor = sr.getScaleFactor();

        GuiUtils.drawCenteredString("Labels:", sr.getScaledWidth()/2, 5 + sr.getScaledHeight()/2 - sizeY/2, 0xFFFFFFFF, true);

        // draw side button background
        final int BUTTON_X = sr.getScaledWidth() / 2 + sizeX / 2 + 50;
        final int BUTTON_SIZE = 100;
        final int BUTTON_COUNT = 3;
        GuiUtils.drawRoundedRect(BUTTON_X - 4, sr.getScaledHeight()/2 - sizeY/2 - 4,
                BUTTON_X + BUTTON_SIZE + 4, sr.getScaledHeight()/2 - sizeY/2 + BUTTON_COUNT * 20,
                5, CyvForge.theme.background1);

        // draw buttons
        this.guiEditButton.draw(mouseX, mouseY);
        this.settingsButton.draw(mouseX, mouseY);
        this.macroButton.draw(mouseX, mouseY);

        //draw searchbar
        ColorTheme theme = CyvForge.theme;
        boolean isHovered = this.searchBar.isFocused() ||
                (mouseX > searchBar.xPosition - 3 &&
                        mouseX < searchBar.xPosition + searchBar.width  + 3&&
                        mouseY > searchBar.yPosition - 3.5 &&
                        mouseY < searchBar.yPosition + searchBar.height + 2.5);

        GuiUtils.drawRoundedRect(searchBar.xPosition - 3,
                searchBar.yPosition - 3.5f,
                searchBar.xPosition + searchBar.width + 3,
                searchBar.yPosition + searchBar.height + 2.5f,
                2, theme.background1);
        GuiUtils.drawRoundedRect(searchBar.xPosition - 1.5f,
                searchBar.yPosition - 2,
                searchBar.xPosition + searchBar.width + 1.5f,
                searchBar.yPosition + searchBar.height + 1f,
                2, isHovered ? theme.main2 : theme.secondary1);
        GuiUtils.drawRoundedRect(searchBar.xPosition - 1.5f,
                searchBar.yPosition - 2,
                searchBar.xPosition + searchBar.width + 1.5f,
                searchBar.yPosition + searchBar.height + 1,
                2, theme.highlight);
        if (!this.searchBar.isFocused() && this.searchBar.getText().length() == 0) {
            GuiUtils.drawString("Search", searchBar.xPosition + 16,
                    searchBar.yPosition + 0.5f,
                    0xFFFFFFFF, true);
        }

        this.searchBar.drawTextBox();

        GL11.glScissor(centerx - ((sizeX + 10)*scaleFactor/2),
                centery - (sizeY*scaleFactor/2) + 3,
                sizeX*scaleFactor, sizeY*scaleFactor - (fontRendererObj.FONT_HEIGHT * scaleFactor * 2));
        GL11.glEnable(GL11.GL_SCISSOR_TEST);


        int index = 0;
        for (LabelLine l : labelLines) {
            int yHeight = (int) ((index + 1) * mc.fontRendererObj.FONT_HEIGHT*2 - scroll + (sr.getScaledHeight()/2 - sizeY/2));
            l.drawEntry(index, (int) scroll, mouseX, mouseY, index == this.selectedIndex);
            index++;
        }

        GL11.glDisable(GL11.GL_SCISSOR_TEST);

        //draw scrollbar
        int scrollbarHeight = (int) ((sizeY - 8 - 15)/(0.01*maxScroll+1));
        if (scroll > maxScroll) scroll = maxScroll;
        if (scroll < 0) scroll = 0;

        int top = sr.getScaledHeight()/2-sizeY/2+4+15;
        int bottom = sr.getScaledHeight()/2+sizeY/2-4 - scrollbarHeight;
        int amount = (int) (top + (bottom - top) * ((float) scroll/maxScroll));
        if (maxScroll == 0) amount = top;

        int color = CyvForge.theme.border2;
        if (mouseX > sr.getScaledWidth()/2+sizeX/2+2 && mouseX < sr.getScaledWidth()/2+sizeX/2+8 &&
                mouseY > amount && mouseY < amount+scrollbarHeight) {
            color = CyvForge.theme.border1;
        }

        GuiUtils.drawRoundedRect(sr.getScaledWidth()/2+sizeX/2+2, amount,
                sr.getScaledWidth()/2+sizeX/2+8, amount+scrollbarHeight, 3, color);

    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();

        int eventDWheel = Mouse.getDWheel();
        if ((!scrollClicked || !Mouse.isButtonDown(0)) && eventDWheel != 0) {
            vScroll -= eventDWheel * 0.03;
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseEvent) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseEvent);

        int scrollbarHeight = (int) ((sizeY - 8)/(0.01*maxScroll+1));
        int top = sr.getScaledHeight()/2-sizeY/2+4+15;
        int bottom = sr.getScaledHeight()/2+sizeY/2-4 - scrollbarHeight;
        int amount = (int) (top + (bottom - top) * ((float) scroll/maxScroll));
        if (maxScroll == 0) amount = top;

        // check scrollbar
        if (mouseX > sr.getScaledWidth()/2+sizeX/2+2 && mouseX < sr.getScaledWidth()/2+sizeX/2+8 &&
                mouseY > amount && mouseY < amount+scrollbarHeight) {
            this.scrollClicked = true;
            return;
        } else {
            this.scrollClicked = false;
        }

        // check searchbar
        this.searchBar.mouseClicked(mouseX, mouseY, mouseEvent);
        if (this.searchBar.isFocused()) {
            updateLabels(true);
            return;
        }

        // check buttons
        if (this.guiEditButton.clicked(mouseX, mouseY, mouseEvent)) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiHUDPositions(true));
            return;
        } else if (this.settingsButton.clicked(mouseX, mouseY, mouseEvent)) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiModConfig(true));
            return;
        } else if (this.macroButton.clicked(mouseX, mouseY, mouseEvent)) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiMacro());
            return;
        }

        // check labels
        if (mouseX < sr.getScaledWidth()/2-this.sizeX/2 || mouseX > sr.getScaledWidth()/2+this.sizeX/2
                || mouseY < sr.getScaledHeight()/2-this.sizeY/2 || mouseY > sr.getScaledHeight()/2+this.sizeY/2) {
            return;
        }

        int index=0;
        for (LabelLine l : labelLines) {
            if (l.isPressed(index, mouseX, mouseY, mouseEvent)) {
                l.mouseClicked(index, mouseX, mouseY, mouseEvent);
                return;
            }
            index++;
        }

    }

    @Override
    public void mouseClickMove(int x, int y, int mouseButton, long time) {
        if (this.scrollClicked) {
            int scrollbarHeight = (int) ((sizeY - 8)/(0.01*maxScroll+1));
            int top = sr.getScaledHeight()/2-sizeY/2+4+15;
            int bottom = sr.getScaledHeight()/2+sizeY/2-4 - scrollbarHeight;

            scroll = (int) ((float) (y - (sr.getScaledHeight()/2-this.sizeY/2+15) - scrollbarHeight/2) /(bottom - top) * maxScroll);

            if (scroll > maxScroll) scroll = maxScroll;
            if (scroll < 0) scroll = 0;
        }

    }

    @Override
    public void updateScreen() {
        this.searchBar.updateCursorCounter();

        //smooth scrolling
        this.scroll += this.vScroll;
        this.vScroll *= 0.75;

    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
        ConfigLoader.save(CyvForge.config, false);
    }

    class LabelLine {
        DraggableHUDElement label;
        int xStart = sr.getScaledWidth()/2 - sizeX/2 - 5;
        int width = sizeX;
        int height = fontRendererObj.FONT_HEIGHT*2;

        public LabelLine(DraggableHUDElement label) {
            this.label = label;
        }

        public void drawEntry(int slotIndex, int scroll, int mouseX, int mouseY, boolean isSelected) {
            int yHeight = (slotIndex + 1) * fontRendererObj.FONT_HEIGHT*2 - scroll + (sr.getScaledHeight()/2 - sizeY/2);
            GuiUtils.drawRoundedRect(xStart, yHeight + 1,
                    xStart + width, yHeight + height - 1,
                    3, label.isEnabled ? CyvForge.theme.shade2 : CyvForge.theme.secondary1);

            GuiUtils.drawString(label.getDisplayName(), xStart + 4, yHeight + height/3, 0xFFFFFFFF, true);

        }

        public boolean isPressed(int slotIndex, int mouseX, int mouseY, int mouseEvent) {
            float yHeight = (slotIndex + 1) * fontRendererObj.FONT_HEIGHT*2 - scroll + (sr.getScaledHeight()/2 - sizeY/2);
            if (mouseX > xStart && mouseX < xStart + width && mouseY > yHeight && mouseY < yHeight + height) {
                return true;
            }

            return false;
        }

        public void mouseClicked(int slotIndex, int mouseX, int mouseY, int mouseEvent) {
            label.setEnabled(!label.isEnabled);
        }

    }

}
