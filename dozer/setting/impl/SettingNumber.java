package dozer.setting.impl;

import dozer.module.Module;
import dozer.setting.Setting;
import dozer.setting.SettingType;

public class SettingNumber extends Setting {

    private double value;
    private double min;
    private double max;
    private double increment;

    public SettingNumber(String name, Module moduleParent, Setting.dependency dependency, double defaultValue, double min, double max, double increment) {
        this.name = name;
        this.parentModule = moduleParent;
        this.dependency = dependency;
        this.value = defaultValue;
        this.min = min;
        this.max = max;
        this.increment = increment;
        this.settingType = SettingType.NUMBER;
        this.category = null;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getIncrement() {
        return increment;
    }

    public void setIncrement(double increment) {
        this.increment = increment;
    }

}
