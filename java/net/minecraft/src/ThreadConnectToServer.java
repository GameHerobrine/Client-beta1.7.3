// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

package net.minecraft.src;

import net.minecraft.client.Minecraft;
import net.minecraft.src.gui.GuiConnectFailed;
import net.minecraft.src.gui.GuiConnecting;
import net.minecraft.src.network.packets.Packet2Handshake;

import java.net.ConnectException;
import java.net.UnknownHostException;

// Referenced classes of package net.minecraft.src:
//            NetClientHandler, GuiConnecting, Packet2Handshake, Session,
//            GuiConnectFailed

public class ThreadConnectToServer extends Thread {

  final Minecraft mc; /* synthetic field */
  final String hostName; /* synthetic field */
  final int port; /* synthetic field */
  final GuiConnecting connectingGui; /* synthetic field */

  public ThreadConnectToServer(GuiConnecting guiconnecting, Minecraft minecraft, String s, int i) {
    //        super();
    connectingGui = guiconnecting;
    mc = minecraft;
    hostName = s;
    port = i;
  }

  public void run() {
    try {
      GuiConnecting.setNetClientHandler(connectingGui, new NetClientHandler(mc, hostName, port));
      if (GuiConnecting.isCancelled(connectingGui)) {
        return;
      }
      GuiConnecting.getNetClientHandler(connectingGui)
          .addToSendQueue(new Packet2Handshake(mc.session.username));
    } catch (UnknownHostException unknownhostexception) {
      if (GuiConnecting.isCancelled(connectingGui)) {
        return;
      }
      mc.displayGuiScreen(
          new GuiConnectFailed(
              "connect.failed",
              "disconnect.genericReason",
              new Object[] {
                  "Unknown host '" +
                      hostName +
                      "'"
              }));
    } catch (ConnectException connectexception) {
      if (GuiConnecting.isCancelled(connectingGui)) {
        return;
      }
      mc.displayGuiScreen(
          new GuiConnectFailed(
              "connect.failed",
              "disconnect.genericReason",
              new Object[] {connectexception.getMessage()}));
    } catch (Exception exception) {
      if (GuiConnecting.isCancelled(connectingGui)) {
        return;
      }
      exception.printStackTrace();
      mc.displayGuiScreen(
          new GuiConnectFailed(
              "connect.failed", "disconnect.genericReason", new Object[] {exception.toString()}));
    }
  }
}
