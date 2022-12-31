package dozer.ui.clickGui;

import dozer.systems.module.ModuleCategory;
import dozer.ui.clickGui.impl.CategoryPanel;
import dozer.ui.clickGui.impl.ModuleButton;
import dozer.util.render.Render2DUtil;
import net.minecraft.src.gui.GuiScreen;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ClickGUI extends GuiScreen implements Render2DUtil {

    private final List<CategoryPanel> panels = new ArrayList<>();

    public ClickGUI() {

        int y = 5;
        int width = 100;
        int height = 14;
        int offset = 0;
        int x = 5;

        for (ModuleCategory category : ModuleCategory.values()) {
            panels.add(new CategoryPanel(category, x + offset, y, width, height, new Color(253, 169, 0)));
            offset += width + 1;
        }
    }

    public boolean isBinding() {
        for (CategoryPanel panel : panels) {
            for (ModuleButton button : panel.buttons) {
                if (button.isBinding()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawRect(0, 0, width, height, 0x80000000);
        panels.forEach(panel -> panel.drawScreen(mouseX, mouseY, partialTicks));
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void keyTyped(char character, int keyCode) {
        if (keyCode == 1 && !isBinding()) {
            mc.displayGuiScreen(null);
            mc.focusDisplay();
        }
        panels.forEach(panel -> panel.keyTyped(character, keyCode));
        super.keyTyped(character, keyCode);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        panels.forEach(panel -> panel.mouseClicked(mouseX, mouseY, mouseButton));
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        panels.forEach(panel -> panel.mouseReleased(mouseX, mouseY, state));
        super.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    public void handleInput() {
        super.handleInput();
    }

    @Override
    public void handleMouseInput() {
        super.handleMouseInput();
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
    }

}
