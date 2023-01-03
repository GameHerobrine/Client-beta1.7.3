package dozer.shader;

import com.google.common.collect.Lists;
import dozer.shader.renderer.DozerTessellator;
import dozer.shader.resources.IResourceManager;
import dozer.shader.util.JsonException;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

import javax.vecmath.Matrix4f;
import java.util.List;

public class Shader
{
    private final ShaderManager manager;
    public final Framebuffer framebufferIn;
    public final Framebuffer framebufferOut;
    private final List<Framebuffer> listAuxFramebuffers = Lists.newArrayList();
    private final List<String> listAuxNames = Lists.newArrayList();
    private final List<Integer> listAuxWidths = Lists.newArrayList();
    private final List<Integer> listAuxHeights = Lists.newArrayList();
    private Matrix4f projectionMatrix;

    public Shader(IResourceManager p_i45089_1_, String p_i45089_2_, Framebuffer p_i45089_3_, Framebuffer p_i45089_4_) throws JsonException
    {
        this.manager = new ShaderManager(p_i45089_1_, p_i45089_2_);
        this.framebufferIn = p_i45089_3_;
        this.framebufferOut = p_i45089_4_;
    }

    public void deleteShader()
    {
        this.manager.func_147988_a();
    }

    public void addAuxFramebuffer(String name, Framebuffer framebuffer, int width, int height)
    {
        this.listAuxNames.add(name);
        this.listAuxFramebuffers.add(framebuffer);
        this.listAuxWidths.add(width);
        this.listAuxHeights.add(height);
    }

    private void preLoadShader()
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glDisable(GL11.GL_FOG);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_COLOR_MATERIAL);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
    }

    public void setProjectionMatrix(Matrix4f p_148045_1_)
    {
        this.projectionMatrix = p_148045_1_;
    }

    public void loadShader(float p_148042_1_)
    {
        this.preLoadShader();
        this.framebufferIn.unbindFramebuffer();
        float var2 = (float)this.framebufferOut.textureWidth;
        float var3 = (float)this.framebufferOut.textureHeight;
        GL11.glViewport(0, 0, (int)var2, (int)var3);
        this.manager.func_147992_a("DiffuseSampler", this.framebufferIn);

        for (int var4 = 0; var4 < this.listAuxFramebuffers.size(); ++var4)
        {
            this.manager.func_147992_a(this.listAuxNames.get(var4), this.listAuxFramebuffers.get(var4));
            this.manager.func_147984_b("AuxSize" + var4).func_148087_a((float) this.listAuxWidths.get(var4), (float) this.listAuxHeights.get(var4));
        }

        this.manager.func_147984_b("ProjMat").func_148088_a(this.projectionMatrix);
        this.manager.func_147984_b("InSize").func_148087_a((float)this.framebufferIn.textureWidth, (float)this.framebufferIn.textureHeight);
        this.manager.func_147984_b("OutSize").func_148087_a(var2, var3);
        this.manager.func_147984_b("Time").func_148090_a(p_148042_1_);
        Minecraft var8 = Minecraft.getMinecraft();
        this.manager.func_147984_b("ScreenSize").func_148087_a((float)var8.displayWidth, (float)var8.displayHeight);
        this.manager.func_147995_c();
        this.framebufferOut.framebufferClear();
        this.framebufferOut.bindFramebuffer(false);
        GL11.glDepthMask(false);
        GL11.glColorMask(true, true, true, false);
        DozerTessellator var5 = DozerTessellator.instance;
        var5.startDrawingQuads();
        var5.setColorOpaque_I(-1);
        var5.addVertex(0.0D, (double)var3, 500.0D);
        var5.addVertex((double)var2, (double)var3, 500.0D);
        var5.addVertex((double)var2, 0.0D, 500.0D);
        var5.addVertex(0.0D, 0.0D, 500.0D);
        var5.draw();
        GL11.glDepthMask(true);
        GL11.glColorMask(true, true, true, true);
        this.manager.func_147993_b();
        this.framebufferOut.unbindFramebuffer();
        this.framebufferIn.unbindFramebufferTexture();

        for (Framebuffer var7 : this.listAuxFramebuffers) {
            if (var7 != null) {
                var7.unbindFramebufferTexture();
            }
        }
    }

    public ShaderManager getShaderManager()
    {
        return this.manager;
    }
}
