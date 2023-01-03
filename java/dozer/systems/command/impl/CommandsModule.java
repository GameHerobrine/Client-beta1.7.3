package dozer.systems.command.impl;

import dozer.Dozer;
import dozer.systems.command.Command;
import dozer.systems.command.CommandInfo;
import dozer.util.chat.ChatPrintUtil;

import java.util.stream.Collectors;

@CommandInfo(name = "commands", description = "Lists all commands")
public class CommandsModule extends Command {

    @Override
    public void execute(String[] args) {
        ChatPrintUtil.print(String.format("Commands (%s): %s%n",
                Dozer.getSingleton().getCommandManager().getCommands().size(),
                Dozer.getSingleton().getCommandManager().getCommands().stream().map(Command::getName).collect(Collectors.joining(", "))));
    }

}
