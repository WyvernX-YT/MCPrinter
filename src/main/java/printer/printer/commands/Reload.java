package printer.printer.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import printer.printer.Printer;
import printer.printer.util.Color;

import java.io.IOException;

public class Reload implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if(p.hasPermission("printer.reload")) {
            Printer.instance.saveConfig();
            Printer.config = Printer.instance.getConfig();
            p.sendMessage(Color.colorize("&cSuccessfully reloaded"));
        }
        return false;
    }
}
