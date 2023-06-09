// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

package net.minecraft.src;

// Referenced classes of package net.minecraft.src:
//            NBTTagCompound

import net.minecraft.src.util.nbt.NBTTagCompound;

public abstract class MapDataBase {

  public final String field_28168_a;
  private boolean dirty;

  public MapDataBase(String s) {
    field_28168_a = s;
  }

  public abstract void readFromNBT(NBTTagCompound nbttagcompound);

  public abstract void writeToNBT(NBTTagCompound nbttagcompound);

  public void markDirty() {
    setDirty(true);
  }

  public boolean isDirty() {
    return dirty;
  }

  public void setDirty(boolean flag) {
    dirty = flag;
  }
}
