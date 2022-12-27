// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

package net.minecraft.src.entity;

// Referenced classes of package net.minecraft.src:
//            EntityCreature, World, NBTTagCompound

import net.minecraft.src.util.nbt.NBTTagCompound;
import net.minecraft.src.world.World;

public class EntityWaterMob extends EntityCreature {

  public EntityWaterMob(World world) {
    super(world);
  }

  public boolean canBreatheUnderwater() {
    return true;
  }

  public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
    super.writeEntityToNBT(nbttagcompound);
  }

  public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
    super.readEntityFromNBT(nbttagcompound);
  }

  public boolean getCanSpawnHere() {
    return worldObj.checkIfAABBIsClear(boundingBox);
  }

  public int getTalkInterval() {
    return 120;
  }
}
