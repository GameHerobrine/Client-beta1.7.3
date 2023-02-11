package dozer.systems.module.impl.player;

import dozer.Dozer;
import dozer.event.Subscribe;
import dozer.event.impl.UpdateEvent;
import dozer.systems.module.Module;
import dozer.systems.module.ModuleCategory;
import dozer.systems.module.ModuleInfo;
import dozer.systems.module.impl.movement.Flight;
import dozer.util.misc.TimerUtil;
import net.minecraft.src.network.packets.Packet10Flying;

@ModuleInfo(name = "NoFallDamage", description = "Prevents you from taking fall damage.", category = ModuleCategory.PLAYER)
public class NoFallDamage extends Module {

    private final TimerUtil timer = new TimerUtil();

    @Subscribe
    public void onUpdate(final UpdateEvent event) {
        if (Dozer.getSingleton().getModuleManager().getModuleByClass(Flight.class).isToggled())
            return;
        if (mc.theWorld.multiplayerWorld) {
            if (mc.thePlayer.fallDistance > 2.5F) {
                mc.getSendQueue().addToSendQueue(new Packet10Flying(true));
                mc.thePlayer.fallDistance = 0.0F;
                timer.reset();
            }
        } else {
            if (mc.thePlayer.fallDistance > 2.5F) {
                mc.thePlayer.fallDistance = 0.0F;
            }
        }
    }

}
