package dozer.setting;

import com.sun.istack.internal.NotNull;
import dozer.module.Module;

import java.util.ArrayList;

public class SettingManager {

    private final ArrayList<Setting> settings;

    public SettingManager() {
        this.settings = new ArrayList<Setting>();
    }

    public void addSetting(@NotNull Setting in) {
        this.settings.add(in);
    }

    public ArrayList<Setting> getSettings() {
        return this.settings;
    }

    public ArrayList<Setting> getSettingsByMod(Module mod) {
        ArrayList<Setting> out = new ArrayList<Setting>();
        for (Setting s : getSettings()) {
            if (s.getParentModule().equals(mod)) {
                out.add(s);
            }
        }
        if (out.isEmpty()) {
            return null;
        }
        return out;
    }

    public Setting getSettingByName(@NotNull String name, @NotNull Module mod) throws IllegalArgumentException {
        for (Setting set : getSettings()) {
            if (set.getName().equalsIgnoreCase(name) && set.getParentModule() == mod) {
                return set;
            }
        }
        throw new IllegalArgumentException();
    }

}
