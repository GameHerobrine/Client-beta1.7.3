package dozer.module.impl.combat;

import dozer.event.Subscribe;
import dozer.event.impl.UpdateEvent;
import dozer.module.Module;
import dozer.module.ModuleCategory;
import dozer.module.ModuleInfo;
import dozer.setting.impl.SettingBoolean;
import dozer.setting.impl.SettingMode;
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


    public enum Values {
        VALUE1,
        VALUE2,
        VALUE3
    }

    private final SettingNumber range = numberSetting("Range", null, 6, 0, 6, 0.1);
    private final SettingNumber aps = numberSetting("APS", null, 10, 1, 20, 1);
    private final SettingBoolean players = boolSetting("Players", null, true);
    private final SettingBoolean mobs = boolSetting("Mobs", null, true);
    private final SettingBoolean animals = boolSetting("Animals", null, false);
    private final SettingMode<?> testSetting = modeSetting("Test", null, Values.VALUE1, Values.values());


    @Subscribe
    public void onUpdate(final UpdateEvent event) {
        world().ifPresent(world -> {
            List<Entity> entityList = mc.theWorld.loadedEntityList;

            entityList.forEach(entity -> {
                boolean check = isEntityValid(entity) && entity != mc.thePlayer && mc.thePlayer.getDistanceToEntity(entity) <= range.getValue();
                if (check && timer.hasReached((long) (1000L / aps.getValue()))) {
                    mc.playerController.attackEntity(mc.thePlayer, entity);
                    timer.reset();
                }
            });


        });
    }




    public boolean isEntityValid(Entity entity) {
        return (entity instanceof EntityAnimal && animals.getValue()) ||
                (entity instanceof EntityMob && mobs.getValue()) ||
                (entity instanceof EntityPlayer) && players.getValue();

    }

}
