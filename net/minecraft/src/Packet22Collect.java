// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

// Referenced classes of package net.minecraft.src:
//            Packet, NetHandler

public class Packet22Collect extends Packet {

    public int collectedEntityId;
    public int collectorEntityId;

    public Packet22Collect() {
    }

    public void readPacketData(DataInputStream datainputstream)
            throws IOException {
        collectedEntityId = datainputstream.readInt();
        collectorEntityId = datainputstream.readInt();
    }

    public void writePacketData(DataOutputStream dataoutputstream)
            throws IOException {
        dataoutputstream.writeInt(collectedEntityId);
        dataoutputstream.writeInt(collectorEntityId);
    }

    public void processPacket(NetHandler nethandler) {
        nethandler.handleCollect(this);
    }

    public int getPacketSize() {
        return 8;
    }
}
