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

public class Packet8UpdateHealth extends Packet {

  public int healthMP;

  public Packet8UpdateHealth() {}

  public void readPacketData(DataInputStream datainputstream) throws IOException {
    healthMP = datainputstream.readShort();
  }

  public void writePacketData(DataOutputStream dataoutputstream) throws IOException {
    dataoutputstream.writeShort(healthMP);
  }

  public void processPacket(NetHandler nethandler) {
    nethandler.handleHealth(this);
  }

  public int getPacketSize() {
    return 2;
  }
}
