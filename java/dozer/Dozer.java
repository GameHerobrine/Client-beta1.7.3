package dozer;

import dozer.event.EventBus;
import dozer.systems.command.CommandManager;
import dozer.systems.hud.HUDManager;
import dozer.systems.hud.HUDRenderer;
import dozer.systems.module.ModuleManager;
import dozer.systems.setting.SettingManager;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

@Getter
public class Dozer {

  @Getter private static final Dozer singleton = new Dozer();
  private final String name = "DozerHack", version = "2.0.0";
  private final String[] authors = {"KillDozer", "Signam", "Shae", "Eternal"};

  @Setter
  private String prefix = ".";
  private final SettingManager settingManager = new SettingManager();
  private final ModuleManager moduleManager = new ModuleManager();
  private final CommandManager commandManager = new CommandManager();
  private final EventBus eventBus = new EventBus();
  private final HUDManager hudManager = new HUDManager();
  private final HUDRenderer hudRenderer = new HUDRenderer();

  @SneakyThrows
  public void init() {
    System.out.println("Initializing DozerHack...");

    moduleManager.init();
    commandManager.init();
    settingManager.init();
    hudManager.init();
    hudRenderer.init();

    System.out.println("DozerHack initialized!");
  }
}
