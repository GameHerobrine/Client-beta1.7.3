package dozer.command;


import dozer.Dozer;
import dozer.event.Subscribe;
import dozer.event.impl.ChatEvent;
import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class CommandManager {

    private final CopyOnWriteArrayList<Command> commands = new CopyOnWriteArrayList<>();

    public void init() {
        System.out.println("Initializing commands...");

        // Get all classes that use annotations of CommandInfo.
        new Reflections("dozer.command.impl").getTypesAnnotatedWith(CommandInfo.class).forEach(this::addCommand);

        Dozer.getSingleton().getEventBus().register(this);

        System.out.println("Commands (" + commands.size() + "): " + commands.stream().map(Command::getName).collect(Collectors.joining(", ")));
        System.out.println("Command initialized.");
    }

    @SneakyThrows
    private void addCommand(Class<?> clazz) {
        this.commands.add((Command) clazz.getConstructor().newInstance());
    }

    @Subscribe
    public void onChat(ChatEvent event) {

        String message = event.getMessage();
        String prefix = Dozer.getSingleton().getPrefix();
        if(!message.startsWith(prefix) || (message.startsWith(prefix) && message.length() <= 1)) return;
        getCommandByName(message.substring(1).split(" ")[0]).execute(Arrays.copyOfRange(message.split(" "), 1, message.split(" ").length));

        event.setCancelled(true);
    }

    public Command getCommandByName(String name) throws IllegalArgumentException {
        return commands.stream().filter(
                command -> command.getName().equalsIgnoreCase(name)
        ).findFirst().orElseThrow(IllegalArgumentException::new);
    }


}
