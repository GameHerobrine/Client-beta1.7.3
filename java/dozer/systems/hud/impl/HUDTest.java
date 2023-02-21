package dozer.systems.hud.impl;

import dozer.systems.hud.HUDElement;
import dozer.systems.hud.HUDElementInfo;
import java.awt.*;

@HUDElementInfo(name = "Test", description = "test", x = 250, y = 40)
public class HUDTest extends HUDElement {

    @Override
    public void render() {
        drawStringWithShadow(getName(), getX(), getY(), Color.WHITE);
    }

    @Override
    public void setDimensions(int width, int height) {
        super.setDimensions(fontRenderer().getStringWidth(getName()), 12);
    }

}
