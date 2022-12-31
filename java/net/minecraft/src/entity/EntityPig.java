// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

package net.minecraft.src.entity;

// Referenced classes of package net.minecraft.src:
//            EntityAnimal, DataWatcher, NBTTagCompound, World,
//            EntityPlayer, Item, EntityPigZombie, AchievementList,
//            EntityLightningBolt

import net.minecraft.src.AchievementList;
import net.minecraft.src.item.Item;
import net.minecraft.src.util.nbt.NBTTagCompound;
import net.minecraft.src.world.World;

public class EntityPig extends EntityAnimal {

  public EntityPig(World world) {
    super(world);
    texture = "/mob/pig.png";
    setSize(0.9F, 0.9F);
  }

  protected void entityInit() {
    dataWatcher.addObject(16, Byte.valueOf((byte) 0));
  }

  public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
    super.writeEntityToNBT(nbttagcompound);
    nbttagcompound.setBoolean("Saddle", getSaddled());
  }

  public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
    super.readEntityFromNBT(nbttagcompound);
    setSaddled(nbttagcompound.getBoolean("Saddle"));
  }

  protected String getLivingSound() {
    return "mob.pig";
  }

  protected String getHurtSound() {
    return "mob.pig";
  }

  protected String getDeathSound() {
    return "mob.pigdeath";
  }

  public boolean interact(EntityPlayer entityplayer) {
    if (getSaddled()
        && !worldObj.multiplayerWorld
        && (riddenByEntity == null || riddenByEntity == entityplayer)) {
      entityplayer.mountEntity(this);
      return true;
    } else {
      return false;
    }
  }

  protected int getDropItemId() {
    if (fire > 0) {
      return Item.porkCooked.shiftedIndex;
    } else {
      return Item.porkRaw.shiftedIndex;
    }
  }

  public boolean getSaddled() {
    return (dataWatcher.getWatchableObjectByte(16) & 1) != 0;
  }

  public void setSaddled(boolean flag) {
    if (flag) {
      dataWatcher.updateObject(16, Byte.valueOf((byte) 1));
    } else {
      dataWatcher.updateObject(16, Byte.valueOf((byte) 0));
    }
  }

  public void onStruckByLightning(EntityLightningBolt entitylightningbolt) {
    if (worldObj.multiplayerWorld) {
      return;
    } else {
      EntityPigZombie entitypigzombie = new EntityPigZombie(worldObj);
      entitypigzombie.setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
      worldObj.entityJoinedWorld(entitypigzombie);
      setEntityDead();
      return;
    }
  }

  protected void fall(float f) {
    super.fall(f);
    if (f > 5F && (riddenByEntity instanceof EntityPlayer)) {
      ((EntityPlayer) riddenByEntity).triggerAchievement(AchievementList.flyPig);
    }
  }
}
