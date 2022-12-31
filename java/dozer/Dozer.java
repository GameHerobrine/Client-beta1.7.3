package dozer;

import dozer.command.CommandManager;
import dozer.event.EventBus;
import dozer.font.CustomFontRenderer;
import dozer.module.ModuleManager;
import dozer.setting.SettingManager;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

@Getter
public class Dozer {

  @Getter private static final Dozer singleton = new Dozer();
  private final String name = "DozerHack", version = "2.0.0";
  private final String[] authors = {"KillDozer", "Signam", "Shae", "Eternal"};

  @Setter
  private String prefix = ".";

  private final CustomFontRenderer customFontRenderer = new CustomFontRenderer(new Font("Verdana", Font.PLAIN, 18), true, true);
  private final SettingManager settingManager = new SettingManager();
  private final ModuleManager moduleManager = new ModuleManager();
  private final CommandManager commandManager = new CommandManager();
  private final EventBus eventBus = new EventBus();

  @SneakyThrows
  public void init() {
    System.out.println("Initializing DozerHack...");

    moduleManager.init();
    commandManager.init();
    settingManager.init();

    System.out.println("DozerHack initialized!");
  }
}
