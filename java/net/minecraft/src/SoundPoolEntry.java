// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

package net.minecraft.src;

import java.net.URL;

public class SoundPoolEntry {

  public String soundName;
  public URL soundUrl;

  public SoundPoolEntry(String s, URL url) {
    soundName = s;
    soundUrl = url;
  }
}
