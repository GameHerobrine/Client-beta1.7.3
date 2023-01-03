package dozer.ui.hudEditor.impl;

import dozer.systems.hud.HudElement;
import dozer.ui.clickGui.impl.Widget;

import java.awt.*;

public class HudElementWidget extends Widget<HudElement> {

    boolean dragged;
    int startingX, startingY;

    public HudElementWidget(HudElement type, int x, int y, int width, int height, Color color) {
        super(type, x, y, width, height, color);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        if(dragged) {
            type.setX(mouseX + startingX);
            type.setY(mouseY + startingY);

            x = mouseX + startingX;
            y = mouseY + startingY;
        }

        x = type.getX() - 2;
        y = type.getY() - 2;
        width = type.getWidth() + 2;
        height = type.getHeight() + 2;
        

        if(type.isToggled()) {
          drawRect(x, y, width + x, y + height, color);
          type.render();
        }

    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if(isHovering(mouseX, mouseY)) {
            if(mouseButton == 0) {
                dragged = true;
                startingX = x - mouseX;
                startingY = y - mouseY;
            }
        }

    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        dragged = false;
    }

    @Override
    public void keyTyped(char character, int keyCode) {

    }
}
