package dozer.module.impl.combat;

import dozer.event.Subscribe;
import dozer.event.impl.UpdateEvent;
import dozer.module.Module;
import dozer.module.ModuleCategory;
import dozer.module.ModuleInfo;
import dozer.setting.impl.SettingNumber;
import dozer.util.misc.TimerUtil;
import net.minecraft.src.entity.Entity;
import net.minecraft.src.entity.EntityAnimal;
import net.minecraft.src.entity.EntityMob;
import net.minecraft.src.entity.EntityPlayer;
import org.lwjgl.input.Keyboard;

import java.util.List;

@ModuleInfo(name = "ForceField", description = "Attacks entities around you.", category = ModuleCategory.COMBAT, keyCode = Keyboard.KEY_R)
public class ForceField extends Module {

    private final TimerUtil timer = new TimerUtil();

    private final SettingNumber range = new SettingNumber("Range", this, null, 6, 0, 6, 0.1);
    private final SettingNumber aps = new SettingNumber("APS", this, null, 10, 1, 20, 1);

    public Entity target;

    public ForceField() {
        this.addSettings(range, aps);
    }

    public void onDisable() {
        target = null;
    }

    @Subscribe
    public void onUpdate(final UpdateEvent event) {
        List entityList = mc.theWorld.loadedEntityList;

        for (int i = 0; i < entityList.size(); i++) {
            boolean check = isEntityValid((Entity) entityList.get(i)) && entityList.get(i) != mc.thePlayer && mc.thePlayer.getDistanceToEntity((Entity) entityList.get(i)) <= range.getValue();
            if (check && timer.hasReached((long) (1000L / aps.getValue()))) {
                mc.playerController.attackEntity(mc.thePlayer, (Entity) entityList.get(i));
                timer.reset();
            }
        }
        if (target != null && mc.thePlayer.getDistanceToEntity(target) > range.getValue()) {
            target = null;
        }
    }

    public boolean isEntityValid(Entity entity) {
        if (entity instanceof EntityPlayer) {
            return true;
        }
        if (entity instanceof EntityAnimal) {
            return true;
        }
        if (entity instanceof EntityMob) {
            return true;
        }
        return false;
    }

}
