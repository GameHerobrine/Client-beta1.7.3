package dozer.ui.clickGui.impl.settings;

import dozer.systems.setting.impl.SettingMode;
import dozer.ui.clickGui.impl.Widget;
import net.minecraft.src.gui.Gui;

import java.awt.*;

public class Mode extends Widget<SettingMode> {

    public Mode(SettingMode modeSetting, int x, int y, int width, int height, Color color) {
      super(modeSetting, x, y, width, height, color);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      Gui.drawRect(x, y, x + 100, y + 14, new Color(16, 16, 16, 200).getRGB());
      drawStringWithShadow(type.getName(), x + 2, this.y + 3, Color.WHITE);
      drawStringWithShadow(type.getValue(), x + 100 - fontRenderer().getStringWidth(type.getValue()) - 2, this.y + 3, color);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if(!isHovering(mouseX, mouseY)) return;
        switch (mouseButton) {
            case 0 -> type.cycle();
            case 1 -> type.cycleReverse();
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {

    }

    @Override
    public void keyTyped(char character, int keyCode) {

    }
}
