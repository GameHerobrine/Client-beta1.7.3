package dozer.systems.module.impl.player;

import dozer.event.Subscribe;
import dozer.event.impl.PacketEvent;
import dozer.systems.module.Module;
import dozer.systems.module.ModuleCategory;
import dozer.systems.module.ModuleInfo;
import net.minecraft.src.network.packets.Packet28EntityVelocity;

@ModuleInfo(name = "AntiKnockBack", description = "Prevents the player from being knocked back.", category = ModuleCategory.PLAYER)
public class AntiKnockBack extends Module {

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
