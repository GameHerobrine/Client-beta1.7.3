package dozer.module;

import dozer.Dozer;
import dozer.event.Subscribe;
import dozer.event.impl.KeyPressEvent;
import org.reflections.Reflections;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class ModuleManager {

    private static CopyOnWriteArrayList<Module> moduleList = new CopyOnWriteArrayList<>();

    /**
     * Uses reflections to get all classes that use annotations of ModuleInfo.
     * Then, it adds them to the module list.
     * Finally, it registers the module manager to the event bus to allow the use of the KeyPressEvent.
     */
    public void init() {
        System.out.println("Initializing modules...");

        Reflections reflections = new Reflections();

        // Get all classes that use annotations of ModuleInfo.
        reflections.getTypesAnnotatedWith(ModuleInfo.class).forEach(module -> {
            try {
                moduleList.add((Module) module.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        Dozer.getSingleton().getEventBus().register(new ModuleManager());

        System.out.println("Modules (" + moduleList.size() + "): " + moduleList.stream().map(Module::getName).collect(Collectors.joining(", ")));
        System.out.println("Modules initialized.");
    }

    @Subscribe
    public void onKeyPress(final KeyPressEvent event) {
        for (Module module : moduleList) {
            if (event.getKey() == module.getKeyBind()) {
                module.toggle();
            }
        }
    }

    /**
     * Gets all initialized modules.
     *
     * @return modules.
     */
    public List<Module> getModules() {
        return moduleList;
    }

    /**
     * Gets a module by its category.
     *
     * @return modules.
     */
    public List<Module> getModulesByCategory(ModuleCategory category) {
        return getModules().stream().filter(
                module -> module.getCategory().equals(category)
        ).collect(Collectors.toList());
    }


    /**
     * Gets a module by its name.
     *
     * @return The module.
     */
    public Module getModuleByName(String name) throws IllegalArgumentException {
        return moduleList.stream().filter(
                module -> module.getName().equalsIgnoreCase(name)
        ).findFirst().orElseThrow(IllegalArgumentException::new);
    }

    /**
     * Gets enabled modules.
     *
     * @return modules.
     */
    public List<Module> getEnabledModules() {
        return moduleList.stream().filter(
                Module::getState
        ).collect(Collectors.toList());
    }

}
