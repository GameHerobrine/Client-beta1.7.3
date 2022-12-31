package dozer.systems.hud.impl;

import dozer.systems.hud.HudElement;
import dozer.systems.hud.HudElementInfo;
import net.minecraft.client.Minecraft;

import java.awt.*;


@HudElementInfo(name = "Test", description = "test", x = 250, y = 40)
public class TestHud extends HudElement {
    @Override
    public void render() {
        drawStringWithShadow(getName(), getX(), getY(), Color.WHITE);
    }


    @Override
    public void setDimensions(int width, int height) {
        super.setDimensions(fontRenderer().getStringWidth(getName()), 12);
    }
}
