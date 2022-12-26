package dozer.module.impl.render;

import dozer.module.Module;
import dozer.module.ModuleCategory;
import dozer.module.ModuleInfo;
import org.lwjgl.input.Keyboard;

@ModuleInfo(name = "ClickGUI", description = "Opens the ClickGUI.", category = ModuleCategory.RENDER)
public class ClickGUI extends Module {

    public ClickGUI() {
        this.setKeyBind(Keyboard.KEY_RSHIFT);
    }

    @Override
    public void onEnable() {
        mc.displayGuiScreen(new dozer.ui.ClickGUI(5, 5));
        this.toggle();
    }

}
