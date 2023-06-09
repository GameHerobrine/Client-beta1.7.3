// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

package net.minecraft.src.network.packets;

import net.minecraft.src.NetHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

// Referenced classes of package net.minecraft.src:
//            Packet, NetHandler

public class Packet105UpdateProgressbar extends Packet {

  public int windowId;
  public int progressBar;
  public int progressBarValue;

  public Packet105UpdateProgressbar() {}

  public void processPacket(NetHandler nethandler) {
    nethandler.func_20090_a(this);
  }

  public void readPacketData(DataInputStream datainputstream) throws IOException {
    windowId = datainputstream.readByte();
    progressBar = datainputstream.readShort();
    progressBarValue = datainputstream.readShort();
  }

  public void writePacketData(DataOutputStream dataoutputstream) throws IOException {
    dataoutputstream.writeByte(windowId);
    dataoutputstream.writeShort(progressBar);
    dataoutputstream.writeShort(progressBarValue);
  }

  public int getPacketSize() {
    return 5;
  }
}
