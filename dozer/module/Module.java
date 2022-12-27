package dozer.module;

import dozer.Dozer;
import dozer.setting.Setting;
import dozer.util.MinecraftUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class Module implements MinecraftUtil {

  public List<Setting> settings;
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
    settings = new ArrayList<>();
  }

  public void addSettings(Setting... setting) {
    Arrays.asList(setting).forEach(Dozer.getSingleton().getSettingManager()::addSetting);
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
}
