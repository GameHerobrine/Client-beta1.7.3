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

public class Packet6SpawnPosition extends Packet {

  public int xPosition;
  public int yPosition;
  public int zPosition;

  public Packet6SpawnPosition() {}

  public void readPacketData(DataInputStream datainputstream) throws IOException {
    xPosition = datainputstream.readInt();
    yPosition = datainputstream.readInt();
    zPosition = datainputstream.readInt();
  }

  public void writePacketData(DataOutputStream dataoutputstream) throws IOException {
    dataoutputstream.writeInt(xPosition);
    dataoutputstream.writeInt(yPosition);
    dataoutputstream.writeInt(zPosition);
  }

  public void processPacket(NetHandler nethandler) {
    nethandler.handleSpawnPosition(this);
  }

  public int getPacketSize() {
    return 12;
  }
}
