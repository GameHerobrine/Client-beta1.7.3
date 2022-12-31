// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

package net.minecraft.src.item;

// Referenced classes of package net.minecraft.src:
//            ItemTool, Block, EnumToolMaterial

import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.block.Block;

public class ItemSpade extends ItemTool {

  private static Block blocksEffectiveAgainst[];

  static {
    blocksEffectiveAgainst =
        (new Block[] {
          Block.grass,
          Block.dirt,
          Block.sand,
          Block.gravel,
          Block.snow,
          Block.blockSnow,
          Block.blockClay,
          Block.tilledField
        });
  }

  public ItemSpade(int i, EnumToolMaterial enumtoolmaterial) {
    super(i, 1, enumtoolmaterial, blocksEffectiveAgainst);
  }

  public boolean canHarvestBlock(Block block) {
    if (block == Block.snow) {
      return true;
    }
    return block == Block.blockSnow;
  }
}
