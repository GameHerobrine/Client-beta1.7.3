package dozer.systems.module.impl.movement;

import dozer.Dozer;
import dozer.event.Subscribe;
import dozer.event.impl.UpdateEvent;
import dozer.systems.module.Module;
import dozer.systems.module.ModuleCategory;
import dozer.systems.module.ModuleInfo;
import dozer.util.misc.TimerUtil;

@ModuleInfo(name = "Spider", description = "Allows you to climb walls.", category = ModuleCategory.MOVEMENT)
public class Spider extends Module {

    private final TimerUtil timer = new TimerUtil();

    @Subscribe
    public void onUpdate(final UpdateEvent event) {
        if (Dozer.getSingleton().getModuleManager().getModuleByName("Step").isToggled()) {
            return;
        }
        if (mc.thePlayer.isCollidedHorizontally) {
            mc.thePlayer.motionY = 0.1;
        }
    }

}
