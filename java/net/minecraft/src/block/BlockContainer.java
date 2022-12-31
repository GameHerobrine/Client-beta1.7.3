// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

package net.minecraft.src.block;

// Referenced classes of package net.minecraft.src:
//            Block, World, Material, TileEntity

import net.minecraft.src.Material;
import net.minecraft.src.world.tile.TileEntity;
import net.minecraft.src.world.World;

public abstract class BlockContainer extends Block {

  protected BlockContainer(int i, Material material) {
    super(i, material);
    isBlockContainer[i] = true;
  }

  protected BlockContainer(int i, int j, Material material) {
    super(i, j, material);
    isBlockContainer[i] = true;
  }

  public void onBlockAdded(World world, int i, int j, int k) {
    super.onBlockAdded(world, i, j, k);
    world.setBlockTileEntity(i, j, k, getBlockEntity());
  }

  public void onBlockRemoval(World world, int i, int j, int k) {
    super.onBlockRemoval(world, i, j, k);
    world.removeBlockTileEntity(i, j, k);
  }

  protected abstract TileEntity getBlockEntity();
}
