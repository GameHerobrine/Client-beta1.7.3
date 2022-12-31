package dozer.module.impl.render;

import dozer.module.Module;
import dozer.module.ModuleCategory;
import dozer.module.ModuleInfo;
import dozer.ui.clickGui.ClickGUI;
import org.lwjgl.input.Keyboard;

@ModuleInfo(name = "ClickGUI", description = "Opens the ClickGUI.", category = ModuleCategory.RENDER, keyCode = Keyboard.KEY_RSHIFT)
public class ClickGuiModule extends Module {

    @Override
    public void onEnable() {
        mc.displayGuiScreen(new ClickGUI());
        this.toggle();
    }

}
