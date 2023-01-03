package dozer.systems.module;

import dozer.Dozer;
import dozer.util.MinecraftUtil;
import lombok.Data;

@Data
public class Module implements MinecraftUtil {

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
    Dozer.getSingleton().getSettingManager().addToSettingManager(this);
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
