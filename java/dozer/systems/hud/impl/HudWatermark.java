package dozer.systems.hud.impl;

import dozer.Dozer;
import dozer.systems.hud.HudElement;
import dozer.systems.hud.HudElementInfo;

import java.awt.*;

@HudElementInfo(name = "Watermark", description = "Displays the client name and version.", x = 4, y = 4)
public class HudWatermark extends HudElement {

    @Override
    public void render() {
        drawStringWithShadow(Dozer.getSingleton().getName() + " " + Dozer.getSingleton().getVersion(), getX(), getY(), Color.WHITE);
    }

    @Override
    public void setDimensions(int width, int height) {
        super.setDimensions(fontRenderer().getStringWidth(Dozer.getSingleton().getName() + " " + Dozer.getSingleton().getVersion()) + 1, 9);
    }

}
