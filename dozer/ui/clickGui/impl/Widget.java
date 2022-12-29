package dozer.ui.clickGui.impl;

import dozer.util.render.Render2DUtil;
import java.awt.*;

public abstract class Widget implements Render2DUtil {

    public int x, y, width, height;
    public Color color;


    public Widget(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public abstract void drawScreen(int mouseX, int mouseY, float partialTicks);
    public abstract void mouseClicked(int mouseX, int mouseY, int mouseButton);
    public abstract void mouseReleased(int mouseX, int mouseY, int state);
    public abstract void keyTyped(char character, int keyCode);

    public boolean isHovering(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY > y && mouseY <= y + height;
    }



}
