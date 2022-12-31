package dozer.module.impl.movement;

import dozer.event.Subscribe;
import dozer.event.impl.UpdateEvent;
import dozer.module.Module;
import dozer.module.ModuleCategory;
import dozer.module.ModuleInfo;

@ModuleInfo(name = "Step", description = "Allows you to step up blocks.", category = ModuleCategory.MOVEMENT)
public class Step extends Module {

    @Subscribe
    public void onUpdate(final UpdateEvent event) {
        mc.thePlayer.stepHeight = 1.0F;
    }

    @Override
    public void onDisable() {
        mc.thePlayer.stepHeight = 0.5F;
    }

}
