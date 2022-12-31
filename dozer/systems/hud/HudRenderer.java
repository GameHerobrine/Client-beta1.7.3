package dozer.systems.hud;

import dozer.Dozer;
import dozer.event.Subscribe;
import dozer.event.impl.Render2DEvent;
import dozer.ui.hudEditor.HudScreen;
import dozer.util.ClientUtil;
import dozer.util.MinecraftUtil;
import net.minecraft.client.Minecraft;

public class HudRenderer implements ClientUtil {


    public void init() {
      getEventBus().register(this);
    }


    @Subscribe
    public void onRender(Render2DEvent event) {

        if(Minecraft.getMinecraft().currentScreen instanceof HudScreen) return;

       getDozer().getHudManager().getHudModules().forEach(hudElement -> {
            if(hudElement.isToggled()) hudElement.render();
       });
    }

}
