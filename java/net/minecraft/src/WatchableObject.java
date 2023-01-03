// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

package net.minecraft.src;

public class WatchableObject {

  private final int objectType;
  private final int dataValueId;
  private Object watchedObject;
  private boolean isWatching;

  public WatchableObject(int i, int j, Object obj) {
    dataValueId = j;
    watchedObject = obj;
    objectType = i;
    isWatching = true;
  }

  public int getDataValueId() {
    return dataValueId;
  }

  public Object getObject() {
    return watchedObject;
  }

  public void setObject(Object obj) {
    watchedObject = obj;
  }

  public int getObjectType() {
    return objectType;
  }

  public void setWatching(boolean flag) {
    isWatching = flag;
  }
}
