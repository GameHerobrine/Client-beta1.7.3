package dozer.systems.module.impl.render;

import dozer.event.Subscribe;
import dozer.event.impl.Render2DEvent;
import dozer.event.impl.Render3DEvent;
import dozer.systems.module.Module;
import dozer.shader.Framebuffer;
import dozer.systems.module.ModuleCategory;
import dozer.systems.module.ModuleInfo;
import dozer.systems.setting.Serialize;
import dozer.systems.setting.annotation.Mode;
import dozer.util.render.shader.FillShader;
import dozer.util.render.shader.OutlineShader;
import dozer.util.render.shader.ShaderProgram;
import net.minecraft.src.ScaledResolution;
import net.minecraft.src.entity.*;
import net.minecraft.src.render.RenderManager;
import net.minecraft.src.world.tile.TileEntity;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

/**
 * @author Signam, MedMex & Eternal
 */
@ModuleInfo(name = "ShaderESP", description = "Draws esp", category = ModuleCategory.RENDER)
public class ModuleShader extends Module {



  @Serialize(name = "Mode")
  @Mode(modes = {
          "Outline",
          "Shader"
  })
  public String mode = "Outline";

  private Framebuffer framebuffer;
  private int w, h, s;

  private final OutlineShader outlineShader = new OutlineShader();
  private final FillShader fillShader = new FillShader();



  @Subscribe
  public void onRender(Render3DEvent event) {

    glEnable(GL_ALPHA_TEST);
    glPushMatrix();
    glPushAttrib(8256);

    ScaledResolution res = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);

    if (framebuffer != null) {
      framebuffer.framebufferClear();

      if (res.getScaledHeight() != h || res.getScaledWidth() != w || res.scaleFactor != s) {
        framebuffer.delete();

        framebuffer = new Framebuffer(mc.displayWidth, mc.displayHeight, false);
        framebuffer.framebufferClear();
      }

      w = res.getScaledWidth();
      h = res.getScaledHeight();
      s = res.scaleFactor;
    } else {
      framebuffer = new Framebuffer(mc.displayWidth, mc.displayHeight, false);
    }

    framebuffer.bindFramebuffer(false);

    mc.entityRenderer.disableLightmap();
    mc.theWorld.loadedEntityList.forEach(
        (entity) -> {
          if (entity != null && !entity.equals(mc.thePlayer) && isValid(entity)) {
            RenderManager.instance.renderEntityStatic(entity, event.partialTicks());
          }
        });

    glEnable(GL_BLEND);
    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

    framebuffer.unbindFramebuffer();
    mc.getFramebuffer().bindFramebuffer(true);

    glPopMatrix();
    mc.entityRenderer.enableLightmap();
    glPopAttrib();
  }

  @Subscribe
  public void onRender2D(Render2DEvent event) {

    ShaderProgram shader = null;

    switch (mode) {
      case "Outline" -> {
        shader = outlineShader;

        outlineShader.setColor(new Color(0xFFAA0000));
        outlineShader.setLineWidth(1);
        outlineShader.useShader();
        outlineShader.updateUniforms();
      }
      case "Fill" -> {
        shader = fillShader;

        fillShader.setColor(new Color(0xFFAA0000));
        fillShader.setLineWidth(1);
        fillShader.useShader();
        fillShader.updateUniforms();
      }
    }

    ScaledResolution res = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
    glBindTexture(GL_TEXTURE_2D, framebuffer.texture);
    glBegin(GL_QUADS);
    glTexCoord2d(0, 1);
    glVertex2d(0, 0);
    glTexCoord2d(0, 0);
    glVertex2d(0, res.getScaledHeight());
    glTexCoord2d(1, 0);
    glVertex2d(res.getScaledWidth(), res.getScaledHeight());
    glTexCoord2d(1, 1);
    glVertex2d(res.getScaledWidth(), 0);
    glEnd();
    shader.stopShader();
  }

  public boolean isValid(Entity e) {
    return true;
  }

  public boolean isValid(TileEntity e) {
    return true;
  }

  public Color getColor(Entity e) {
    if (e instanceof EntityPlayer) {
      return new Color(138, 68, 242);
    }
    if (e instanceof EntityAnimal) {
      return new Color(3, 252, 136);
    }
    if (e instanceof EntityMob) {
      return new Color(242, 68, 68);
    }
    if (e instanceof EntityMinecart || e instanceof EntityBoat) {
      return new Color(242, 233, 68);
    }
    if (e instanceof EntityItem) {
      return new Color(68, 77, 242);
    }
    return Color.WHITE;
  }

  public static int[] getRGB(int hex) {
    int a = hex >> 24 & 0xFF;
    int r = hex >> 16 & 0xFF;
    int g = hex >> 8 & 0xFF;
    int b = hex & 0xFF;
    return new int[] {r, g, b, a};
  }

  public static int rainbowESP(int delay) {
    float rainbowSpeed = 25.0F;
    double rainbowState = Math.ceil((System.currentTimeMillis() + delay)) / 25.0D;
    rainbowState %= 360.0D;
    return Color.getHSBColor((float) (rainbowState / 360.0D), 0.9F, 1.0F).getRGB();
  }

  public static Color SkyRainbow(int counter, float bright, float st) {
    double v1 = Math.ceil((System.currentTimeMillis() + counter * 109L)) / 6.0D;
    return Color.getHSBColor(
        ((float) ((v1 %= 360.0D) / 360.0D) < 0.5D)
            ? -((float) (v1 / 360.0D))
            : (float) (v1 / 360.0D),
        st,
        bright);
  }
}
