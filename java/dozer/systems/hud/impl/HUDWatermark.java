package dozer.systems.hud.impl;

import dozer.Dozer;
import dozer.systems.hud.HUDElement;
import dozer.systems.hud.HUDElementInfo;

import java.awt.*;

@HUDElementInfo(name = "Watermark", description = "Displays the client name and version.", x = 4, y = 4)
public class HUDWatermark extends HUDElement {

    @Override
    public void render() {
        drawStringWithShadow(Dozer.getSingleton().getName() + " " + Dozer.getSingleton().getVersion(), getX(), getY(), Color.WHITE);
    }

    @Override
    public void setDimensions(int width, int height) {
        super.setDimensions(fontRenderer().getStringWidth(Dozer.getSingleton().getName() + " " + Dozer.getSingleton().getVersion()) + 1, 9);
    }

}
