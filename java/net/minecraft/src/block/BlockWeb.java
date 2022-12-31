// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

package net.minecraft.src.block;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.item.Item;
import net.minecraft.src.Material;
import net.minecraft.src.world.World;
import net.minecraft.src.entity.Entity;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            Block, Material, Entity, Item,
//            World, AxisAlignedBB

public class BlockWeb extends Block {

  public BlockWeb(int i, int j) {
    super(i, j, Material.field_31068_A);
  }

  public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
    entity.isInWeb = true;
  }

  public boolean isOpaqueCube() {
    return false;
  }

  public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
    return null;
  }

  public int getRenderType() {
    return 1;
  }

  public boolean renderAsNormalBlock() {
    return false;
  }

  public int idDropped(int i, Random random) {
    return Item.silk.shiftedIndex;
  }
}
