package dozer.shader;


import dozer.shader.renderer.DozerTessellator;
import dozer.shader.renderer.OpenGlHelper;
import dozer.shader.renderer.TextureUtil;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;

import java.nio.ByteBuffer;

public class Framebuffer
{
    public int textureWidth;
    public int textureHeight;
    public int width;
    public int height;
    public boolean useDepth;
    public int framebufferObject;
    public int texture;
    public int depthBuffer;
    public float[] color;
    public int filter;
    private static final String __OBFID = "CL_00000959";

    public Framebuffer(int width, int height, boolean depth)
    {
        this.useDepth = depth;
        this.framebufferObject = -1;
        this.texture = -1;
        this.depthBuffer = -1;
        this.color = new float[4];
        this.color[0] = 0.0F;
        this.color[1] = 0.0F;
        this.color[2] = 0.0F;
        this.color[3] = 0.0F;
        this.createBindFramebuffer(width, height);
    }

    public void createBindFramebuffer(int width, int height)
    {
        if (!OpenGlHelper.isFramebufferEnabled())
        {
            this.width = width;
            this.height = height;
        }
        else
        {
            GL11.glEnable(GL11.GL_DEPTH_TEST);

            if (this.framebufferObject >= 0)
            {
                this.delete();
            }

            this.createFramebuffer(width, height);
            this.checkFramebufferComplete();
            EXTFramebufferObject.glBindFramebufferEXT(36160, 0);
        }
    }

    public void delete()
    {
        if (OpenGlHelper.isFramebufferEnabled())
        {
            this.unbindFramebufferTexture();
            this.unbindFramebuffer();

            if (this.depthBuffer > -1)
            {
                EXTFramebufferObject.glDeleteRenderbuffersEXT(this.depthBuffer);
                this.depthBuffer = -1;
            }

            if (this.texture > -1)
            {
                TextureUtil.deleteTexture(this.texture);
                this.texture = -1;
            }

            if (this.framebufferObject > -1)
            {
                EXTFramebufferObject.glBindFramebufferEXT(36160, 0);
                EXTFramebufferObject.glDeleteFramebuffersEXT(this.framebufferObject);
                this.framebufferObject = -1;
            }
        }
    }

    public void createFramebuffer(int p_147605_1_, int p_147605_2_)
    {
        this.width = p_147605_1_;
        this.height = p_147605_2_;
        this.textureWidth = p_147605_1_;
        this.textureHeight = p_147605_2_;

        if (!OpenGlHelper.isFramebufferEnabled())
        {
            this.framebufferClear();
        }
        else
        {
            this.framebufferObject = EXTFramebufferObject.glGenFramebuffersEXT();
            this.texture = TextureUtil.glGenTextures();

            if (this.useDepth)
            {
                this.depthBuffer = EXTFramebufferObject.glGenRenderbuffersEXT();
            }

            this.setFilter(9729);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.texture);
            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, this.textureWidth, this.textureHeight, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (ByteBuffer)null);
            EXTFramebufferObject.glBindFramebufferEXT(36160, this.framebufferObject);
            EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064, 3553, this.texture, 0);

            if (this.useDepth)
            {
                EXTFramebufferObject.glBindRenderbufferEXT(36161, this.depthBuffer);
                EXTFramebufferObject.glRenderbufferStorageEXT(36161, 33190, this.textureWidth, this.textureHeight);
                EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36096, 36161, this.depthBuffer);
            }

            this.framebufferClear();
            this.unbindFramebufferTexture();
        }
    }

    public void setFilter(int p_147607_1_)
    {
        if (OpenGlHelper.isFramebufferEnabled())
        {
            this.filter = p_147607_1_;
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.texture);
            GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, (float)p_147607_1_);
            GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, (float)p_147607_1_);
            GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, 10496.0F);
            GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, 10496.0F);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
        }
    }

    public void checkFramebufferComplete()
    {
        int var1 = EXTFramebufferObject.glCheckFramebufferStatusEXT(36160);

        switch (var1) {
            case 36054 -> throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT_EXT");
            case 36055 -> throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT_EXT");
            case 36057 -> throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_DIMENSIONS_EXT");
            case 36058 -> throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_FORMATS_EXT");
            case 36059 -> throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER_EXT");
            case 36060 -> throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER_EXT");
            default -> throw new RuntimeException("glCheckFramebufferStatusEXT returned unknown status:" + var1);
        }
    }

    public void bindFramebufferTexture()
    {
        if (OpenGlHelper.isFramebufferEnabled())
        {
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.texture);
        }
    }

    public void unbindFramebufferTexture()
    {
        if (OpenGlHelper.isFramebufferEnabled())
        {
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
        }
    }

    public void bindFramebuffer(boolean p_147610_1_)
    {
        if (OpenGlHelper.isFramebufferEnabled())
        {
            EXTFramebufferObject.glBindFramebufferEXT(36160, this.framebufferObject);

            if (p_147610_1_)
            {
                GL11.glViewport(0, 0, this.width, this.height);
            }
        }
    }

    public void unbindFramebuffer()
    {
        if (OpenGlHelper.isFramebufferEnabled())
        {
            EXTFramebufferObject.glBindFramebufferEXT(36160, 0);
        }
    }

    public void setFramebufferColor(float p_147604_1_, float p_147604_2_, float p_147604_3_, float p_147604_4_)
    {
        this.color[0] = p_147604_1_;
        this.color[1] = p_147604_2_;
        this.color[2] = p_147604_3_;
        this.color[3] = p_147604_4_;
    }

    public void framebufferRender(int p_147615_1_, int p_147615_2_)
    {
        if (OpenGlHelper.isFramebufferEnabled())
        {
            GL11.glColorMask(true, true, true, false);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glDepthMask(false);
            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glLoadIdentity();
            GL11.glOrtho(0.0D, p_147615_1_, p_147615_2_, 0.0D, 1000.0D, 3000.0D);
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glLoadIdentity();
            GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
            GL11.glViewport(0, 0, p_147615_1_, p_147615_2_);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glEnable(GL11.GL_COLOR_MATERIAL);
            this.bindFramebufferTexture();
            float var3 = (float)p_147615_1_;
            float var4 = (float)p_147615_2_;
            float var5 = (float)this.width / (float)this.textureWidth;
            float var6 = (float)this.height / (float)this.textureHeight;
            DozerTessellator var7 = DozerTessellator.instance;
            var7.startDrawingQuads();
            var7.setColorOpaque_I(-1);
            var7.addVertexWithUV(0.0D, (double)var4, 0.0D, 0.0D, 0.0D);
            var7.addVertexWithUV((double)var3, (double)var4, 0.0D, (double)var5, 0.0D);
            var7.addVertexWithUV((double)var3, 0.0D, 0.0D, (double)var5, (double)var6);
            var7.addVertexWithUV(0.0D, 0.0D, 0.0D, 0.0D, (double)var6);
            var7.draw();
            this.unbindFramebufferTexture();
            GL11.glDepthMask(true);
            GL11.glColorMask(true, true, true, true);
        }
    }

    public void framebufferClear()
    {
        this.bindFramebuffer(true);
        GL11.glClearColor(this.color[0], this.color[1], this.color[2], this.color[3]);
        int var1 = 16384;

        if (this.useDepth)
        {
            GL11.glClearDepth(1.0D);
            var1 |= 256;
        }

        GL11.glClear(var1);
        this.unbindFramebuffer();
    }
}
