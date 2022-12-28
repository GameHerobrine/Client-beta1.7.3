package dozer.setting.impl;

import dozer.setting.Setting;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SettingNumber extends Setting<Double> {

    private double min;
    private double max;
    private double increment;

    public SettingNumber(String name, Setting.dependency dependency, double value, double min, double max, double increment) {
        super(name, dependency, value);
        this.max = max;
        this.min = min;
        this.increment = increment;
    }
}
