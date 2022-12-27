// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

package net.minecraft.src.block;

// Referenced classes of package net.minecraft.src:
//            Block, Material

import net.minecraft.src.Material;

public class BlockOreStorage extends Block {

  public BlockOreStorage(int i, int j) {
    super(i, Material.iron);
    blockIndexInTexture = j;
  }

  public int getBlockTextureFromSide(int i) {
    return blockIndexInTexture;
  }
}
