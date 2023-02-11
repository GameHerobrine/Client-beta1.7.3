package dozer.systems.hud;

import dozer.util.UtilMinecraft;
import dozer.util.render.UtilRender2D;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class HudElement implements UtilMinecraft, UtilRender2D {

    String name, description;
    int x, y, width, height;
    HudElementInfo hudModuleInfo = getClass().getAnnotation(HudElementInfo.class);
    boolean toggled;

    public HudElement() {
        setName(hudModuleInfo.name());
        setDescription(hudModuleInfo.description());
        setX(hudModuleInfo.x());
        setY(hudModuleInfo.y());
    }

    public abstract void render();


    public void setDimensions(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

}
