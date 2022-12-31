package dozer.ui.clickGui.impl.settings;

import dozer.setting.impl.SettingMode;
import dozer.ui.clickGui.impl.ModuleButton;
import dozer.ui.clickGui.impl.Widget;
import net.minecraft.client.Minecraft;
import net.minecraft.src.gui.Gui;

import java.awt.*;

public class Mode extends Widget {


    private final SettingMode modeSetting;

    public Mode(SettingMode modeSetting, int x, int y, int width, int height, Color color) {
      super(x, y, width, height, color);
      this.modeSetting = modeSetting;
    }


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      Gui.drawRect(x, y, x + 100, y + 14, new Color(16, 16, 16, 200).getRGB());
      drawStringWithShadow(this.modeSetting.getName(), x + 2, this.y + 3, Color.WHITE);
      drawStringWithShadow(this.modeSetting.getValue(), x + 100 - fontRenderer().getStringWidth(this.modeSetting.getValue()) - 2, this.y + 3, Color.WHITE);
    }



    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if(!isHovering(mouseX, mouseY)) return;
        if (mouseButton == 0) {
          modeSetting.cycle();
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {

    }

    @Override
    public void keyTyped(char character, int keyCode) {

    }
}
