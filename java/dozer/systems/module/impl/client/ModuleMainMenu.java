package dozer.systems.module.impl.client;

import dozer.systems.module.ModuleCategory;
import dozer.systems.module.ModuleInfo;
import dozer.systems.module.Module;
import dozer.systems.setting.annotation.CheckBox;
import dozer.systems.setting.Serialize;


@ModuleInfo(name = "Main Menu", description = "Settings for the main menu", category = ModuleCategory.CLIENT)
public class ModuleMainMenu extends Module {

    @Serialize(name = "buttons")
    @CheckBox()
    public static boolean buttons = false;






}
