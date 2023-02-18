package dozer.systems.hud.impl;

import dozer.Dozer;
import dozer.systems.hud.HudElement;
import dozer.systems.hud.HudElementInfo;
import dozer.systems.module.Module;

import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

@HudElementInfo(name = "Arraylist", description = "Displays enabled modules.", x = 4, y = 14)
public class HudModuleArraylist extends HudElement {

    @Override
    public void render() {
        CopyOnWriteArrayList<Module> modules = Dozer.getSingleton().getModuleManager().getEnabledModules();

        int y = getY();
        for (Module module : modules) {
            drawStringWithShadow(module.getName(), getX(), y, Color.WHITE);
            y += 10;
        }
    }

    @Override
    public void setDimensions(int width, int height) {
        // for each module * 10
        super.setDimensions(100, getY() + (Dozer.getSingleton().getModuleManager().getEnabledModules().size() * 8));
    }

}
