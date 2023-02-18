package dozer.systems.setting;

import dozer.systems.setting.annotation.CheckBox;
import dozer.systems.setting.annotation.*;
import dozer.systems.setting.impl.SettingCheckBox;
import dozer.systems.setting.impl.SettingMode;
import dozer.systems.setting.impl.SettingSlider;
import lombok.SneakyThrows;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Annotation based setting system.
 * @author Eternal
 */
public class SettingManager {

  private final List<Setting<?, ?>> settings = new ArrayList<>();
  private final Map<Class<? extends Annotation>, Class<? extends Setting<?, ?>>>
      annotationToSetting =
          new HashMap() {
            {
              put(CheckBox.class, SettingCheckBox.class);
              put(Slider.class, SettingSlider.class);
              put(Mode.class, SettingMode.class);
            }
          };

  // State of the art "I can't think of a better implementation"
  public void init() {
    settings.forEach(
        setting -> {
          if (setting.getField().isAnnotationPresent(Parent.class)) {
            Setting<?, ?> parent =
                getSetting(
                    setting.getObject().getClass(),
                    setting.getField().getAnnotation(Parent.class).parent());
            setting.setParent(parent);
            if (parent instanceof SettingMode) setting.setParentMode(parent.getName());
          } else if (setting.getField().isAnnotationPresent(SettingGroup.class)) {
            setting.setGroup(setting.getField().getAnnotation(SettingGroup.class).groupName());
          }
        });
  }

  @SneakyThrows
  public void addToSettingManager(Object o) {
    for (Field field : o.getClass().getFields()) {
      if (field.isAnnotationPresent(Serialize.class)) {
        Class<? extends Annotation> settingType = field.getAnnotations()[1].annotationType();
        Class<? extends Setting<?, ?>> setting = annotationToSetting.get(settingType);

        Setting<?, ?> instance =
            setting.getConstructor(Field.class, Object.class).newInstance(field, o);

        settings.add(instance);
      }
    }
  }

  public Setting<?, ?> getSetting(Class<?> clazz, String name) {
    return settings.stream()
        .filter(
            setting -> setting.getObject().getClass() == clazz && setting.getName().equals(name))
        .findFirst()
        .orElseThrow(NoSuchElementException::new);
  }

  public List<Setting<?, ?>> getSettingsFromType(Class<?> clazz) {
    return settings.stream().filter(setting -> setting.getObject().getClass() == clazz).toList();
  }
}
