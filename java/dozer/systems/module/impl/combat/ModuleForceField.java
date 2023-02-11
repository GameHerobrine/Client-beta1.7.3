package dozer.systems.module.impl.combat;

import dozer.event.Subscribe;
import dozer.event.impl.UpdateEvent;
import dozer.systems.module.Module;
import dozer.systems.module.ModuleCategory;
import dozer.systems.module.ModuleInfo;
import dozer.systems.setting.Serialize;
import dozer.systems.setting.annotation.CheckBox;
import dozer.systems.setting.annotation.Slider;
import dozer.util.misc.UtilTimer;
import net.minecraft.src.entity.Entity;
import net.minecraft.src.entity.EntityAnimal;
import net.minecraft.src.entity.EntityMob;
import net.minecraft.src.entity.EntityPlayer;
import org.lwjgl.input.Keyboard;

import java.util.List;

@ModuleInfo(name = "ForceField", description = "Attacks entities around you.", category = ModuleCategory.COMBAT, keyCode = Keyboard.KEY_R)
public class ModuleForceField extends Module {

  private final UtilTimer timer = new UtilTimer();
  public Entity target;

  @Serialize(name = "range")
  @Slider(min = 3, max = 6, increment = 0.1)
  public double range = 6;

  @Serialize(name = "aps")
  @Slider(min = 4, max = 20, increment = 0.1)
  public double aps = 12;

  @Serialize(name = "attackPlayers")
  @CheckBox
  public boolean attackPlayers = true;

  @Serialize(name = "attackMobs")
  @CheckBox
  public boolean attackMobs = true;

  @Serialize(name = "attackAnimals")
  @CheckBox
  public boolean attackAnimals = true;

  @Override
  public void onDisable() {
    target = null;
  }

  @Subscribe
  public void onUpdate(final UpdateEvent event) {
    List entityList = mc.theWorld.loadedEntityList;

    for (int i = 0; i < entityList.size(); i++) {
      boolean check = isEntityValid((Entity) entityList.get(i)) && entityList.get(i) != mc.thePlayer && mc.thePlayer.getDistanceToEntity((Entity) entityList.get(i)) <= range;
      if (check) {
        mc.playerController.attackEntity(mc.thePlayer, (Entity) entityList.get(i));
        timer.reset();
      }
    }
    if(target != null && mc.thePlayer.getDistanceToEntity(target) > range) {
      target = null;
    }
  }

  public boolean isEntityValid(Entity entity) {
    if (entity == mc.thePlayer) {
      return false;
    }
    if(entity instanceof EntityPlayer && attackPlayers) {
      return true;
    }
    if(entity instanceof EntityAnimal && attackAnimals) {
      return true;
    }
    if(entity instanceof EntityMob && attackMobs) {
      return true;
    }
    return false;
  }

}
