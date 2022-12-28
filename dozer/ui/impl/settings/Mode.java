package dozer.ui.impl.settings;

import dozer.setting.impl.SettingMode;
import dozer.ui.impl.Button;
import net.minecraft.client.Minecraft;
import net.minecraft.src.gui.Gui;

import java.awt.*;

public class Mode extends SettingComponent {

  private double x;
  private double y;
  private final SettingMode modeSetting;

  public Mode(SettingMode settingMode, Button parent) {
    super(settingMode, parent);
    this.modeSetting = settingMode;
  }

  @Override
  public int drawScreen(int mouseX, int mouseY, double x, double y) {
    this.x = x;
    this.y = y;
    Gui.drawRect(x, y, x + 100, y + 14, new Color(16, 16, 16, 200).getRGB());
    Minecraft.getMinecraft()
        .fontRenderer
        .drawStringWithShadow(this.modeSetting.getName(), this.x + 2, this.y + 3, -1);
    Minecraft.getMinecraft()
        .fontRenderer
        .drawStringWithShadow(
            this.modeSetting.getValue(),
            this.x
                + 100
                - Minecraft.getMinecraft().fontRenderer.getStringWidth(this.modeSetting.getValue())
                - 2,
            this.y + 3,
            -1);
    return 14;
  }

  @Override
  public void mouseClicked(int x, int y, int button) {
    if (button == 0) {
      if (isHovered(x, y, this.x, this.y)) {
        modeSetting.cycle();
      }
    }
  }
}
