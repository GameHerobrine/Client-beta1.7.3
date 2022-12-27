package dozer.ui.impl.settings;

import dozer.setting.Setting;
import dozer.setting.impl.SettingMode;
import dozer.ui.impl.Button;
import net.minecraft.client.Minecraft;
import net.minecraft.src.gui.Gui;

import java.awt.*;

public class Mode extends SettingComponent {

    private double x;
    private double y;
    private SettingMode modeSetting;

    public Mode(Setting s, Button parent) {
        super(s, parent);
        this.modeSetting = (SettingMode) s;
    }

    @Override
    public int drawScreen(int mouseX, int mouseY, double x, double y) {
        this.x = x;
        this.y = y;
        Gui.drawRect(x, y, x + 100, y + 14, new Color(16, 16, 16, 200).getRGB());
        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(this.modeSetting.getName(), this.x + 2, this.y + 3, -1);
        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(this.modeSetting.getMode(), this.x + 100 - Minecraft.getMinecraft().fontRenderer.getStringWidth(this.modeSetting.getMode()) - 2, this.y + 3, -1);
        return 14;
    }

    @Override
    public void mouseClicked(int x, int y, int button) {
        if (button == 0) {
            if (isHovered(x, y, this.x, this.y)) {
                this.modeSetting.cycle("up");
            }
        }
        if (button == 1) {
            if (isHovered(x, y, this.x, this.y)) {
                this.modeSetting.cycle("down");
            }
        }
    }

}