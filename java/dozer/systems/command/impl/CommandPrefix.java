package dozer.systems.command.impl;

import dozer.Dozer;
import dozer.systems.command.Command;
import dozer.systems.command.CommandInfo;

@CommandInfo(name = "prefix", description = "Sets the client prefix")
public class CommandPrefix extends Command {

    @Override
    public void execute(String[] args) {
        Dozer.getSingleton().setPrefix(args[0]);
    }
}
