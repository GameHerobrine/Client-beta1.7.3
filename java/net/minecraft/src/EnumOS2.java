// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

package net.minecraft.src;

public enum EnumOS2 {
  linux("linux", 0),
  solaris("solaris", 1),
  windows("windows", 2),
  macos("macos", 3),
  unknown("unknown", 4);

  /*
      public static final EnumOS2 linux;
      public static final EnumOS2 solaris;
      public static final EnumOS2 windows;
      public static final EnumOS2 macos;
      public static final EnumOS2 unknown;
  */
  private static final EnumOS2 field_6511_f[]; /* synthetic field */

  static {
    /*
            linux = new EnumOS2("linux", 0);
            solaris = new EnumOS2("solaris", 1);
            windows = new EnumOS2("windows", 2);
            macos = new EnumOS2("macos", 3);
            unknown = new EnumOS2("unknown", 4);
    */
    field_6511_f = (new EnumOS2[] {linux, solaris, windows, macos, unknown});
  }

  /*
      public static EnumOS2[] values()
      {
          return (EnumOS2[])field_6511_f.clone();
      }

      public static EnumOS2 valueOf(String s)
      {
          return (EnumOS2)Enum.valueOf(net.minecraft.src.EnumOS2.class, s);
      }
  */
  private EnumOS2(String s, int i) {
    //        super(s, i);
  }
}
