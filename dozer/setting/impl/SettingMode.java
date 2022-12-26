package dozer.setting.impl;

import dozer.module.Module;
import dozer.setting.Setting;
import dozer.setting.SettingType;

import java.util.Arrays;
import java.util.List;

public class SettingMode extends Setting {

    private final List<String> options;
    private String value;

    public SettingMode(String name, Module moduleParent, Setting.dependency dependency, String defaultValue, String... options) {
        this.name = name;
        this.parentModule = moduleParent;
        this.dependency = dependency;
        this.value = defaultValue;
        this.options = Arrays.asList(options);
        this.settingType = SettingType.MODE;
        this.category = null;
    }

    public String getMode() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getIndexByMode(String mode) {
        int index = 0;
        for (String s : options) {
            if (s.equals(mode)) {
                return index;
            }
            index++;
        }
        return 0;
    }

    public void cycle(String type) {
        int index = this.getIndexByMode(this.getMode());

        if (type.equalsIgnoreCase("up")) {
            index = index + 1;

            if (index > this.getOptions().size() - 1) {
                index = 0;
            }
            if (index < 0) {
                index = this.getOptions().size() - 1;
            }
        } else if (type.equalsIgnoreCase("down")) {
            index = index - 1;

            if (index > this.getOptions().size() - 1) {
                index = 0;
            }
            if (index < 0) {
                index = this.getOptions().size() - 1;
            }
        }
        this.setValue(this.getOptions().get(index));
    }

}
