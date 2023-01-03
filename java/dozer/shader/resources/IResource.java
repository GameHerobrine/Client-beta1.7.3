package dozer.shader.resources;

import dozer.shader.resources.data.IMetadataSection;

import java.io.InputStream;

public interface IResource
{
    InputStream getInputStream();

    boolean hasMetadata();

    IMetadataSection getMetadata(String var1);
}
