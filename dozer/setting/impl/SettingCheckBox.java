package dozer.setting.impl;

import dozer.setting.Setting;
import dozer.setting.annotation.CheckBox;

import java.lang.reflect.Field;

public class SettingCheckBox extends Setting<CheckBox, Boolean> {
  public SettingCheckBox(Field field, Object object) {
    super(field, object);
  }

  @Override
  public Boolean getValue() {
    return getFieldValue();
  }

  @Override
  public void setValue(Boolean aBoolean) {
    setFieldValue(aBoolean);
  }

  @Override
  public void loadSetting(String s) {
    setValue(Boolean.parseBoolean(s));
  }

  public void cycle() {
    setValue(!getValue());
  }

}
