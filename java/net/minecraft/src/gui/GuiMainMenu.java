// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

package net.minecraft.src.gui;

import dozer.Dozer;
import dozer.systems.module.impl.client.MainMenu;
import dozer.util.chat.ChatColorUtil;
import dozer.util.render.shader.ShaderUtil;
import net.minecraft.src.Tessellator;
import org.lwjgl.opengl.GL11;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            GuiScreen, StringTranslate, GuiButton, GuiOptions,
//            GuiSelectWorld, GuiMultiplayer, GuiTexturePacks, Tessellator,
//            RenderEngine, MathHelper, FontRenderer

public class GuiMainMenu extends GuiScreen {

  private static final Random rand = new Random();
  private final ChatColorUtil chatColorUtil = new ChatColorUtil();
  private float updateCounter;
  private String splashText;
  private final ShaderUtil shader = new ShaderUtil("shaders/vertex.glsl", "shaders/shader.glsl");
  private final boolean customButtons;

  public GuiMainMenu() {
    updateCounter = 0.0F;
    splashText = "missing";
    String[] splashTextArray = {
      "DozerHack > All!",
      "DozerHack owns you and all!",
      "DozerHack is the best!",
      "Nothing better then DozerHack!",
      "DozerHack is back!",
      "Sixene is a prick!",
      "Since 2021!",
      "DozerHack helps you get bitches!",
      "Zǎoshang hǎo zhōngguó xiànzài wo you BING CHILLING",
      "Marvin Heemeyer - very based",
      "We are we are we are, the ones who make the night shine",
      "Stay noided!"
    };

    splashText = splashTextArray[rand.nextInt(splashTextArray.length)];

    customButtons = MainMenu.buttons;
  }

  public void updateScreen() {
    updateCounter++;
  }

  protected void keyTyped(char c, int i) {}

  public void initGui() {
    int i = height / 4 + 48;
    controlList.add(new GuiButton(1, width / 2 - 100, i, "Singleplayer", customButtons));
    controlList.add(new GuiButton(2, width / 2 - 100, i + 24, "Multiplayer", customButtons));
    controlList.add(new GuiButton(3, width / 2 - 100, i + 48, "Texture Packs", customButtons));
    controlList.add(new GuiButton(4, width / 2 - 100, i + 72, "Options...", customButtons));
    controlList.add(new GuiButton(5, width / 2 - 100, i + 96, "Quit", customButtons));
  }

  protected void actionPerformed(GuiButton guibutton) {
    switch (guibutton.id) {
      case 1 -> mc.displayGuiScreen(new GuiSelectWorld(this));
      case 2 -> mc.displayGuiScreen(new GuiMultiplayer(this));
      case 3 -> mc.displayGuiScreen(new GuiTexturePacks(this));
      case 4 -> mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
      case 5 -> mc.shutdown();
    }
  }

  public void drawScreen(int i, int j, float f) {
    drawDefaultBackground();

    //not the best way of doing this

    if (Dozer.getSingleton().getModuleManager().getModuleByClass(MainMenu.class).isToggled()) {
      shader.useShader();
      shader.setUniform2f("resolution", width, height);
      shader.setUniform1f("time", (float) (System.currentTimeMillis() - shader.getInitTime()) / 2000F);
      Gui.drawRect(0, 0, width, height, 0xFFFFFFFF);
      shader.stopShader();
    }

    Tessellator tessellator = Tessellator.instance;
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    tessellator.setColorOpaque_I(0xffffff);

    GL11.glPushMatrix();
    GL11.glScalef(3.0F, 3.0F, 1.0F);
    drawCenteredString(fontRenderer, Dozer.getSingleton().getName(), (width / 3) / 2, 20, 0xffffff);
    GL11.glPopMatrix();

    GL11.glPushMatrix();
    drawCenteredString(fontRenderer, chatColorUtil.GOLD + splashText, width / 2, 90, 0xffffff);
    GL11.glPopMatrix();

    String text =
        String.format(
            "Made with %slove%s by %s%s!",
            chatColorUtil.RED,
            chatColorUtil.RESET,
            chatColorUtil.GOLD
                + String.join(
                    chatColorUtil.RESET + ", " + chatColorUtil.GOLD,
                    Dozer.getSingleton().getAuthors()),
            chatColorUtil.RESET);
    drawString(fontRenderer, text, 2, height - 10, 0xffffff);
    super.drawScreen(i, j, f);
  }
}
