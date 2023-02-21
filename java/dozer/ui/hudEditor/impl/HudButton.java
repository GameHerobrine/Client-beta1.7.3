package dozer.ui.hudEditor.impl;

import dozer.systems.hud.HUDElement;
import dozer.ui.clickGui.impl.Widget;

import java.awt.*;

public class HudButton extends Widget<HUDElement> {

    public HudButton(HUDElement hudElement, int x, int y, int width, int height, Color color) {
        super(hudElement, x, y, width, height, color);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawRect(x, y, width + x,  y + height, type.isToggled() ? color : new Color(25, 25, 25, 180));
        drawStringWithShadow(type.getName(), x + 2, y + 2, Color.WHITE);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if(!isHovering(mouseX, mouseY)) return;
        type.setToggled(!type.isToggled());
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {

    }

    @Override
    public void keyTyped(char character, int keyCode) {

    }
}
