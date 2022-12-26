package dozer.setting;

import dozer.module.Module;

public class Setting {

    public boolean focused;
    protected String name;
    protected Module parentModule;
    protected dependency dependency;
    protected SettingType settingType;
    protected SettingCategory category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Setting.dependency getDependency() {
        return dependency;
    }

    public void setDependency(Setting.dependency dependency) {
        this.dependency = dependency;
    }

    public SettingType getSettingType() {
        return settingType;
    }

    public void setSettingType(SettingType settingType) {
        this.settingType = settingType;
    }

    public Module getParentModule() {
        return parentModule;
    }

    public void setParentModule(Module parentModule) {
        this.parentModule = parentModule;
    }

    public boolean isMode() {
        return this.settingType == SettingType.MODE;
    }

    public boolean isNumber() {
        return this.settingType == SettingType.NUMBER;
    }

    public boolean isBoolean() {
        return this.settingType == SettingType.BOOLEAN;
    }

    public SettingCategory getCategory() {
        return category;
    }

    public void setCategory(SettingCategory category) {
        this.category = category;
    }

    public boolean isFocused() {
        return focused;
    }

    public void setFocused(boolean focused) {
        this.focused = focused;
    }

    public boolean isParentActive() {
        if (this.dependency == null) {
            return true;
        }
        return this.dependency.check();
    }

    @FunctionalInterface
    public interface dependency {
        boolean check();
    }

}
