package dozer.event.impl;

import net.minecraft.src.network.packets.Packet;

/**
 * @author Shae
 * Called in NetworkManager
 */
public class PacketEvent {

    public boolean cancelled;
    public Packet packet;

    public PacketEvent(Packet packet) {
        this.packet = packet;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public Packet getPacket() {
        return this.packet;
    }

}
