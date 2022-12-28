package dozer.setting.impl;

import dozer.setting.Setting;

public class SettingBoolean extends Setting<Boolean> {


    public SettingBoolean(String name, Setting.dependency dependency, Boolean value) {
        super(name, dependency, value);
    }
}
