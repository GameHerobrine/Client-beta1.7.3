// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

package net.minecraft.src.render;

import net.minecraft.src.ModelBiped;
import net.minecraft.src.block.Block;
import net.minecraft.src.entity.EntityLiving;
import net.minecraft.src.item.Item;
import net.minecraft.src.item.ItemStack;
import org.lwjgl.opengl.GL11;

// Referenced classes of package net.minecraft.src:
//            RenderLiving, EntityLiving, ModelBiped, ModelRenderer,
//            ItemStack, Block, RenderBlocks, Item,
//            RenderManager, ItemRenderer

public class RenderBiped extends RenderLiving {

  protected ModelBiped modelBipedMain;

  public RenderBiped(ModelBiped modelbiped, float f) {
    super(modelbiped, f);
    modelBipedMain = modelbiped;
  }

  protected void renderEquippedItems(EntityLiving entityliving, float f) {
    ItemStack itemstack = entityliving.getHeldItem();
    if (itemstack != null) {
      GL11.glPushMatrix();
      modelBipedMain.bipedRightArm.postRender(0.0625F);
      GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
      if (itemstack.itemID < 256
          && RenderBlocks.renderItemIn3d(Block.blocksList[itemstack.itemID].getRenderType())) {
        float f1 = 0.5F;
        GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
        f1 *= 0.75F;
        GL11.glRotatef(20F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(45F, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(f1, -f1, f1);
      } else if (Item.itemsList[itemstack.itemID].isFull3D()) {
        float f2 = 0.625F;
        GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
        GL11.glScalef(f2, -f2, f2);
        GL11.glRotatef(-100F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(45F, 0.0F, 1.0F, 0.0F);
      } else {
        float f3 = 0.375F;
        GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
        GL11.glScalef(f3, f3, f3);
        GL11.glRotatef(60F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(-90F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(20F, 0.0F, 0.0F, 1.0F);
      }
      renderManager.itemRenderer.renderItem(entityliving, itemstack);
      GL11.glPopMatrix();
    }
  }
}
