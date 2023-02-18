package dozer.ui.clickGui.impl;

import dozer.Dozer;
import dozer.systems.module.ModuleCategory;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CategoryPanel extends Widget<ModuleCategory> {

    public List<ModuleButton> buttons = new ArrayList<>();
    private boolean extended = true, dragging;
    private int startingX, startingY;

    public CategoryPanel(ModuleCategory category, int x, int y, int width, int height, Color color) {
        super(category, x, y, width, height, color);

        AtomicInteger count = new AtomicInteger(1);

        Dozer.getSingleton().getModuleManager().getModulesByCategory(category).forEach(module -> {
            buttons.add(new ModuleButton(module, x, y + (height * count.get()), width, height, color));
            count.incrementAndGet();
        });
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        if(dragging) {
            x = mouseX + startingX;
            y = mouseY + startingY;
        }

        drawRect(x, y, x + width, y + height, color);
        drawStringWithShadow(type.getName(), x + 2,  y + 2, Color.WHITE);
        drawStringWithShadow(extended ? "-" : "+", x + width - 10,  y + 3, Color.WHITE);

        if (!extended) return;
        int count = 0;

        for(ModuleButton button : buttons) {
            button.x = x;
            button.y = y + height + count;
            count += button.finalHeight;
            button.drawScreen(mouseX, mouseY, partialTicks);
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (extended) {
            for (ModuleButton button : buttons) {
                button.mouseClicked(mouseX, mouseY, mouseButton);
            }
        }

        if(isHovering(mouseX, mouseY)) {
            if (mouseButton == 0) {
                this.dragging = true;
            }
            if (mouseButton == 1) {
                extended = !extended;
            }
        }

        if(dragging) {
            startingX = x - mouseX;
            startingY = y - mouseY;
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        this.dragging = false;
        if (extended) {
            for (ModuleButton button : buttons) {
                button.mouseReleased(mouseX, mouseY, state);
            }
        }
    }

    @Override
    public void keyTyped(char character, int keyCode) {
        if (extended) {
            for (ModuleButton button : buttons) {
                button.keyTyped(character, keyCode);
            }
        }
    }


}
