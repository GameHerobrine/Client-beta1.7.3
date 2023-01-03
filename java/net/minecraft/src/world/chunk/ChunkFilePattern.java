package net.minecraft.src.world.chunk;

import net.minecraft.src.Empty2;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChunkFilePattern implements FilenameFilter {

  public static final Pattern field_22189_a =
      Pattern.compile("c\\.(-?[0-9a-z]+)\\.(-?[0-9a-z]+)\\.dat");

  private ChunkFilePattern() {}

  public ChunkFilePattern(Empty2 empty2) {
    this();
  }

  public boolean accept(File file, String s) {
    Matcher matcher = field_22189_a.matcher(s);
    return matcher.matches();
  }
}
