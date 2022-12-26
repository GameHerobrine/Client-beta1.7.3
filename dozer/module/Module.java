package dozer.module;

import dozer.Dozer;
import dozer.setting.Setting;
import dozer.util.MinecraftUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Module implements MinecraftUtil {

    public List<Setting> settings;
    protected String name, description, suffix;
    protected ModuleCategory category;
    protected boolean state;
    protected int keyBind;
    ModuleInfo moduleinfo = getClass().getAnnotation(ModuleInfo.class);

    public Module() {
        setName(moduleinfo.name());
        setDescription(moduleinfo.description());
        setCategory(moduleinfo.category());
        this.state = false;
        this.keyBind = 0;
        this.suffix = "";
        settings = new ArrayList<>();
    }

    public void addSettings(Setting... setting) {
        this.settings.addAll(Arrays.asList(setting));
        this.settings.forEach(s -> Dozer.getSingleton().getSettingManager().addSetting(s));
    }

    public List<Setting> getSettings() {
        return settings;
    }

    public void setSettings(List<Setting> settings) {
        this.settings = settings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public ModuleCategory getCategory() {
        return category;
    }

    public void setCategory(ModuleCategory category) {
        this.category = category;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public int getKeyBind() {
        return keyBind;
    }

    public void setKeyBind(int keyBind) {
        this.keyBind = keyBind;
    }

    public void toggle() {
        this.state = !this.state;
        if (this.state) {
            this.onEnable();
            Dozer.getSingleton().getEventBus().register(this);
        } else {
            this.onDisable();
            Dozer.getSingleton().getEventBus().unregister(this);
        }
    }

    public void onEnable() {

    }

    public void onDisable() {

    }

}
