package dozer.util;

import net.minecraft.client.Minecraft;
import net.minecraft.src.entity.EntityPlayerSP;
import net.minecraft.src.world.World;

import java.util.Optional;

public interface UtilMinecraft {


    Minecraft mc = Minecraft.getMinecraft();

    default Optional<EntityPlayerSP> player() {
        return Optional.ofNullable(mc.thePlayer);
    }

    default Optional<World> world() {
        return Optional.ofNullable(mc.theWorld);
    }

}
