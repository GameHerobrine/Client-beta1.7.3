package dozer.systems.command.impl;

import dozer.Dozer;
import dozer.systems.command.Command;
import dozer.systems.command.CommandInfo;
import dozer.util.chat.UtilChatPrint;

import java.util.stream.Collectors;

@CommandInfo(name = "commands", description = "Lists all commands")
public class CommandCommands extends Command {

    @Override
    public void execute(String[] args) {
        UtilChatPrint.print(String.format("Commands (%s): %s%n",
                Dozer.getSingleton().getCommandManager().getCommands().size(),
                Dozer.getSingleton().getCommandManager().getCommands().stream().map(Command::getName).collect(Collectors.joining(", "))));
    }

}
