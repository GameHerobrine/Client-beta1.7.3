// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

package net.minecraft.src.item;

// Referenced classes of package net.minecraft.src:
//            Item, ItemStack, EntityPlayer, World

import net.minecraft.src.world.World;
import net.minecraft.src.entity.EntityPlayer;

public class ItemFood extends Item {

  private int healAmount;
  private boolean isWolfsFavoriteMeat;

  public ItemFood(int i, int j, boolean flag) {
    super(i);
    healAmount = j;
    isWolfsFavoriteMeat = flag;
    maxStackSize = 1;
  }

  public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
    itemstack.stackSize--;
    entityplayer.heal(healAmount);
    return itemstack;
  }

  public int getHealAmount() {
    return healAmount;
  }

  public boolean getIsWolfsFavoriteMeat() {
    return isWolfsFavoriteMeat;
  }
}
