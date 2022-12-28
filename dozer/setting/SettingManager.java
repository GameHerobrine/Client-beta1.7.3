package dozer.setting;

import java.util.ArrayList;

public class SettingManager {

  private final ArrayList<Setting<?>> settings = new ArrayList<>();

  public void addSetting(Setting<?> in) {
    this.settings.add(in);
  }

  public ArrayList<Setting<?>> getSettings() {
    return this.settings;
  }


}
