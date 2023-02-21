package dozer.systems.module.impl.misc;

import dozer.Dozer;
import dozer.event.Subscribe;
import dozer.event.impl.UpdateEvent;
import dozer.systems.module.Module;
import dozer.systems.module.ModuleCategory;
import dozer.systems.module.ModuleInfo;
import dozer.systems.module.impl.movement.ModuleFlight;
import dozer.util.misc.UtilTimer;
import net.minecraft.src.network.packets.Packet10Flying;

@ModuleInfo(name = "NoFallDamage", description = "Prevents you from taking fall damage.", category = ModuleCategory.MISC)
public class ModuleNoFallDamage extends Module {

    @Subscribe
    public void onUpdate(final UpdateEvent event) {
        if (Dozer.getSingleton().getModuleManager().getModuleByClass(ModuleFlight.class).isToggled())
            return;
        if (mc.theWorld.multiplayerWorld) {
            if (mc.thePlayer.fallDistance > 2F) {
                mc.getSendQueue().addToSendQueue(new Packet10Flying(true));
                mc.thePlayer.fallDistance = 0.0F;
            }
        } else {
            if (mc.thePlayer.fallDistance > 2F) {
                mc.thePlayer.fallDistance = 0.0F;
            }
        }
    }

}
