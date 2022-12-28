package dozer.ui.impl.settings;

import dozer.setting.impl.SettingCheckBox;
import dozer.ui.impl.Button;
import net.minecraft.client.Minecraft;
import net.minecraft.src.gui.Gui;

import java.awt.*;

public class CheckBox extends SettingComponent {

    private double x;
    private double y;
    private final SettingCheckBox booleanSetting;

    public CheckBox(SettingCheckBox booleanSetting, Button parent) {
        super(booleanSetting, parent);
        this.booleanSetting = booleanSetting;
    }

    @Override
    public int drawScreen(int mouseX, int mouseY, double x, double y) {
        this.x = x;
        this.y = y;
        Gui.drawRect(x, y, x + 100, y + 14, new Color(16, 16, 16, 200).getRGB());
        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(this.booleanSetting.getName(), this.x + 2, this.y + 3, -1);
        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(this.booleanSetting.getValue() + "", this.x + 100 - Minecraft.getMinecraft().fontRenderer.getStringWidth(this.booleanSetting.getValue() + "") - 2, this.y + 3, -1);
        return 14;
    }

    @Override
    public void mouseClicked(int x, int y, int button) {
        if (button == 0) {
            if (isHovered(x, y, this.x, this.y)) {
                this.booleanSetting.setValue(!this.booleanSetting.getValue());
            }
        }
    }

}
