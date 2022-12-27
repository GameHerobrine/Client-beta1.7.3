// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

package net.minecraft.src.world.biome;

import net.minecraft.src.world.WorldGenBigTree;
import net.minecraft.src.world.WorldGenTrees;
import net.minecraft.src.world.WorldGenerator;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            BiomeGenBase, WorldGenBigTree, WorldGenTrees, WorldGenerator

public class BiomeGenRainforest extends BiomeGenBase {

  public BiomeGenRainforest() {}

  public WorldGenerator getRandomWorldGenForTrees(Random random) {
    if (random.nextInt(3) == 0) {
      return new WorldGenBigTree();
    } else {
      return new WorldGenTrees();
    }
  }
}
