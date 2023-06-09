package dozer.systems.command;

import dozer.util.UtilMinecraft;

public abstract class Command implements UtilMinecraft {

    String name, description;
    CommandInfo commandInfo = getClass().getAnnotation(CommandInfo.class);

    public Command() {
        setName(commandInfo.name());
        setDescription(commandInfo.description());
    }

    public abstract void execute(String[] args);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}