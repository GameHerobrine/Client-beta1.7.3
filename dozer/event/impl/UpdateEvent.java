package dozer.event.impl;

/**
 * @author Shae
 * Called in EntityPlayer
 */
public class UpdateEvent {

    public UpdateType type;

    public UpdateEvent(UpdateType type) {
        this.type = type;
    }

    public boolean isPre() {
        if (type == null) return false;
        return type == UpdateType.PRE;
    }

    public boolean isPost() {
        if (type == null) return false;
        return type == UpdateType.POST;
    }

    public enum UpdateType {
        PRE, POST
    }

}
