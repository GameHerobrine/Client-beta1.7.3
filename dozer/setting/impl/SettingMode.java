package dozer.setting.impl;

import dozer.setting.Setting;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SettingMode<T extends Enum<?>> extends Setting<T> {

    public T[] values;


    public SettingMode(String name, Setting.dependency dependency, T value, T[] values) {
        super(name, dependency, value);
        this.values = values;
    }

    public Enum<?> nextValue(Enum<?> value) {
        Enum<?>[] values = value.getDeclaringClass().getEnumConstants();
        return values.length - 1 == value.ordinal() ? values[0] : values[value.ordinal() + 1];
    }

    public Enum<?> previousValue(Enum<?> value) {
        Enum<?>[] values = value.getDeclaringClass().getEnumConstants();
        return values[0] == value ? values[values.length - 1] : values[value.ordinal() - 1];
    }

}
