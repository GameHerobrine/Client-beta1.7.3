package dozer.module.impl.player;

import dozer.event.Subscribe;
import dozer.event.impl.PacketEvent;
import dozer.module.Module;
import dozer.module.ModuleCategory;
import dozer.module.ModuleInfo;
import net.minecraft.src.Packet28EntityVelocity;

@ModuleInfo(name = "AntiKnockBack", description = "Prevents the player from being knocked back.", category = ModuleCategory.PLAYER)
public class AntiKnockBack extends Module {

    @Subscribe
    public void onPacket(final PacketEvent event) {
        if (mc.thePlayer == null) {
            return;
        }
        if (event.getPacket() instanceof Packet28EntityVelocity) {
            Packet28EntityVelocity packet = (Packet28EntityVelocity) event.getPacket();
            if (packet.entityId == mc.thePlayer.entityId) {
                event.setCancelled(true);
            }
        }
    }

}
