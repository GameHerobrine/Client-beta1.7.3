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

public class Packet70Bed extends Packet {

  public static final String field_25020_a[] = {"tile.bed.notValid", null, null};
  public int field_25019_b;

  public Packet70Bed() {}

  public void readPacketData(DataInputStream datainputstream) throws IOException {
    field_25019_b = datainputstream.readByte();
  }

  public void writePacketData(DataOutputStream dataoutputstream) throws IOException {
    dataoutputstream.writeByte(field_25019_b);
  }

  public void processPacket(NetHandler nethandler) {
    nethandler.func_25118_a(this);
  }

  public int getPacketSize() {
    return 1;
  }
}
