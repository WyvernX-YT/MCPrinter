package printer.printer.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import printer.printer.util.Color;

public class Info implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(Color.colorize("&8---------- &6Printer Plugin &8----------"));
        sender.sendMessage(Color.colorize("&8-          &cMade By WyvernX         &8-"));
        sender.sendMessage(Color.colorize(""));
        sender.sendMessage(Color.colorize("&7This plugin adds &6Printers &7to the game\n" +
                "&7these printers can be used to print images and stuff\n" +
                "&7There are some commands I will list here:\n" +
                "&71 - /ShowRecipe [ITEMNAME] - This command has a tab completer\n" +
                "&72 - /get [itemname] - this command also has tab completer\n" +
                "&73 - /get [itemname] [amount] - has tab completer\n" +
                "&74 - /reloadconfig - reloads the config admin only\n" +
                "&75 - /info - shows this info section\n"));
        return false;
    }
}
