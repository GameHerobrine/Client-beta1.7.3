// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

package net.minecraft.src.render;

import dozer.shader.renderer.OpenGlHelper;
import net.minecraft.src.*;
import net.minecraft.src.block.Block;
import net.minecraft.src.entity.*;
import net.minecraft.src.item.Item;
import net.minecraft.src.item.ItemRenderer;
import net.minecraft.src.world.World;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

// Referenced classes of package net.minecraft.src:
//            EntitySpider, RenderSpider, EntityPig, RenderPig,
//            ModelPig, EntitySheep, RenderSheep, ModelSheep2,
//            ModelSheep1, EntityCow, RenderCow, ModelCow,
//            EntityWolf, RenderWolf, ModelWolf, EntityChicken,
//            RenderChicken, ModelChicken, EntityCreeper, RenderCreeper,
//            EntitySkeleton, RenderBiped, ModelSkeleton, EntityZombie,
//            ModelZombie, EntitySlime, RenderSlime, ModelSlime,
//            EntityPlayer, RenderPlayer, EntityGiantZombie, RenderGiantZombie,
//            EntityGhast, RenderGhast, EntitySquid, RenderSquid,
//            ModelSquid, EntityLiving, RenderLiving, ModelBiped,
//            Entity, RenderEntity, EntityPainting, RenderPainting,
//            EntityArrow, RenderArrow, EntitySnowball, RenderSnowball,
//            Item, EntityEgg, EntityFireball, RenderFireball,
//            EntityItem, RenderItem, EntityTNTPrimed, RenderTNTPrimed,
//            EntityFallingSand, RenderFallingSand, EntityMinecart, RenderMinecart,
//            EntityBoat, RenderBoat, EntityFish, RenderFish,
//            EntityLightningBolt, RenderLightningBolt, Render, MathHelper,
//            World, Block, FontRenderer, RenderEngine,
//            ItemRenderer, GameSettings

public class RenderManager {

  public static RenderManager instance = new RenderManager();
  public static double renderPosX;
  public static double renderPosY;
  public static double renderPosZ;
  public RenderEngine renderEngine;
  public ItemRenderer itemRenderer;
  public World worldObj;
  public EntityLiving livingPlayer;
  public float playerViewY;
  public float playerViewX;
  public GameSettings options;
  public double field_1222_l;
  public double field_1221_m;
  public double field_1220_n;
  private Map entityRenderMap;
  private FontRenderer fontRenderer;

  private RenderManager() {
    entityRenderMap = new HashMap();
    entityRenderMap.put(EntitySpider.class, new RenderSpider());
    entityRenderMap.put(EntityPig.class, new RenderPig(new ModelPig(), new ModelPig(0.5F), 0.7F));
    entityRenderMap.put(
        EntitySheep.class, new RenderSheep(new ModelSheep2(), new ModelSheep1(), 0.7F));
    entityRenderMap.put(EntityCow.class, new RenderCow(new ModelCow(), 0.7F));
    entityRenderMap.put(EntityWolf.class, new RenderWolf(new ModelWolf(), 0.5F));
    entityRenderMap.put(EntityChicken.class, new RenderChicken(new ModelChicken(), 0.3F));
    entityRenderMap.put(EntityCreeper.class, new RenderCreeper());
    entityRenderMap.put(EntitySkeleton.class, new RenderBiped(new ModelSkeleton(), 0.5F));
    entityRenderMap.put(EntityZombie.class, new RenderBiped(new ModelZombie(), 0.5F));
    entityRenderMap.put(
        EntitySlime.class, new RenderSlime(new ModelSlime(16), new ModelSlime(0), 0.25F));
    entityRenderMap.put(EntityPlayer.class, new RenderPlayer());
    entityRenderMap.put(
        EntityGiantZombie.class, new RenderGiantZombie(new ModelZombie(), 0.5F, 6F));
    entityRenderMap.put(EntityGhast.class, new RenderGhast());
    entityRenderMap.put(EntitySquid.class, new RenderSquid(new ModelSquid(), 0.7F));
    entityRenderMap.put(EntityLiving.class, new RenderLiving(new ModelBiped(), 0.5F));
    entityRenderMap.put(Entity.class, new RenderEntity());
    entityRenderMap.put(EntityPainting.class, new RenderPainting());
    entityRenderMap.put(EntityArrow.class, new RenderArrow());
    entityRenderMap.put(
        EntitySnowball.class, new RenderSnowball(Item.snowball.getIconFromDamage(0)));
    entityRenderMap.put(EntityEgg.class, new RenderSnowball(Item.egg.getIconFromDamage(0)));
    entityRenderMap.put(EntityFireball.class, new RenderFireball());
    entityRenderMap.put(EntityItem.class, new RenderItem());
    entityRenderMap.put(EntityTNTPrimed.class, new RenderTNTPrimed());
    entityRenderMap.put(EntityFallingSand.class, new RenderFallingSand());
    entityRenderMap.put(EntityMinecart.class, new RenderMinecart());
    entityRenderMap.put(EntityBoat.class, new RenderBoat());
    entityRenderMap.put(EntityFish.class, new RenderFish());
    entityRenderMap.put(EntityLightningBolt.class, new RenderLightningBolt());
    Render render;
    for (Iterator iterator = entityRenderMap.values().iterator();
        iterator.hasNext();
        render.setRenderManager(this)) {
      render = (Render) iterator.next();
    }
  }

  public Render getEntityClassRenderObject(Class class1) {
    Render render = (Render) entityRenderMap.get(class1);
    if (render == null && class1 != (Entity.class)) {
      render = getEntityClassRenderObject(class1.getSuperclass());
      entityRenderMap.put(class1, render);
    }
    return render;
  }
  public void renderEntityStatic(Entity entityIn, float partialTicks)
  {
    if (entityIn.ticksExisted == 0)
    {
      entityIn.lastTickPosX = entityIn.posX;
      entityIn.lastTickPosY = entityIn.posY;
      entityIn.lastTickPosZ = entityIn.posZ;
    }

    double d0 = entityIn.lastTickPosX + (entityIn.posX - entityIn.lastTickPosX) * (double)partialTicks;
    double d1 = entityIn.lastTickPosY + (entityIn.posY - entityIn.lastTickPosY) * (double)partialTicks;
    double d2 = entityIn.lastTickPosZ + (entityIn.posZ - entityIn.lastTickPosZ) * (double)partialTicks;
    float f = entityIn.prevRotationYaw + (entityIn.rotationYaw - entityIn.prevRotationYaw) * partialTicks;


    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    this.renderEntity(entityIn, partialTicks);
  }


  public Render getEntityRenderObject(Entity entity) {
    return getEntityClassRenderObject(entity.getClass());
  }

  public void cacheActiveRenderInfo(
      World world,
      RenderEngine renderengine,
      FontRenderer fontrenderer,
      EntityLiving entityliving,
      GameSettings gamesettings,
      float f) {
    worldObj = world;
    renderEngine = renderengine;
    options = gamesettings;
    livingPlayer = entityliving;
    fontRenderer = fontrenderer;
    if (entityliving.isPlayerSleeping()) {
      int i =
          world.getBlockId(
              MathHelper.floor_double(entityliving.posX),
              MathHelper.floor_double(entityliving.posY),
              MathHelper.floor_double(entityliving.posZ));
      if (i == Block.blockBed.blockID) {
        int j =
            world.getBlockMetadata(
                MathHelper.floor_double(entityliving.posX),
                MathHelper.floor_double(entityliving.posY),
                MathHelper.floor_double(entityliving.posZ));
        int k = j & 3;
        playerViewY = k * 90 + 180;
        playerViewX = 0.0F;
      }
    } else {
      playerViewY =
          entityliving.prevRotationYaw
              + (entityliving.rotationYaw - entityliving.prevRotationYaw) * f;
      playerViewX =
          entityliving.prevRotationPitch
              + (entityliving.rotationPitch - entityliving.prevRotationPitch) * f;
    }
    field_1222_l =
        entityliving.lastTickPosX + (entityliving.posX - entityliving.lastTickPosX) * (double) f;
    field_1221_m =
        entityliving.lastTickPosY + (entityliving.posY - entityliving.lastTickPosY) * (double) f;
    field_1220_n =
        entityliving.lastTickPosZ + (entityliving.posZ - entityliving.lastTickPosZ) * (double) f;
  }

  public void renderEntity(Entity entity, float f) {
    double d = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double) f;
    double d1 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double) f;
    double d2 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double) f;
    float f1 = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * f;
    float f2 = entity.getEntityBrightness(f);
    GL11.glColor3f(f2, f2, f2);
    renderEntityWithPosYaw(entity, d - renderPosX, d1 - renderPosY, d2 - renderPosZ, f1, f);
  }

  public void renderEntityWithPosYaw(
      Entity entity, double d, double d1, double d2, float f, float f1) {
    Render render = getEntityRenderObject(entity);
    if (render != null) {
      render.doRender(entity, d, d1, d2, f, f1);
      render.doRenderShadowAndFire(entity, d, d1, d2, f, f1);
    }
  }

  public void func_852_a(World world) {
    worldObj = world;
  }

  public double func_851_a(double d, double d1, double d2) {
    double d3 = d - field_1222_l;
    double d4 = d1 - field_1221_m;
    double d5 = d2 - field_1220_n;
    return d3 * d3 + d4 * d4 + d5 * d5;
  }

  public FontRenderer getFontRenderer() {
    return fontRenderer;
  }
}
