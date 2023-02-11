package dozer.systems.module.impl.movement;

import dozer.Dozer;
import dozer.event.Subscribe;
import dozer.event.impl.UpdateEvent;
import dozer.systems.module.Module;
import dozer.systems.module.ModuleCategory;
import dozer.systems.module.ModuleInfo;
import dozer.util.player.UtilMovement;
import net.minecraft.src.MathHelper;

@ModuleInfo(name = "Strafe", description = "Allows you to strafe.", category = ModuleCategory.MOVEMENT)
public class ModuleStrafe extends Module {

    @Subscribe
    public void onUpdate(final UpdateEvent event) {
        if (Dozer.getSingleton().getModuleManager().getModuleByClass(ModuleFlight.class).isToggled())
            return;
        if (mc.thePlayer.movementInput.moveForward != 0 || mc.thePlayer.movementInput.moveStrafe != 0) {
            UtilMovement.setSpeed(MathHelper.sqrt_double(mc.thePlayer.motionX * mc.thePlayer.motionX + mc.thePlayer.motionZ * mc.thePlayer.motionZ));
        }
    }


}
