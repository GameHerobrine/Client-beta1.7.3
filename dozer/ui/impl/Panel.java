package dozer.ui.impl;

import dozer.Dozer;
import dozer.module.Module;
import dozer.module.ModuleCategory;
import dozer.ui.ClickGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.src.MathHelper;
import net.minecraft.src.gui.Gui;

import java.awt.*;
import java.util.ArrayList;

public class Panel {

    private final ModuleCategory category;
    public ArrayList<Button> buttons = new ArrayList<Button>();
    public double x;
    public double y;
    public int height = 14;
    public int width = 100;

    public boolean extended = true;
    public boolean dragging = false;

    private boolean setValues = false;
    private double startingX, startingY;

    public Panel(double x, double y, ModuleCategory category, ClickGUI clickGUI) {
        this.category = category;
        this.x = x;
        this.y = y;

        int count = 0;
        for (Module module : Dozer.getSingleton().getModuleManager().getModulesByCategory(category)) {
            Button button = new Button(module, this, this.y + 9 + (count));
            buttons.add(button);
            count += 14;
        }
    }

    public void drawScreen(int i, int j, float f) {
        Gui.drawRect(x, y, x + width, y + height, new Color(253, 169, 0, 255).getRGB());
        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(category.getName(), (int) x + 2, (int) y + 2, new Color(255, 255, 255).getRGB());
        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(extended ? "-" : "+", (int) x + width - 10, (int) y + 2, new Color(255, 255, 255).getRGB());
        if (extended) {
            int count = 0;
            int lastButtonHeight = 0;
            int buttonHeight = 0;
            for (Button button : buttons) {
//                if(count > 0) {
//                    lastButtonHeight += buttons.get(count - 1).setHeight;
//                    buttonHeight += buttons.get(count).height;
//                }
                buttonHeight += button.drawScreen(i, j, buttonHeight);
                count++;
            }
        }
        if (dragging) {
            this.x = MathHelper.clamp(i - this.startingX, 0, Minecraft.getMinecraft().displayWidth - 100);
            this.y = MathHelper.clamp(j - this.startingY, 0, Minecraft.getMinecraft().displayHeight - 14);
        }
    }

    public void mouseClicked(int i, int j, int k) {
        if (extended) {
            for (Button button : buttons) {
                button.mouseClicked(i, j, k);
            }
        }
        if (k == 0) {
            if (isHovered(i, j, x, y, x + width, y + height)) {
                dragging = true;
                if (!setValues) {
                    startingX = i - x;
                    startingY = j - y;
                    setValues = true;
                }
            }
        }
        if (k == 1) {
            if (isHovered(i, j, x, y, x + width, y + height)) {
                extended = !extended;
            }
        }
    }

    public void mouseReleased(int i, int j, int k) {
        if (k == 0) {
            dragging = false;
            setValues = false;
        }
        if (extended) {
            for (Button button : buttons) {
                button.mouseReleased(i, j, k);
            }
        }
    }

    public void keyTyped(char c, int i) {
        if (extended) {
            for (Button button : buttons) {
                button.keyTyped(c, i);
            }
        }
    }

    public boolean isHovered(int mouseX, int mouseY, double x, double y, double x2, double y2) {
        return mouseX >= x && mouseX <= x2 && mouseY >= y && mouseY <= y2;
    }

}
