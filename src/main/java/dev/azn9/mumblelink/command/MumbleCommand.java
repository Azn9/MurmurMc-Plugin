package dev.azn9.mumblelink.command;

import dev.azn9.mumblelink.service.WebService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MumbleCommand implements CommandExecutor {

    private final WebService webService;

    public MumbleCommand(WebService webService) {
        this.webService = webService;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            return true;
        }

        if (args.length > 0) {
            if (commandSender.isOp()) {

            } else {

            }
        } else {

        }

        return true;
    }


}
