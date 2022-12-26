package dozer.setting.impl;

import dozer.module.Module;
import dozer.setting.Setting;
import dozer.setting.SettingType;

public class SettingBoolean extends Setting {

    private boolean value;

    public SettingBoolean(String name, Module moduleParent, Setting.dependency dependency, boolean defaultValue) {
        this.name = name;
        this.parentModule = moduleParent;
        this.dependency = dependency;
        this.value = defaultValue;
        this.settingType = SettingType.BOOLEAN;
        this.category = null;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

}
