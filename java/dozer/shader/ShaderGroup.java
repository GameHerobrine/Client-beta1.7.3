package dozer.shader;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dozer.shader.resources.IResource;
import dozer.shader.resources.IResourceManager;
import dozer.shader.resources.ResourceLocation;
import dozer.shader.util.JsonException;
import dozer.shader.util.JsonUtils;
import org.apache.commons.io.IOUtils;

import javax.vecmath.Matrix4f;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ShaderGroup
{
    private final Framebuffer mainFramebuffer;
    private final IResourceManager resourceManager;
    private final String shaderGroupName;
    private final List<Shader> listShaders = Lists.newArrayList();
    private final Map<String, Framebuffer> mapFramebuffers = Maps.newHashMap();
    private final List<Framebuffer> listFramebuffers = Lists.newArrayList();
    private Matrix4f projectionMatrix;
    private int mainFramebufferWidth;
    private int mainFramebufferHeight;
    private float field_148036_j;
    private float field_148037_k;
    private static final String __OBFID = "CL_00001041";

    public ShaderGroup(IResourceManager p_i45088_1_, Framebuffer p_i45088_2_, ResourceLocation p_i45088_3_) throws JsonException
    {
        this.resourceManager = p_i45088_1_;
        this.mainFramebuffer = p_i45088_2_;
        this.field_148036_j = 0.0F;
        this.field_148037_k = 0.0F;
        this.mainFramebufferWidth = p_i45088_2_.width;
        this.mainFramebufferHeight = p_i45088_2_.height;
        this.shaderGroupName = p_i45088_3_.toString();
        this.resetProjectionMatrix();
        this.initFromLocation(p_i45088_3_);
    }

    public void initFromLocation(ResourceLocation p_148025_1_) throws JsonException
    {
        JsonParser var2 = new JsonParser();
        InputStream var3 = null;

        try
        {
            IResource var4 = this.resourceManager.getResource(p_148025_1_);
            var3 = var4.getInputStream();
            JsonObject var21 = var2.parse(IOUtils.toString(var3, Charsets.UTF_8)).getAsJsonObject();
            JsonArray var6;
            int var7;
            Iterator var8;
            JsonElement var9;
            JsonException var11;

            if (JsonUtils.jsonObjectFieldTypeIsArray(var21, "targets"))
            {
                var6 = var21.getAsJsonArray("targets");
                var7 = 0;

                for (var8 = var6.iterator(); var8.hasNext(); ++var7)
                {
                    var9 = (JsonElement)var8.next();

                    try
                    {
                        this.initTarget(var9);
                    }
                    catch (Exception var18)
                    {
                        var11 = JsonException.func_151379_a(var18);
                        var11.func_151380_a("targets[" + var7 + "]");
                        throw var11;
                    }
                }
            }

            if (JsonUtils.jsonObjectFieldTypeIsArray(var21, "passes"))
            {
                var6 = var21.getAsJsonArray("passes");
                var7 = 0;

                for (var8 = var6.iterator(); var8.hasNext(); ++var7)
                {
                    var9 = (JsonElement)var8.next();

                    try
                    {
                        this.initPass(var9);
                    }
                    catch (Exception var17)
                    {
                        var11 = JsonException.func_151379_a(var17);
                        var11.func_151380_a("passes[" + var7 + "]");
                        throw var11;
                    }
                }
            }
        }
        catch (Exception var19)
        {
            JsonException var5 = JsonException.func_151379_a(var19);
            var5.func_151381_b(p_148025_1_.getResourcePath());
            throw var5;
        }
        finally
        {
            IOUtils.closeQuietly(var3);
        }
    }

    private void initTarget(JsonElement p_148027_1_) throws JsonException
    {
        if (JsonUtils.jsonElementTypeIsString(p_148027_1_))
        {
            this.addFramebuffer(p_148027_1_.getAsString(), this.mainFramebufferWidth, this.mainFramebufferHeight);
        }
        else
        {
            JsonObject var2 = JsonUtils.getJsonElementAsJsonObject(p_148027_1_, "target");
            String var3 = JsonUtils.getJsonObjectStringFieldValue(var2, "name");
            int var4 = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var2, "width", this.mainFramebufferWidth);
            int var5 = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var2, "height", this.mainFramebufferHeight);

            if (this.mapFramebuffers.containsKey(var3))
            {
                throw new JsonException(var3 + " is already defined");
            }

            this.addFramebuffer(var3, var4, var5);
        }
    }

    private void initPass(JsonElement p_148019_1_) throws JsonException
    {
        JsonObject pass = JsonUtils.getJsonElementAsJsonObject(p_148019_1_, "pass");
        String name = JsonUtils.getJsonObjectStringFieldValue(pass, "name");
        String inTarget = JsonUtils.getJsonObjectStringFieldValue(pass, "intarget");
        String outTarget = JsonUtils.getJsonObjectStringFieldValue(pass, "outtarget");
        Framebuffer var6 = this.getFramebuffer(inTarget);
        Framebuffer var7 = this.getFramebuffer(outTarget);

        if (var6 == null)
        {
            throw new JsonException(String.format("Input target '%s' does not exist.", inTarget));
        }
        else if (var7 == null)
        {
            throw new JsonException("Output target \'" + outTarget + "\' does not exist");
        }
        else
        {
            Shader var8 = this.addShader(name, var6, var7);
            JsonArray var9 = JsonUtils.getJsonObjectJsonArrayFieldOrDefault(pass, "auxtargets", null);

            if (var9 != null)
            {
                int var10 = 0;

                for (Iterator<JsonElement> var11 = var9.iterator(); var11.hasNext(); ++var10)
                {
                    JsonElement var12 = var11.next();

                    try
                    {
                        JsonObject var13 = JsonUtils.getJsonElementAsJsonObject(var12, "auxtarget");
                        String var23 = JsonUtils.getJsonObjectStringFieldValue(var13, "name");
                        String var15 = JsonUtils.getJsonObjectStringFieldValue(var13, "id");
                        Framebuffer var16 = this.getFramebuffer(var15);

                        if (var16 == null)
                        {
                            throw new JsonException("Render target \'" + var15 + "\' does not exist");
                        }

                        var8.addAuxFramebuffer(var23, var16, var16.textureWidth, var16.textureHeight);
                    }
                    catch (Exception var18)
                    {
                        JsonException var14 = JsonException.func_151379_a(var18);
                        var14.func_151380_a("auxtargets[" + var10 + "]");
                        throw var14;
                    }
                }
            }

            JsonArray var19 = JsonUtils.getJsonObjectJsonArrayFieldOrDefault(pass, "uniforms", (JsonArray)null);

            if (var19 != null)
            {
                int var20 = 0;

                for (Iterator var21 = var19.iterator(); var21.hasNext(); ++var20)
                {
                    JsonElement var22 = (JsonElement)var21.next();

                    try
                    {
                        this.initUniform(var22);
                    }
                    catch (Exception var17)
                    {
                        JsonException var24 = JsonException.func_151379_a(var17);
                        var24.func_151380_a("uniforms[" + var20 + "]");
                        throw var24;
                    }
                }
            }
        }
    }

    private void initUniform(JsonElement p_148028_1_) throws JsonException
    {
        JsonObject var2 = JsonUtils.getJsonElementAsJsonObject(p_148028_1_, "uniform");
        String var3 = JsonUtils.getJsonObjectStringFieldValue(var2, "name");
        ShaderUniform var4 = ((Shader)this.listShaders.get(this.listShaders.size() - 1)).getShaderManager().func_147991_a(var3);

        if (var4 == null)
        {
            throw new JsonException("Uniform \'" + var3 + "\' does not exist");
        }
        else
        {
            float[] var5 = new float[4];
            int var6 = 0;
            JsonArray var7 = JsonUtils.getJsonObjectJsonArrayField(var2, "values");

            for (Iterator var8 = var7.iterator(); var8.hasNext(); ++var6)
            {
                JsonElement var9 = (JsonElement)var8.next();

                try
                {
                    var5[var6] = JsonUtils.getJsonElementFloatValue(var9, "value");
                }
                catch (Exception var12)
                {
                    JsonException var11 = JsonException.func_151379_a(var12);
                    var11.func_151380_a("values[" + var6 + "]");
                    throw var11;
                }
            }

            switch (var6)
            {
                case 0:
                default:
                    break;

                case 1:
                    var4.func_148090_a(var5[0]);
                    break;

                case 2:
                    var4.func_148087_a(var5[0], var5[1]);
                    break;

                case 3:
                    var4.func_148095_a(var5[0], var5[1], var5[2]);
                    break;

                case 4:
                    var4.func_148081_a(var5[0], var5[1], var5[2], var5[3]);
            }
        }
    }

    public void addFramebuffer(String name, int width, int height)
    {
        Framebuffer var4 = new Framebuffer(width, height, true);
        var4.setFramebufferColor(0.0F, 0.0F, 0.0F, 0.0F);
        this.mapFramebuffers.put(name, var4);

        if (width == this.mainFramebufferWidth && height == this.mainFramebufferHeight)
        {
            this.listFramebuffers.add(var4);
        }
    }

    public void deleteShaderGroup()
    {
        Iterator var1 = this.mapFramebuffers.values().iterator();

        while (var1.hasNext())
        {
            Framebuffer var2 = (Framebuffer)var1.next();
            var2.delete();
        }

        var1 = this.listShaders.iterator();

        while (var1.hasNext())
        {
            Shader var3 = (Shader)var1.next();
            var3.deleteShader();
        }

        this.listShaders.clear();
    }

    public Shader addShader(String p_148023_1_, Framebuffer p_148023_2_, Framebuffer p_148023_3_) throws JsonException
    {
        Shader var4 = new Shader(this.resourceManager, p_148023_1_, p_148023_2_, p_148023_3_);
        this.listShaders.add(this.listShaders.size(), var4);
        return var4;
    }

    private void resetProjectionMatrix()
    {
        this.projectionMatrix = new Matrix4f();
        this.projectionMatrix.setIdentity();
        this.projectionMatrix.m00 = 2.0F / (float)this.mainFramebuffer.textureWidth;
        this.projectionMatrix.m11 = 2.0F / (float)(-this.mainFramebuffer.textureHeight);
        this.projectionMatrix.m22 = -0.0020001999F;
        this.projectionMatrix.m33 = 1.0F;
        this.projectionMatrix.m03 = -1.0F;
        this.projectionMatrix.m13 = 1.0F;
        this.projectionMatrix.m23 = -1.0001999F;
    }

    public void createBindFramebuffers(int p_148026_1_, int p_148026_2_)
    {
        this.mainFramebufferWidth = this.mainFramebuffer.textureWidth;
        this.mainFramebufferHeight = this.mainFramebuffer.textureHeight;
        this.resetProjectionMatrix();
        Iterator var3 = this.listShaders.iterator();

        while (var3.hasNext())
        {
            Shader var4 = (Shader)var3.next();
            var4.setProjectionMatrix(this.projectionMatrix);
        }

        var3 = this.listFramebuffers.iterator();

        while (var3.hasNext())
        {
            Framebuffer var5 = (Framebuffer)var3.next();
            var5.createBindFramebuffer(p_148026_1_, p_148026_2_);
        }
    }

    public void loadShaderGroup(float p_148018_1_)
    {
        if (p_148018_1_ < this.field_148037_k)
        {
            this.field_148036_j += 1.0F - this.field_148037_k;
            this.field_148036_j += p_148018_1_;
        }
        else
        {
            this.field_148036_j += p_148018_1_ - this.field_148037_k;
        }

        for (this.field_148037_k = p_148018_1_; this.field_148036_j > 20.0F; this.field_148036_j -= 20.0F)
        {
            ;
        }

        for (Object listShader : this.listShaders) {
            Shader var3 = (Shader) listShader;
            var3.loadShader(this.field_148036_j / 20.0F);
        }
    }

    public final String getShaderGroupName()
    {
        return this.shaderGroupName;
    }

    private Framebuffer getFramebuffer(String p_148017_1_)
    {
        return p_148017_1_ == null ? null : (p_148017_1_.equals("minecraft:main") ? this.mainFramebuffer : (Framebuffer)this.mapFramebuffers.get(p_148017_1_));
    }
}
