package dozer.ui.impl;

import dozer.module.Module;
import dozer.setting.Setting;
import dozer.setting.SettingType;
import dozer.ui.impl.settings.CheckBox;
import dozer.ui.impl.settings.Mode;
import dozer.ui.impl.settings.SettingComponent;
import dozer.ui.impl.settings.Slider;
import net.minecraft.client.Minecraft;
import net.minecraft.src.Gui;

import java.awt.*;
import java.util.ArrayList;

public class Button {

    int setHeight = 0;
    int height = 14;
    private ArrayList<SettingComponent> settingComponents = new ArrayList<>();
    private Module module;
    private Panel panel;
    private boolean extended = false;
    private boolean binding = false;
    private int extendedHeight = 0;
    private int width = 100;
    private double y;

    public Button(Module module, Panel panel, double y) {
        super();
        this.y = y;
        this.module = module;
        this.panel = panel;
        for (Setting setting : module.getSettings()) {
            if (setting.getSettingType() == SettingType.BOOLEAN) {
                settingComponents.add(new CheckBox(setting, this));
            } else if (setting.getSettingType() == SettingType.MODE) {
                settingComponents.add(new Mode(setting, this));
            } else if (setting.getSettingType() == SettingType.NUMBER) {
                settingComponents.add(new Slider(setting, this));
            }
        }
    }

    public int drawScreen(int mouseX, int mouseY, int p) {
        Gui.drawRect(panel.x, panel.y + this.y + p, panel.x + this.width, panel.y + this.y + this.height + p, new Color(16, 16, 16, 200).getRGB());
        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(binding ? "Listening..." : module.getName(), panel.x + 3, panel.y + this.y + 4 + p, module.getState() ? new Color(255, 255, 255).getRGB() : new Color(150, 150, 150).getRGB());
        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(extended ? "-" : "+", panel.x + width - 10, panel.y + this.y + 4 + p, module.getState() ? new Color(255, 255, 255).getRGB() : new Color(150, 150, 150).getRGB());

        setHeight = 0;
        extendedHeight = p;
        if (extended) {
            for (SettingComponent settingComponent : settingComponents) {
                setHeight += settingComponent.drawScreen(mouseX, mouseY, panel.x, panel.y + this.y + p + this.height + setHeight);
            }
        }
        return setHeight;
    }

    public void mouseClicked(int mouseX, int mouseY, int button) {
        if (button == 0 && !module.getName().equalsIgnoreCase("ClickGUI")) {
            if (isHovered(mouseX, mouseY, (int) panel.x, (int) (y + 5 + extendedHeight), (int) panel.x + width, (int) (y + height + 4 + extendedHeight))) {
                module.toggle();
            }
        }
        if (button == 1) {
            if (isHovered(mouseX, mouseY, (int) panel.x, (int) (y + 5 + extendedHeight), (int) panel.x + width, (int) (y + height + 4 + extendedHeight))) {
                extended = !extended;
            }
        }
        if (button == 2) {
            if (isHovered(mouseX, mouseY, (int) panel.x, (int) (y + 5 + extendedHeight), (int) panel.x + width, (int) (y + height + 4 + extendedHeight))) {
                binding = true;
            }
        }
        if (extended) {
            for (SettingComponent settingComponent : settingComponents) {
                settingComponent.mouseClicked(mouseX, mouseY, button);
            }
        }
    }

    public void keyTyped(char typedChar, int keyCode) {
        if (binding) {
            if (keyCode == 1) {
                module.setKeyBind(0);
                binding = false;
                return;
            }
            module.setKeyBind(keyCode);
            binding = false;
        }
        if (extended) {
            for (SettingComponent settingComponent : settingComponents) {
                settingComponent.keyTyped(typedChar, keyCode);
            }
        }
    }

    public void mouseReleased(int mouseX, int mouseY, int button) {
        if (extended) {
            for (SettingComponent settingComponent : settingComponents) {
                settingComponent.mouseReleased(mouseX, mouseY, button);
            }
        }
    }

    public boolean isBinding() {
        return binding;
    }

    public boolean isHovered(int mouseX, int mouseY, double x, double y, double x2, double y2) {
        return mouseX >= x && mouseX <= x2 && mouseY >= y && mouseY <= y2;
    }

}
