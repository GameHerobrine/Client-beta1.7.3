package dozer.util.chat;

import dozer.Dozer;
import dozer.util.MinecraftUtil;

public class ChatPrintUtil implements MinecraftUtil {

    private static final ChatColorUtil colorUtil = new ChatColorUtil();

    public static void print(String message) {
        mc.thePlayer.addChatMessage(String.format(colorUtil.RED + "[%s] " + colorUtil.RESET + "%s", Dozer.getSingleton().getName(), message));
    }

}
