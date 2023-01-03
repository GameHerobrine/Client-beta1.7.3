package dozer.util.render.shader;

import java.awt.*;

import net.minecraft.client.Minecraft;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glUniform1f;

public class FillShader extends ShaderUtil {
    private Color color = Color.white;
    private float lineWidth = 1.5f;
    Minecraft mc = Minecraft.getMinecraft();

    public FillShader() {
        super("shaders/esp/vertex.fsh","shaders/esp/fill.fsh");
    }

    public void updateUniforms() {
        glUniform1i(getUniform("texture"), 0);
        glUniform2f(getUniform("texelSize"), 1F / mc.displayWidth, 1F / mc.displayHeight);
        glUniform4f(getUniform("color"), color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, color.getAlpha() / 255F);
        glUniform1f(getUniform("radius"), lineWidth);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public float getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
    }
}