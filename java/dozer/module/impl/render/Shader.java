package dozer.module.impl.render;





import dozer.event.Subscribe;
import dozer.event.impl.Render2DEvent;
import dozer.event.impl.Render3DEvent;
import dozer.module.ModuleCategory;
import dozer.module.ModuleInfo;
import dozer.module.Module;
import dozer.shader.Framebuffer;
import dozer.util.render.shader.MedShader;
import dozer.util.render.shader.OutlineShader;
import net.minecraft.src.ScaledResolution;
import net.minecraft.src.entity.*;
import net.minecraft.src.render.RenderHelper;
import net.minecraft.src.render.RenderManager;
import net.minecraft.src.world.tile.TileEntity;
import org.checkerframework.checker.units.qual.C;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

//Big shoutout to Aestheticall <3333
@ModuleInfo(name = "ShaderESP", description = "Draws esp", category = ModuleCategory.RENDER)
public class Shader extends Module{

	public static Shader instance;





	private Framebuffer framebuffer;
    private int w, h, s;

    private final OutlineShader outlineShader = new OutlineShader();

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

					framebuffer = new Framebuffer(mc.displayWidth, mc.displayHeight, true);
					framebuffer.framebufferClear();
				}

				w = res.getScaledWidth();
				h = res.getScaledHeight();
				s = res.scaleFactor;
			} else {
				framebuffer = new Framebuffer(mc.displayWidth, mc.displayHeight, true);
			}

			framebuffer.bindFramebuffer(false);

			mc.entityRenderer.setupCameraTransform(event.partialTicks(), 0);

			mc.theWorld.loadedEntityList.forEach((entity) -> {
				if (entity != null && !entity.equals(mc.thePlayer) && isValid((Entity) entity)) {
					RenderManager.instance.renderEntityStatic(entity, event.partialTicks());
				}
			});


			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

			framebuffer.unbindFramebuffer();
			mc.getFramebuffer().bindFramebuffer(true);

			mc.entityRenderer.enableLightmap();
			mc.entityRenderer.disableLightmap();
			RenderHelper.disableStandardItemLighting();

			glPushMatrix();

		outlineShader.setColor(new Color(255, 255, 255));
			outlineShader.setColor(new Color(SkyRainbow(20, 1.0F, 0.5F).getRed(), SkyRainbow(20, 1.0F, 0.5F).getGreen(), SkyRainbow(20, 1.0F, 0.5F).getBlue()));
			outlineShader.setLineWidth(1);

			outlineShader.use();
			outlineShader.updateUniforms();


			mc.entityRenderer.setupOverlayRendering();

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

			outlineShader.stopUse();
			glPopMatrix();

			mc.entityRenderer.enableLightmap();

			glPopMatrix();
			glPopAttrib();

			mc.entityRenderer.setupOverlayRendering();

	}
	


	
	public boolean isValid(Entity e) {
		return true;
	}
	
	public boolean isValid(TileEntity e) {
		return true;
	}
	
	public Color getColor(Entity e) {
		if(e instanceof EntityPlayer) {
			return new Color(138, 68, 242);
		}
		if(e instanceof EntityAnimal) {
			return new Color(3, 252, 136);
		}
		if(e instanceof EntityMob) {
			return new Color(242, 68, 68);
		}
		if(e instanceof EntityMinecart || e instanceof EntityBoat) {
			return new Color(242, 233, 68);
		}
		if(e instanceof EntityItem) {
			return new Color(68, 77, 242);
		}
		return Color.WHITE;
	}


	public static int[] getRGB(int hex) {
	    int a = hex >> 24 & 0xFF;
	    int r = hex >> 16 & 0xFF;
	    int g = hex >> 8 & 0xFF;
	    int b = hex & 0xFF;
	    return new int[] { r, g, b, a };
	  }
	  
	  public static int rainbowESP(int delay) {
	    float rainbowSpeed = 25.0F;
	    double rainbowState = Math.ceil((System.currentTimeMillis() + delay)) / 25.0D;
	    rainbowState %= 360.0D;
	    return Color.getHSBColor((float)(rainbowState / 360.0D), 0.9F, 1.0F).getRGB();
	  }
	  
	  public static Color SkyRainbow(int counter, float bright, float st) {
	    double v1 = Math.ceil((System.currentTimeMillis() + counter * 109L)) / 6.0D;
	    return Color.getHSBColor(((float)((v1 %= 360.0D) / 360.0D) < 0.5D) ? -((float)(v1 / 360.0D)) : (float)(v1 / 360.0D), st, bright);
	  }
	
	
}
