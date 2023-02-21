package dozer.systems.hud;

import dozer.event.Subscribe;
import dozer.event.impl.Render2DEvent;
import dozer.ui.hudEditor.HudScreen;
import dozer.util.UtilClient;
import net.minecraft.client.Minecraft;

public class HUDRenderer implements UtilClient {

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
