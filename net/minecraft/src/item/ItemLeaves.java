// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

package net.minecraft.src.item;

// Referenced classes of package net.minecraft.src:
//            ItemBlock, Block, BlockLeaves, ColorizerFoliage

import net.minecraft.src.ColorizerFoliage;
import net.minecraft.src.block.Block;

public class ItemLeaves extends ItemBlock {

  public ItemLeaves(int i) {
    super(i);
    setMaxDamage(0);
    setHasSubtypes(true);
  }

  public int getPlacedBlockMetadata(int i) {
    return i | 8;
  }

  public int getIconFromDamage(int i) {
    return Block.leaves.getBlockTextureFromSideAndMetadata(0, i);
  }

  public int getColorFromDamage(int i) {
    if ((i & 1) == 1) {
      return ColorizerFoliage.getFoliageColorPine();
    }
    if ((i & 2) == 2) {
      return ColorizerFoliage.getFoliageColorBirch();
    } else {
      return ColorizerFoliage.func_31073_c();
    }
  }
}
