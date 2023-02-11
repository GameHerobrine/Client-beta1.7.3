package dozer.systems.module.impl.client;

import dozer.systems.module.Module;
import dozer.systems.module.ModuleCategory;
import dozer.systems.module.ModuleInfo;
import dozer.ui.clickGui.ClickGUI;
import org.lwjgl.input.Keyboard;

@ModuleInfo(name = "ClickGUI", description = "Opens the ClickGUI.", category = ModuleCategory.CLIENT, keyCode = Keyboard.KEY_RSHIFT)
public class ModuleClickGUI extends Module {

    @Override
    public void onEnable() {
        mc.displayGuiScreen(new ClickGUI());
        this.toggle();
    }

}
