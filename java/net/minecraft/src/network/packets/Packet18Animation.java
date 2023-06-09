// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

package net.minecraft.src.network.packets;

import net.minecraft.src.NetHandler;
import net.minecraft.src.entity.Entity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

// Referenced classes of package net.minecraft.src:
//            Packet, Entity, NetHandler

public class Packet18Animation extends Packet {

  public int entityId;
  public int animate;

  public Packet18Animation() {}

  public Packet18Animation(Entity entity, int i) {
    entityId = entity.entityId;
    animate = i;
  }

  public void readPacketData(DataInputStream datainputstream) throws IOException {
    entityId = datainputstream.readInt();
    animate = datainputstream.readByte();
  }

  public void writePacketData(DataOutputStream dataoutputstream) throws IOException {
    dataoutputstream.writeInt(entityId);
    dataoutputstream.writeByte(animate);
  }

  public void processPacket(NetHandler nethandler) {
    nethandler.handleArmAnimation(this);
  }

  public int getPacketSize() {
    return 5;
  }
}
