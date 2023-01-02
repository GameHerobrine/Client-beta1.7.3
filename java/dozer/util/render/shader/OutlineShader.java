package dozer.util.render.shader;

import java.awt.Color;

import net.minecraft.client.Minecraft;

import static org.lwjgl.opengl.GL20.*;


public class OutlineShader extends MedShader {
    private Color color = Color.white;
    private float lineWidth = 1.5f;
    Minecraft mc = Minecraft.getMinecraft();

    public OutlineShader() {
        super("/shaders/esp/outline.fsh", "shaders/esp/vertex.fsh");
    }

    @Override
    protected void onInitialize() {
        createUniform("texture");
        createUniform("texelSize");
        createUniform("color");
        createUniform("divider");
        createUniform("radius");
        createUniform("maxSample");
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