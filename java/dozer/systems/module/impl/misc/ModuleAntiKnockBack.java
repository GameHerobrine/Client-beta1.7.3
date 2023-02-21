package dozer.systems.module.impl.misc;

import dozer.event.Subscribe;
import dozer.event.impl.PacketEvent;
import dozer.systems.module.Module;
import dozer.systems.module.ModuleCategory;
import dozer.systems.module.ModuleInfo;
import net.minecraft.src.network.packets.Packet28EntityVelocity;

@ModuleInfo(name = "AntiKnockBack", description = "Prevents the player from being knocked back.", category = ModuleCategory.MISC)
public class ModuleAntiKnockBack extends Module {

    @Subscribe
    public void onPacket(final PacketEvent event) {
        if (mc.thePlayer == null) {
            return;
        }
        if (event.getPacket() instanceof Packet28EntityVelocity packet) {
            if (packet.entityId == mc.thePlayer.entityId) {
                event.setCancelled(true);
            }
        }
    }

}
