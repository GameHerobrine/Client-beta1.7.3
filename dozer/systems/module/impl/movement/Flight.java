package dozer.systems.module.impl.movement;

import dozer.event.Subscribe;
import dozer.event.impl.UpdateEvent;
import dozer.systems.module.Module;
import dozer.systems.module.ModuleCategory;
import dozer.systems.module.ModuleInfo;
import dozer.util.player.MovementUtil;
import net.minecraft.src.MathHelper;
import org.lwjgl.input.Keyboard;

@ModuleInfo(name = "Flight", description = "Allows you to fly.", category = ModuleCategory.MOVEMENT)
public class Flight extends Module {




    @Subscribe
    public void onUpdate(final UpdateEvent event) {
        mc.thePlayer.motionY = 0.0;
        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            mc.thePlayer.motionY += 0.08;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
            mc.thePlayer.motionY -= 0.08;
        }
        if (mc.thePlayer.movementInput.moveForward != 0 || mc.thePlayer.movementInput.moveStrafe != 0) {
            MovementUtil.setSpeed(MathHelper.sqrt_double(mc.thePlayer.motionX * mc.thePlayer.motionX + mc.thePlayer.motionZ * mc.thePlayer.motionZ));
        }
    }

}
