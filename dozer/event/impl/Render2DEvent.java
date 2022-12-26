package dozer.event.impl;

/**
 * @author Shae
 * Called in GuiIngame
 */
public class Render2DEvent {

    private float displayWidth;
    private float displayHeight;

    public Render2DEvent(float displayWidth, float displayHeight) {
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;
    }

    public float getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayWidth(float displayWidth) {
        this.displayWidth = displayWidth;
    }

    public float getDisplayHeight() {
        return displayHeight;
    }

    public void setDisplayHeight(float displayHeight) {
        this.displayHeight = displayHeight;
    }

}
