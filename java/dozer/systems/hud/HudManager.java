package dozer.systems.hud;

import dozer.Dozer;
import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.util.LinkedList;

public class HudManager {

    private final LinkedList<HudElement> hudModules = new LinkedList<>();

    public void init() {
        new Reflections("dozer.systems.hud.impl").getTypesAnnotatedWith(HudElementInfo.class).forEach(this::addHudModule);

        Dozer.getSingleton().getEventBus().register(this);
    }

    @SneakyThrows
    public void addHudModule(Class<?> klass)  {
        hudModules.add((HudElement) klass.getConstructor().newInstance());
    }


    /**
     * @return List of HudModules
     */
    public LinkedList<HudElement> getHudModules() {return hudModules;}


}
