package dozer.systems.module.impl.movement;

import dozer.event.Subscribe;
import dozer.event.impl.UpdateEvent;
import dozer.systems.module.Module;
import dozer.systems.module.ModuleCategory;
import dozer.systems.module.ModuleInfo;

@ModuleInfo(name = "Step", description = "Allows you to step up blocks.", category = ModuleCategory.MOVEMENT)
public class ModuleStep extends Module {

    @Subscribe
    public void onUpdate(final UpdateEvent event) {
        mc.thePlayer.stepHeight = 1.0F;
    }

    @Override
    public void onDisable() {
        mc.thePlayer.stepHeight = 0.5F;
    }

}
