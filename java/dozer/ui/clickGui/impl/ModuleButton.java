package dozer.ui.clickGui.impl;

import dozer.Dozer;
import dozer.systems.module.Module;
import dozer.systems.module.impl.client.ModuleClickGUI;
import dozer.systems.setting.Setting;
import dozer.systems.setting.impl.SettingCheckBox;
import dozer.systems.setting.impl.SettingMode;
import dozer.systems.setting.impl.SettingSlider;
import dozer.ui.clickGui.impl.settings.CheckBox;
import dozer.ui.clickGui.impl.settings.Mode;
import dozer.ui.clickGui.impl.settings.Slider;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ModuleButton extends Widget<Module> {

    private final List<Widget<?>> settingComponents = new ArrayList<>();
    private boolean extended, binding;
    public int finalHeight = 0;

    public ModuleButton(Module module, int x, int y, int width, int height, Color color) {
        super(module, x, y, width, height, color);

        int count = 0;
        final int startY = y + height;


        for(Setting<?, ?> setting: Dozer.getSingleton().getSettingManager().getSettingsFromType(module.getClass())) {
            if(setting instanceof SettingCheckBox settingCheckBox) settingComponents.add(new CheckBox(settingCheckBox, x, startY + height * count, width, height, color));
            if(setting instanceof SettingMode settingMode) settingComponents.add(new Mode(settingMode, x, startY + height * count, width, height, color));
            if(setting instanceof SettingSlider settingSlider) settingComponents.add(new Slider(settingSlider, x, startY + height * count, width, height, color));
            count++;
        }

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawRect(x, y, x + width, y + height, new Color(16, 16, 16, 200));
        drawStringWithShadow(binding ? "Listening..." : type.getName(), x + 3, y + 2, type.isToggled() ? Color.WHITE : new Color(150, 150, 150));
        drawStringWithShadow(extended ? "-" : "+", x + width - 10, y + 2, type.isToggled() ? Color.WHITE : new Color(150, 150, 150));

        int count = 0;
        int settingHeight = 0;
        if (extended) {
            final int startY = y + height;
            for(Widget<?> widget: settingComponents) {
                widget.x = x;
                widget.y = startY + widget.height * count;
                widget.drawScreen(mouseX, mouseY, partialTicks);
                count++;
                settingHeight = widget.height;
            }
        }
        finalHeight = settingHeight * count + height;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {

        if(extended) settingComponents.forEach(settingComponent -> settingComponent.mouseClicked(mouseX, mouseY, mouseButton));

        if(!isHovering(mouseX, mouseY)) return;

        switch (mouseButton) {
            case 0 -> {if(!type.getClass().equals(ModuleClickGUI.class)) type.toggle();}
            case 1 -> extended = !extended;
            case 2 -> binding = true;
        }
    }

    @Override
    public void keyTyped(char character, int keyCode) {
        if (binding) {
            if (keyCode == 1) {
                type.setKeyBind(0);
                binding = false;
                return;
            }
            type.setKeyBind(keyCode);
            binding = false;
        }
        if (extended) settingComponents.forEach(settingComponent -> settingComponent.keyTyped(character, keyCode));
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int button) {
        if (extended) settingComponents.forEach(settingComponent -> settingComponent.mouseReleased(mouseX, mouseY, button));
    }

    public boolean isBinding() {
        return binding;
    }


}
