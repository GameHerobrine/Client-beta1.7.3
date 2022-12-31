package dozer.command.impl;

import dozer.Dozer;
import dozer.command.Command;
import dozer.command.CommandInfo;

@CommandInfo(name = "prefix", description = "Sets the client prefix")
public class PrefixCommand extends Command {

    @Override
    public void execute(String[] args) {
        Dozer.getSingleton().setPrefix(args[0]);
    }
}
