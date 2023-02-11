package dozer.systems.module.impl.misc;

import dozer.event.Subscribe;
import dozer.event.impl.PacketEvent;
import dozer.event.impl.UpdateEvent;
import dozer.systems.module.Module;
import dozer.systems.module.ModuleCategory;
import dozer.systems.module.ModuleInfo;
import dozer.util.player.MovementUtil;
import net.minecraft.src.network.packets.Packet0KeepAlive;
import net.minecraft.src.network.packets.Packet11PlayerPosition;
import net.minecraft.src.network.packets.Packet12PlayerLook;
import net.minecraft.src.network.packets.Packet13PlayerLookMove;
import org.lwjgl.input.Keyboard;

@ModuleInfo(name = "FreeCam", description = "Allows you to move out of your body.", category = ModuleCategory.MISC)
public class ModuleFreeCam extends Module {


    private double x, y, z;
    private float yaw, pitch;

    @Override
    public void onEnable() {
        x = mc.thePlayer.posX;
        y = mc.thePlayer.posY;
        z = mc.thePlayer.posZ;
        yaw = mc.thePlayer.rotationYaw;
        pitch = mc.thePlayer.rotationPitch;
    }

    @Override
    public void onDisable() {
        MovementUtil.setSpeed(0.0);
        mc.thePlayer.setPosition(x, y, z);
        mc.thePlayer.rotationYaw = yaw;
        mc.thePlayer.rotationPitch = pitch;
        mc.thePlayer.noClip = false;
    }

    @Subscribe
    public void onUpdate(final UpdateEvent event) {
        mc.thePlayer.noClip = true;
        mc.thePlayer.motionY = 0.0;
        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            mc.thePlayer.motionY += 0.25;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
            mc.thePlayer.motionY -= 0.25;
        }
        if (mc.thePlayer.movementInput.moveForward != 0 || mc.thePlayer.movementInput.moveStrafe != 0) {
            MovementUtil.setSpeed(0.25);
        }
    }

    @Subscribe
    public void onPacket(final PacketEvent event) {
        if (event.getPacket() instanceof Packet0KeepAlive) {
            event.setCancelled(true);
        }
        if (event.getPacket() instanceof Packet11PlayerPosition) {
            event.setCancelled(true);
        }
        if (event.getPacket() instanceof Packet12PlayerLook) {
            event.setCancelled(true);
        }
        if (event.getPacket() instanceof Packet13PlayerLookMove) {
            event.setCancelled(true);
        }
    }

}
