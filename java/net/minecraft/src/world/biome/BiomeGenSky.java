// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

package net.minecraft.src.world.biome;

// Referenced classes of package net.minecraft.src:
//            BiomeGenBase, SpawnListEntry, EntityChicken

import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.entity.EntityChicken;

public class BiomeGenSky extends BiomeGenBase {

  public BiomeGenSky() {
    spawnableMonsterList.clear();
    spawnableCreatureList.clear();
    spawnableWaterCreatureList.clear();
    spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10));
  }

  public int getSkyColorByTemp(float f) {
    return 0xc0c0ff;
  }
}
