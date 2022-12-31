package dozer.util.render;

import net.minecraft.client.Minecraft;
import net.minecraft.src.FontRenderer;
import net.minecraft.src.gui.Gui;

import java.awt.*;

public interface Render2DUtil {


    /**
     * returns minecraft font renderer
     *
     * @return FontRenderer
     */
    default FontRenderer fontRenderer() {
        return Minecraft.getMinecraft().fontRenderer;
    }

    /**
     * draw rect with mc render engine
     *
     * @param x x coordinate
     * @param y y coordinate
     * @param width x + width for real position
     * @param height y + height for real position
     * @param color convert Color to integer
     */
    default void drawRect(int x, int y, int width, int height, Color color) {
        Gui.drawRect(x, y, width, height, color.getRGB());
    }


    /**
     *
     * string with shadow using default mc font renderer
     *
     * @param text string parameter
     * @param x x coordinate
     * @param y y coordinate
     * @param color convert Color to integer
     */
    default void drawStringWithShadow(String text, int x, int y, Color color) {
        fontRenderer().drawStringWithShadow(text, x, y, color.getRGB());
    }

}
