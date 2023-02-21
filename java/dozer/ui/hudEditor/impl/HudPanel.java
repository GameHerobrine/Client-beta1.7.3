package dozer.ui.hudEditor.impl;

import dozer.ui.clickGui.impl.Widget;

import java.awt.*;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class HudPanel extends Widget<Class<HudPanel>> {

    public LinkedList<HudButton> hudButtons = new LinkedList<>();

    public HudPanel(int x, int y, int width, int height, Color color) {
        super(HudPanel.class, x, y, width, height, color);

        AtomicInteger count = new AtomicInteger();

        int startY = y + height;


        getDozer().getHudManager().getHudModules().forEach(module -> {
            hudButtons.add(new HudButton(module, x, startY + (height * count.get()), width, height, color));
            count.getAndIncrement();
        });
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        drawRect(x, y, width + x, y + height, color);
        drawStringWithShadow("Hud Editor", x + 2, y + 2, Color.WHITE);


        hudButtons.forEach(hudButton -> hudButton.drawScreen(mouseX, mouseY, partialTicks));
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        hudButtons.forEach(hudButton -> hudButton.mouseClicked(mouseX, mouseY, mouseButton));

    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        hudButtons.forEach(hudButton -> hudButton.mouseReleased(mouseX, mouseY, state));
    }

    @Override
    public void keyTyped(char character, int keyCode) {
        hudButtons.forEach(hudButton -> hudButton.keyTyped(character, keyCode));
    }
}
