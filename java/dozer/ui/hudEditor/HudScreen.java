package dozer.ui.hudEditor;

import dozer.systems.hud.HudElement;
import dozer.ui.hudEditor.impl.HudElementWidget;
import dozer.ui.hudEditor.impl.HudPanel;
import dozer.util.ClientUtil;
import net.minecraft.src.gui.GuiScreen;

import java.awt.*;
import java.util.LinkedList;

public class HudScreen extends GuiScreen implements ClientUtil {

    public HudPanel hudPanel;
    LinkedList<HudElementWidget> hudElements = new LinkedList<>();

    public HudScreen() {

        hudPanel = new HudPanel(150, 40, 100, 14, new Color(253, 169, 0));

        getDozer().getHudManager().getHudModules().forEach(hudElement -> {
            hudElements.add(new HudElementWidget(hudElement, hudElement.getX(), hudElement.getY(), hudElement.getWidth(), hudElement.getHeight(), new Color(35, 35, 35, 180)));
            hudElement.setDimensions(hudElement.getWidth(), hudElement.getHeight());
        });
    }


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        hudPanel.drawScreen(mouseX, mouseY, partialTicks);

        hudElements.forEach(hudElementWidget -> hudElementWidget.drawScreen(mouseX, mouseY, partialTicks));

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void keyTyped(char character, int keyCode) {

        hudPanel.keyTyped(character, keyCode);

        hudElements.forEach(hudElementWidget -> hudElementWidget.keyTyped(character, keyCode));

        super.keyTyped(character, keyCode);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {


        hudPanel.mouseClicked(mouseX, mouseY, mouseButton);

        hudElements.forEach(hudElementWidget -> hudElementWidget.mouseClicked(mouseX, mouseY, mouseButton));

        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {

        hudPanel.mouseReleased(mouseX, mouseY, state);

        hudElements.forEach(hudElementWidget -> hudElementWidget.mouseReleased(mouseX, mouseY, state));

        super.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
