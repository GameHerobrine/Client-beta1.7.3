package dozer;

import dozer.event.EventBus;
import dozer.module.ModuleManager;
import dozer.setting.SettingManager;

public class Dozer {

    private static final Dozer singleton = new Dozer();
    private final String name = "DozerHack", version = "2.0.0";
    private final String[] authors = {"KillDozer", "Signam", "Shae"};

    private final SettingManager settingManager = new SettingManager();
    private final ModuleManager moduleManager = new ModuleManager();
    private final EventBus eventBus = new EventBus();

    public static Dozer getSingleton() {
        return singleton;
    }

    public void init() {
        System.out.println("Initializing DozerHack...");

        moduleManager.init();

        System.out.println("DozerHack initialized!");
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String[] getAuthor() {
        return authors;
    }

    public SettingManager getSettingManager() {
        return settingManager;
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

}
