package dozer;

import dozer.systems.command.CommandManager;
import dozer.event.EventBus;
import dozer.systems.hud.HudManager;
import dozer.systems.hud.HudRenderer;
import dozer.systems.module.ModuleManager;
import dozer.systems.setting.SettingManager;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.awt.*;
import java.util.Objects;

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
  private final HudManager hudManager = new HudManager();
  private final HudRenderer hudRenderer = new HudRenderer();

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
