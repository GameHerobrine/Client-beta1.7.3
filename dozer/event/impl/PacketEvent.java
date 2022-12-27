package dozer.event.impl;

import dozer.event.EventCancellable;
import net.minecraft.src.network.packets.Packet;

/**
 * @author Shae
 * Called in NetworkManager
 */
public class PacketEvent extends EventCancellable {

    public Packet packet;

    public PacketEvent(Packet packet) {
        this.packet = packet;
    }

    public Packet getPacket() {
        return this.packet;
    }

}
