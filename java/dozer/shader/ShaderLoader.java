package dozer.shader;

import com.google.common.collect.Maps;
import dozer.shader.resources.IResourceManager;
import dozer.shader.resources.ResourceLocation;
import dozer.shader.util.JsonException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;

public class ShaderLoader
{
    private final ShaderType field_148061_a;
    private final String field_148059_b;
    private int field_148060_c;
    private int field_148058_d = 0;
    private static final String __OBFID = "CL_00001043";

    private ShaderLoader(ShaderType p_i45091_1_, int p_i45091_2_, String p_i45091_3_)
    {
        this.field_148061_a = p_i45091_1_;
        this.field_148060_c = p_i45091_2_;
        this.field_148059_b = p_i45091_3_;
    }

    public void func_148056_a(ShaderManager p_148056_1_)
    {
        ++this.field_148058_d;
        GL20.glAttachShader(p_148056_1_.func_147986_h(), this.field_148060_c);
    }

    public void func_148054_b(ShaderManager p_148054_1_)
    {
        --this.field_148058_d;

        if (this.field_148058_d <= 0)
        {
            GL20.glDeleteShader(this.field_148060_c);
            this.field_148061_a.func_148064_d().remove(this.field_148059_b);
        }
    }

    public String func_148055_a()
    {
        return this.field_148059_b;
    }

    public static ShaderLoader func_148057_a(IResourceManager p_148057_0_, ShaderType p_148057_1_, String p_148057_2_) throws IOException
    {
        ShaderLoader var3 = (ShaderLoader)p_148057_1_.func_148064_d().get(p_148057_2_);

        if (var3 == null)
        {
            ResourceLocation var4 = new ResourceLocation("shaders/program/" + p_148057_2_ + p_148057_1_.func_148063_b());
            BufferedInputStream var5 = new BufferedInputStream(p_148057_0_.getResource(var4).getInputStream());
            byte[] var6 = IOUtils.toByteArray(var5);
            ByteBuffer var7 = BufferUtils.createByteBuffer(var6.length);
            var7.put(var6);
            var7.position(0);
            int var8 = GL20.glCreateShader(p_148057_1_.func_148065_c());
            GL20.glShaderSource(var8, var7);
            GL20.glCompileShader(var8);

            if (GL20.glGetShaderi(var8, 35713) == 0)
            {
                String var9 = StringUtils.trim(GL20.glGetShaderInfoLog(var8, 32768));
                JsonException var10 = new JsonException("Couldn\'t compile " + p_148057_1_.func_148062_a() + " program: " + var9);
                var10.func_151381_b(var4.getResourcePath());
                throw var10;
            }

            var3 = new ShaderLoader(p_148057_1_, var8, p_148057_2_);
            p_148057_1_.func_148064_d().put(p_148057_2_, var3);
        }

        return var3;
    }

    public static enum ShaderType
    {
        VERTEX("VERTEX", 0, "vertex", ".vsh", 35633),
        FRAGMENT("FRAGMENT", 1, "fragment", ".fsh", 35632);
        private final String field_148072_c;
        private final String field_148069_d;
        private final int field_148070_e;
        private final Map field_148067_f = Maps.newHashMap();

        private static final ShaderType[] $VALUES = new ShaderType[]{VERTEX, FRAGMENT};
        private static final String __OBFID = "CL_00001044";

        private ShaderType(String p_i45090_1_, int p_i45090_2_, String p_i45090_3_, String p_i45090_4_, int p_i45090_5_)
        {
            this.field_148072_c = p_i45090_3_;
            this.field_148069_d = p_i45090_4_;
            this.field_148070_e = p_i45090_5_;
        }

        public String func_148062_a()
        {
            return this.field_148072_c;
        }

        protected String func_148063_b()
        {
            return this.field_148069_d;
        }

        protected int func_148065_c()
        {
            return this.field_148070_e;
        }

        protected Map func_148064_d()
        {
            return this.field_148067_f;
        }
    }
}
