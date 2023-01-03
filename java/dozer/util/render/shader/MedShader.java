package dozer.util.render.shader;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;

public abstract class MedShader {

    private final Map<String, Integer> uniforms = new LinkedHashMap<>();

    protected int program;
    protected boolean canUse = false;

    public MedShader(String frag) {
        this(frag, "/shaders/vertex.fsh");
    }

    public MedShader(String frag, String vert) {
        int fragShader = 0;
        int vertShader = 0;

        try {
            vertShader = compile(vert, ARBVertexShader.GL_VERTEX_SHADER_ARB);
            fragShader = compile(frag, ARBFragmentShader.GL_FRAGMENT_SHADER_ARB);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fragShader == 0 || vertShader == 0) {
                System.out.println("Failed to compile shaders!");
                return;
            }
        }

        program = ARBShaderObjects.glCreateProgramObjectARB();
        if (program == 0) {
            return;
        }

        ARBShaderObjects.glAttachObjectARB(program, vertShader);
        ARBShaderObjects.glAttachObjectARB(program, fragShader);

        ARBShaderObjects.glLinkProgramARB(program);
        if (ARBShaderObjects.glGetObjectParameteriARB(program, ARBShaderObjects.GL_OBJECT_LINK_STATUS_ARB) == GL_FALSE) {
            System.out.println(getLogInfo(program));
            return;
        }

        ARBShaderObjects.glValidateProgramARB(program);
        if (ARBShaderObjects.glGetObjectParameteriARB(program, ARBShaderObjects.GL_OBJECT_VALIDATE_STATUS_ARB) == GL_FALSE) {
            System.out.println(getLogInfo(program));
            return;
        }

        canUse = true;
        onInitialize();
    }

    protected abstract void onInitialize();

    public void use() {
        if (canUse) {
            ARBShaderObjects.glUseProgramObjectARB(program);
        }
    }

    public void stopUse() {
        ARBShaderObjects.glUseProgramObjectARB(0);
    }

    public void createUniform(String name) {
        uniforms.put(name, glGetUniformLocation(program, name));
    }

    public int getUniform(String name) {
        return uniforms.getOrDefault(name, -1);
    }

    public static String getLogInfo(int obj) {
        return ARBShaderObjects.glGetInfoLogARB(obj, ARBShaderObjects.glGetObjectParameteriARB(obj, ARBShaderObjects.GL_OBJECT_INFO_LOG_LENGTH_ARB));
    }

    public static int compile(String loc, int type) {
        InputStream stream = Shader.class.getResourceAsStream(loc);
        if (stream == null) {
            return 0;
        }

        int shader = 0;

        try {
            shader = ARBShaderObjects.glCreateShaderObjectARB(type);
            if (shader == 0) {
                return 0;
            }

            ARBShaderObjects.glShaderSourceARB(shader, read(stream));
            ARBShaderObjects.glCompileShaderARB(shader);
        } catch (Exception e) {
            ARBShaderObjects.glDeleteObjectARB(shader);
            e.printStackTrace();
        }

        return shader;
    }

    public static String read(InputStream stream) {
        StringBuilder builder = new StringBuilder();

        int i;
        try {
            while ((i = stream.read()) != -1) {
                builder.append((char) i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }
}