package dozer.systems.command.impl;

import dozer.Dozer;
import dozer.systems.command.Command;
import dozer.systems.command.CommandInfo;
import dozer.systems.module.Module;
import dozer.util.chat.UtilChatPrint;

import java.util.stream.Collectors;

@CommandInfo(name = "modules", description = "Lists all modules")
public class CommandModules extends Command {

    @Override
    public void execute(String[] args) {
        UtilChatPrint.print(String.format("Modules (%s): %s%n",
                Dozer.getSingleton().getModuleManager().getModules().size(),
                Dozer.getSingleton().getModuleManager().getModules().stream().map(Module::getName).collect(Collectors.joining(", "))));
    }


}
