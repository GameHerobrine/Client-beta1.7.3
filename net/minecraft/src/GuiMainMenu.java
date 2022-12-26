// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import dozer.Dozer;
import dozer.util.chat.ChatColorUtil;
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
    private GuiButton multiplayerButton;

    public GuiMainMenu() {
        updateCounter = 0.0F;
        splashText = "missing";
        try {
            String[] splashTextArray = {
                    "DozerHack > All!",
                    "DozerHack owns you and all!",
                    "DozerHack is the best!",
                    "Nothing better then DozerHack!",
                    "DozerHack is back!",
                    "Sixene is a prick!",
                    "Since 2021!",
                    "DozerHack helps you get bitches!"
            };

            splashText = splashTextArray[rand.nextInt(splashTextArray.length)];
        } catch (Exception exception) {
        }
    }

    public void updateScreen() {
        updateCounter++;
    }

    protected void keyTyped(char c, int i) {
    }

    public void initGui() {
        int i = height / 4 + 48;
        controlList.add(new GuiButton(1, width / 2 - 100, i, "Singleplayer"));
        controlList.add(multiplayerButton = new GuiButton(2, width / 2 - 100, i + 24, "Multiplayer"));
        controlList.add(new GuiButton(3, width / 2 - 100, i + 48, "Texture Packs"));
        controlList.add(new GuiButton(0, width / 2 - 100, i + 72 + 12, 98, 20, "Options..."));
        controlList.add(new GuiButton(5, width / 2 + 2, i + 72 + 12, 98, 20, "Quit"));
    }

    protected void actionPerformed(GuiButton guibutton) {
        if (guibutton.id == 0) {
            mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
        }
        if (guibutton.id == 1) {
            mc.displayGuiScreen(new GuiSelectWorld(this));
        }
        if (guibutton.id == 2) {
            mc.displayGuiScreen(new GuiMultiplayer(this));
        }
        if (guibutton.id == 3) {
            mc.displayGuiScreen(new GuiTexturePacks(this));
        }
        if (guibutton.id == 4) {
            // TODO - Add alternate account UI
        }
        if (guibutton.id == 5) {
            mc.shutdown();
        }
    }

    public void drawScreen(int i, int j, float f) {
        drawDefaultBackground();
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

        String text = String.format("Made with %slove%s by %s%s!", chatColorUtil.RED, chatColorUtil.RESET, chatColorUtil.GOLD + String.join(chatColorUtil.RESET + ", " + chatColorUtil.GOLD, Dozer.getSingleton().getAuthor()), chatColorUtil.RESET);
        drawString(fontRenderer, text, 2, height - 10, 0xffffff);
        super.drawScreen(i, j, f);
    }

}
