package dozer.ui.clickGui.impl.settings;

import dozer.systems.setting.impl.SettingCheckBox;
import dozer.ui.clickGui.impl.Widget;

import java.awt.*;

public class CheckBox extends Widget<SettingCheckBox> {

    public CheckBox(SettingCheckBox booleanSetting, int x, int y, int width, int height, Color color) {
        super(booleanSetting, x, y, width, height, color);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawRect(x, y, x + width, y + height, color);
        drawStringWithShadow(type.getName(), x + 2, y + 3, Color.WHITE);
        drawStringWithShadow(type.getValue() + "", x + 100 - fontRenderer().getStringWidth(type.getValue() + "") - 2, this.y + 3, Color.WHITE);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        System.out.println("mouse clicked!");
        if (isHovering(mouseX, mouseY)) {
            type.setValue(!type.getValue());
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {

    }

    @Override
    public void keyTyped(char character, int keyCode) {

    }

}
