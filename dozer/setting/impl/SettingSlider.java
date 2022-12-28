package dozer.setting.impl;

import dozer.setting.Setting;
import dozer.setting.annotation.Slider;

import java.lang.reflect.Field;

public class SettingSlider extends Setting<Slider, Double> {
  public SettingSlider(Field field, Object object) {
    super(field, object);
  }

  @Override
  public Double getValue() {
    return getFieldValue();
  }

  @Override
  public void setValue(Double t) {
    setFieldValue(t);
  }

  @Override
  public void loadSetting(String s) {
    setValue(Double.parseDouble(s));
  }

  public void increment(boolean dir) {
    double precision = 1 / getAnnotation().increment();
    double value = getValue();
    value += dir ? getAnnotation().increment() : -getAnnotation().increment();
    value = Math.round(Math.max(getAnnotation().min(), Math.min(getAnnotation().max(), value)) * precision) / precision;
    setValue(value);
  }

  public double min() {
    return getAnnotation().min();
  }

  public double max() {
    return getAnnotation().max();
  }

  public double increment() {
    return getAnnotation().increment();
  }

}
