// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

package net.minecraft.src.block;

import net.minecraft.src.Material;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            BlockBreakable, Material

public class BlockGlass extends BlockBreakable {

  public BlockGlass(int i, int j, Material material, boolean flag) {
    super(i, j, material, flag);
  }

  public int quantityDropped(Random random) {
    return 0;
  }

  public int getRenderBlockPass() {
    return 0;
  }
}
