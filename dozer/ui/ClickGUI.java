package dozer.ui;

import dozer.module.ModuleCategory;
import dozer.ui.impl.Button;
import dozer.ui.impl.Panel;
import net.minecraft.src.GuiScreen;

import java.util.ArrayList;

public class ClickGUI extends GuiScreen {

    private final ArrayList<Panel> panels = new ArrayList<Panel>();
    public double x, y;

    public ClickGUI(double x, double y) {
        this.x = x;
        this.y = y;
        for (ModuleCategory category : ModuleCategory.values()) {
            Panel panel = new Panel(this.x, this.y, category, this);
            panels.add(panel);
            this.x += 110;
        }
    }

    public boolean isBinding() {
        for (Panel panel : panels) {
            for (Button button : panel.buttons) {
                if (button.isBinding()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void drawScreen(int i, int j, float f) {
        super.drawScreen(i, j, f);
        drawRect(0, 0, width, height, 0x80000000);
        for (Panel panel : panels) {
            panel.drawScreen(i, j, f);
        }
    }

    @Override
    protected void keyTyped(char c, int i) {
        if (i == 1 && !isBinding()) {
            mc.displayGuiScreen(null);
            mc.setIngameFocus();
        }
        for (Panel panel : panels) {
            panel.keyTyped(c, i);
        }
    }

    @Override
    protected void mouseClicked(int i, int j, int k) {
        super.mouseClicked(i, j, k);
        for (Panel panel : panels) {
            panel.mouseClicked(i, j, k);
        }
    }

    @Override
    protected void mouseReleased(int i, int j, int k) {
        super.mouseReleased(i, j, k);
        for (Panel panel : panels) {
            panel.mouseReleased(i, j, k);
        }
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
