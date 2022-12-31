package dozer.shader.renderer;

import dozer.shader.resources.IResourceManager;

import java.io.IOException;

public interface ITextureObject
{
    void loadTexture(IResourceManager var1) throws IOException;

    int getGlTextureId();
}
