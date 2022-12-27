// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

package net.minecraft.src.world.chunk;

import net.minecraft.src.Empty2;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package net.minecraft.src:
//            Empty2

public class ChunkFolderPattern implements FileFilter {

  public static final Pattern field_22392_a = Pattern.compile("[0-9a-z]|([0-9a-z][0-9a-z])");

  private ChunkFolderPattern() {}

  public ChunkFolderPattern(Empty2 empty2) {
    this();
  }

  public boolean accept(File file) {
    if (file.isDirectory()) {
      Matcher matcher = field_22392_a.matcher(file.getName());
      return matcher.matches();
    } else {
      return false;
    }
  }
}
