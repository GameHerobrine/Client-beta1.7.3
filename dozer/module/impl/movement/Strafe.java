package dozer.module.impl.movement;

import dozer.Dozer;
import dozer.event.Subscribe;
import dozer.event.impl.UpdateEvent;
import dozer.module.Module;
import dozer.module.ModuleCategory;
import dozer.module.ModuleInfo;
import dozer.util.player.MovementUtil;
import net.minecraft.src.MathHelper;

@ModuleInfo(name = "Strafe", description = "Allows you to strafe.", category = ModuleCategory.MOVEMENT)
public class Strafe extends Module {

    private final MovementUtil movementUtil = new MovementUtil();

    @Subscribe
    public void onUpdate(final UpdateEvent event) {
        if (Dozer.getSingleton().getModuleManager().getModuleByName("Fly").getState()) {
            return;
        }
        if (mc.thePlayer.movementInput.moveForward != 0 || mc.thePlayer.movementInput.moveStrafe != 0) {
            movementUtil.setSpeed(MathHelper.sqrt_double(mc.thePlayer.motionX * mc.thePlayer.motionX + mc.thePlayer.motionZ * mc.thePlayer.motionZ));
        }
    }


}
