package dozer.module;

import dozer.Dozer;
import dozer.setting.Setting;
import dozer.setting.SettingManager;
import dozer.setting.impl.SettingBoolean;
import dozer.setting.impl.SettingMode;
import dozer.setting.impl.SettingNumber;
import dozer.util.MinecraftUtil;
import lombok.Data;

@Data
public class Module implements MinecraftUtil {

  public SettingManager settingManager = new SettingManager();
  protected String name, description, suffix;
  protected ModuleCategory category;
  protected int keyBind;
  protected boolean toggled;
  private final ModuleInfo moduleinfo = getClass().getAnnotation(ModuleInfo.class);

  public Module() {
    setName(moduleinfo.name());
    setDescription(moduleinfo.description());
    setCategory(moduleinfo.category());
    setKeyBind(moduleinfo.keyCode());
    this.toggled = false;
    this.suffix = "";
  }


  public void onEnable() {}

  public void onDisable() {}

  public void toggle() {
    this.toggled = !this.toggled;
    if (this.toggled) {
      this.onEnable();
      Dozer.getSingleton().getEventBus().register(this);
    } else {
      this.onDisable();
      Dozer.getSingleton().getEventBus().unregister(this);
    }
  }

  public SettingBoolean boolSetting(String name, Setting.dependency dependency, boolean defaultValue) {
    SettingBoolean settingBoolean = new SettingBoolean(name, dependency, defaultValue);
    getSettingManager().addSetting(settingBoolean);
    return settingBoolean;
  }


  public SettingNumber numberSetting(String name, Setting.dependency dependency, double defaultValue, double min, double max, double increment) {
    SettingNumber settingNumber = new SettingNumber(name, dependency, defaultValue, min, max, increment);
    getSettingManager().addSetting(settingNumber);
    return settingNumber;
  }

  public SettingMode<?> modeSetting(String name, Setting.dependency dependency, Enum<?> defaultValue, Enum<?>[] defaultValues) {
    SettingMode<?> settingMode = new SettingMode(name, dependency, defaultValue, defaultValues);
    getSettingManager().addSetting(settingMode);
    return settingMode;
  }

}
