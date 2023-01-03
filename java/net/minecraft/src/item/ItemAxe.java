// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

package net.minecraft.src.item;

// Referenced classes of package net.minecraft.src:
//            ItemTool, Block, EnumToolMaterial

import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.block.Block;

public class ItemAxe extends ItemTool {

  private static Block blocksEffectiveAgainst[];

  static {
    blocksEffectiveAgainst = (new Block[] {Block.planks, Block.bookShelf, Block.wood, Block.chest});
  }

  protected ItemAxe(int i, EnumToolMaterial enumtoolmaterial) {
    super(i, 3, enumtoolmaterial, blocksEffectiveAgainst);
  }
}
