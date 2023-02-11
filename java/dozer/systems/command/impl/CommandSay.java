package dozer.systems.command.impl;

import dozer.systems.command.Command;
import dozer.systems.command.CommandInfo;

@CommandInfo(name = "say", description = "Relays message to chat")
public class CommandSay extends Command {

    @Override
    public void execute(String[] args) {
        mc.thePlayer.sendChatMessage(String.join(" ", args));
    }
}
