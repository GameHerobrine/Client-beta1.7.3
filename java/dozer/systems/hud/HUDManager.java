package dozer.systems.hud;

import dozer.Dozer;
import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.util.LinkedList;

public class HUDManager {

    private final LinkedList<HUDElement> hudModules = new LinkedList<>();

    public void init() {
        new Reflections("dozer.systems.hud.impl").getTypesAnnotatedWith(HUDElementInfo.class).forEach(this::addHudModule);

        Dozer.getSingleton().getEventBus().register(this);
    }

    @SneakyThrows
    public void addHudModule(Class<?> klass)  {
        hudModules.add((HUDElement) klass.getConstructor().newInstance());
    }


    /**
     * @return List of HudModules
     */
    public LinkedList<HUDElement> getHudModules() {return hudModules;}


}
