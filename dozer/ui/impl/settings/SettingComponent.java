package dozer.ui.impl.settings;

import dozer.setting.Setting;
import dozer.ui.impl.Button;

public abstract class SettingComponent {

    public Setting setting;
    protected Button parent;
    private double height;

    public SettingComponent(Setting s, Button parent) {
        this.parent = parent;
        this.setting = s;
        this.height = 12;
    }

    public void mouseClicked(int x, int y, int button) {
    }

    public void keyTyped(char typedChar, int keyCode) {
    }

    public Setting getSetting() {
        return this.setting;
    }

    public double getHeight() {
        return height;
    }

    public abstract int drawScreen(int mouseX, int mouseY, double x, double y);

    public void mouseReleased(int x, int y, int button) {
    }

    public boolean isHovered(int mouseX, int mouseY, double x, double y) {
        return mouseX >= x && mouseX <= x + 100 && mouseY >= y && mouseY <= y + 12;
    }

}
