package dozer.ui.clickGui.impl.settings;

import dozer.setting.impl.SettingCheckBox;
import dozer.ui.clickGui.impl.Widget;

import java.awt.*;

public class CheckBox extends Widget {

    private final SettingCheckBox booleanSetting;

    public CheckBox(SettingCheckBox booleanSetting, int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        this.booleanSetting = booleanSetting;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        drawRect(x, y, x + width, y + height, color);
        drawStringWithShadow(this.booleanSetting.getName(), x + 2, y + 2, Color.WHITE);
        drawStringWithShadow(this.booleanSetting.getValue() + "", x + 100 - fontRenderer().getStringWidth(this.booleanSetting.getValue() + "") - 2, this.y + 3, Color.WHITE);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        System.out.println("mouse clicked!");
        if (isHovering(mouseX, mouseY)) {
            this.booleanSetting.setValue(!this.booleanSetting.getValue());
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {

    }

    @Override
    public void keyTyped(char character, int keyCode) {

    }

}
