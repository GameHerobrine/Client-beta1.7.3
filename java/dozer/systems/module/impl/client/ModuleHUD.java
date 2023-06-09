package dozer.systems.module.impl.client;

import dozer.systems.module.ModuleCategory;
import dozer.ui.hudEditor.HudScreen;
import dozer.util.chat.UtilChatColor;
import net.minecraft.src.gui.GuiChat;
import dozer.systems.module.ModuleInfo;
import dozer.systems.module.Module;

import java.util.Comparator;

@ModuleInfo(name = "HUD", description = "Displays the HUD.", category = ModuleCategory.CLIENT)
public class ModuleHUD extends Module {

    private final UtilChatColor chatColorUtil = new UtilChatColor();


    @Override
    public void onEnable() {
        mc.displayGuiScreen(new HudScreen());
        this.toggle();
    }


    /*
    @Subscribe
    public void onRender2D(final Render2DEvent event) {
        List<Module> modules = Dozer.getSingleton().getModuleManager().getEnabledModules();
        ScaledResolution scaledResolution = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
        FontRenderer fontRenderer = mc.fontRenderer;

        modules.sort(new sortModules());

        String watermarkText = String.format("%s%s %s%s", chatColorUtil.GOLD, Dozer.getSingleton().getName(), chatColorUtil.GRAY, Dozer.getSingleton().getVersion());
        fontRenderer.drawStringWithShadow(watermarkText, 2, 2, 0xFFFFFFFF);

        String coordsText;

        if (mc.thePlayer.dimension != -1) {
            coordsText = String.format("%s %s %s " + chatColorUtil.GRAY + "[" + chatColorUtil.GOLD + "%s %s" + chatColorUtil.GRAY + "]", (int) mc.thePlayer.posX, (int) mc.thePlayer.posY, (int) mc.thePlayer.posZ, (int) mc.thePlayer.posX / 8, (int) mc.thePlayer.posZ / 8);
        } else {
            coordsText = String.format("%s %s %s " + chatColorUtil.GRAY + "[" + chatColorUtil.GOLD + "%s %s" + chatColorUtil.GRAY + "]", (int) mc.thePlayer.posX * 8, (int) mc.thePlayer.posY, (int) mc.thePlayer.posZ * 8, (int) mc.thePlayer.posX, (int) mc.thePlayer.posZ);
        }

        fontRenderer.drawStringWithShadow(chatColorUtil.GRAY + "XYZ " + chatColorUtil.GOLD + coordsText, 2, scaledResolution.getScaledHeight() - 10, 0xFFFFFFFF);

        int y = 2;
        int x = scaledResolution.getScaledWidth() - 2;
        for (Module module : modules) {

            String arrayListText = module.getSuffix().length() > 0 ? module.getName() + " " + chatColorUtil.GRAY + module.getSuffix() : module.getName();
            fontRenderer.drawStringWithShadow(chatColorUtil.GOLD + arrayListText, x - fontRenderer.getStringWidth(arrayListText), y, -1);
            y += 10;
        }
    }


     */
    public static class sortModules implements Comparator<Module> {
        public int compare(Module m1, Module m2) {
            return Integer.compare(mc.fontRenderer.getStringWidth(m2.getSuffix().length() > 0 ? m2.getName() + " " + m2.getSuffix() : m2.getName()), mc.fontRenderer.getStringWidth(m1.getSuffix().length() > 0 ? m1.getName() + " " + m1.getSuffix() : m1.getName()));
        }
    }

    public boolean isChatOpen() {
        return mc.currentScreen instanceof GuiChat;
    }

}
