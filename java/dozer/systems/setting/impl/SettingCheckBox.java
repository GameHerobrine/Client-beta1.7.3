package dozer.systems.setting.impl;



import dozer.systems.setting.annotation.CheckBox;
import dozer.systems.setting.Setting;

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



}
