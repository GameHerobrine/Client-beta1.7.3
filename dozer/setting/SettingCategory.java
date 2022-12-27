package dozer.setting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingCategory extends Setting {
    private final String name;
    private List<Setting> settings = new ArrayList<>();
    private boolean extended;

    public SettingCategory(final String name, dependency dependency) {
        this.name = name;
        this.dependency = dependency;
    }

    public List<Setting> getSettings() {
        return settings;
    }

    public void setSettings(List<Setting> settings) {
        this.settings = settings;
    }

    public void addSettings(Setting... settings) {
        this.settings.addAll(Arrays.asList(settings));
    }

    public String getName() {
        return name;
    }

    public boolean isExtended() {
        return extended;
    }

    public void setExtended(boolean extended) {
        this.extended = extended;
    }
}
