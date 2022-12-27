package dozer.command.impl;

import dozer.command.Command;
import dozer.command.CommandInfo;

@CommandInfo(name = "say", description = "Relays message to chat")
public class SayCommand extends Command {

    @Override
    public void execute(String[] args) {
        mc.thePlayer.sendChatMessage(String.join(" ", args));
    }
}
