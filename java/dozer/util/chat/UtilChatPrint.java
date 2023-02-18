package dozer.util.chat;

import dozer.Dozer;
import dozer.util.UtilMinecraft;

public class UtilChatPrint implements UtilMinecraft {

    private static final UtilChatColor colorUtil = new UtilChatColor();

    public static void print(String message) {
        mc.thePlayer.addChatMessage(String.format(colorUtil.RED + "[%s] " + colorUtil.RESET + "%s", Dozer.getSingleton().getName(), message));
    }

}