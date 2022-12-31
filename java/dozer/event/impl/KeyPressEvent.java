package dozer.event.impl;

/**
 * @author Shae
 * Called in Minecraft
 */
public class KeyPressEvent {

    private final int key;

    public KeyPressEvent(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

}
