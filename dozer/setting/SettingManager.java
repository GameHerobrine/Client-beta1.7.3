package dozer.setting;

import dozer.module.Module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SettingManager {

  private final ArrayList<Setting> settings;

  public SettingManager() {
    this.settings = new ArrayList<>();
  }

  public void addSetting(Setting in) {
    this.settings.add(in);
  }

  public ArrayList<Setting> getSettings() {
    return this.settings;
  }

  public List<Setting> getSettingsByMod(Module mod) {
    return settings.stream()
        .filter(setting -> setting.getParentModule().equals(mod))
        .collect(Collectors.toList());
  }

  public Setting getSettingByName(String name, Module mod) throws IllegalArgumentException {
    for (Setting set : getSettings()) {
      if (set.getName().equalsIgnoreCase(name) && set.getParentModule() == mod) {
        return set;
      }
    }
    throw new IllegalArgumentException();
  }
}
