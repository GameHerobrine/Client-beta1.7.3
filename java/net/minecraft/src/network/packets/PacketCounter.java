package net.minecraft.src.network.packets;

import net.minecraft.src.Empty1;

class PacketCounter {

  private int totalPackets;
  private long totalBytes;

  private PacketCounter() {}

  PacketCounter(Empty1 empty1) {
    this();
  }

  public void addPacket(int i) {
    totalPackets++;
    totalBytes += i;
  }
}
