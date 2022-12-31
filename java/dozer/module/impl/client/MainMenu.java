package dozer.module.impl.client;

import dozer.module.Module;
import dozer.module.ModuleCategory;
import dozer.module.ModuleInfo;
import dozer.setting.Serialize;
import dozer.setting.annotation.CheckBox;


@ModuleInfo(name = "Main Menu", description = "Settings for the main menu", category = ModuleCategory.CLIENT)
public class MainMenu extends Module {

    @Serialize(name = "buttons")
    @CheckBox()
    public static boolean buttons = false;






}
