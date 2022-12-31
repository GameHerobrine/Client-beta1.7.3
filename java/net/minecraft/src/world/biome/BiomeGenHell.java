// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

package net.minecraft.src.world.biome;

// Referenced classes of package net.minecraft.src:
//            BiomeGenBase, SpawnListEntry, EntityGhast, EntityPigZombie

import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.entity.EntityGhast;
import net.minecraft.src.entity.EntityPigZombie;

public class BiomeGenHell extends BiomeGenBase {

  public BiomeGenHell() {
    spawnableMonsterList.clear();
    spawnableCreatureList.clear();
    spawnableWaterCreatureList.clear();
    spawnableMonsterList.add(new SpawnListEntry(EntityGhast.class, 10));
    spawnableMonsterList.add(new SpawnListEntry(EntityPigZombie.class, 10));
  }
}
