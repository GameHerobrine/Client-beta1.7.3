package dozer.systems.module.impl.combat;

import dozer.event.Subscribe;
import dozer.event.impl.UpdateEvent;
import dozer.systems.module.Module;
import dozer.systems.module.ModuleCategory;
import dozer.systems.module.ModuleInfo;
import dozer.systems.setting.Serialize;
import dozer.systems.setting.annotation.Slider;
import dozer.util.misc.TimerUtil;
import net.minecraft.src.entity.Entity;
import net.minecraft.src.entity.EntityPlayer;
import org.lwjgl.input.Keyboard;

import java.util.function.Predicate;

@ModuleInfo(
    name = "ForceField",
    description = "Attacks entities around you.",
    category = ModuleCategory.COMBAT,
    keyCode = Keyboard.KEY_R)
public class ModuleForceField extends Module {

  private final TimerUtil timer = new TimerUtil();
  private final Predicate<Entity> entityPredicate = entity ->
      entity != mc.thePlayer &&
      entity instanceof EntityPlayer;

  @Serialize(name = "range")
  @Slider(min = 3, max = 8, increment = 0.1)
  public double range = 4.2;

  @Serialize(name = "aps")
  @Slider(min = 4, max = 20, increment = 0.1)
  public double aps = 12;

  @Subscribe
  public void onUpdate(final UpdateEvent event) {

    if(!event.isPre()) return;
    final Entity target = mc.theWorld.loadedEntityList.stream().filter(entityPredicate).findFirst().orElse(null);
    if(target == null) return;

    if(timer.hasReached((long) aps * 50)) {
      mc.thePlayer.attackTargetEntityWithCurrentItem(target);
    }
  }



}
