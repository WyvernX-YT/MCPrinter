package printer.printer.commands;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import printer.printer.items.ItemManager;
import printer.printer.util.Color;
import printer.printer.util.guis.CraftingUI;

import java.util.ArrayList;
import java.util.List;

public class ShowRecipe implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Need to be a player");
        } else {
            Player p = (Player) sender;
            String arg = args[0];
            if(args.length == 1) {
                if(arg.equalsIgnoreCase("PRINTER_CORE")) {
                    CraftingUI i = new CraftingUI();
                    i.setRecipe(new ItemStack(Material.IRON_BLOCK), new ItemStack(Material.DIAMOND),new ItemStack(Material.IRON_BLOCK),
                                new ItemStack(Material.DIAMOND), new ItemStack(Material.BEACON),new ItemStack(Material.DIAMOND),
                                new ItemStack(Material.IRON_BLOCK),new ItemStack(Material.DIAMOND),new ItemStack(Material.IRON_BLOCK));
                    i.setResult(ItemManager.printerCore);
                    p.openInventory(i.getInventory());
                    p.playSound(p.getLocation(), Sound.BLOCK_CHEST_OPEN,2, 2);
                } else if(arg.equalsIgnoreCase("PRINTER_INFUSER")) {
                    CraftingUI i = new CraftingUI();
                    i.setRecipe(null, new ItemStack(Material.GOLDEN_APPLE), null,
                                null, new ItemStack(Material.IRON_BLOCK), null,
                                null, new ItemStack(Material.IRON_BLOCK), null);
                    i.setResult(ItemManager.printerInfuser);
                    p.openInventory(i.getInventory());
                    p.playSound(p.getLocation(), Sound.BLOCK_CHEST_OPEN,2, 2);
                } else if(arg.equalsIgnoreCase("PRINTER_WAND")) {
                    CraftingUI i = new CraftingUI();
                    i.setRecipe(null, new ItemStack(Material.GOLDEN_APPLE), null,
                                null, new ItemStack(Material.AMETHYST_SHARD), null,
                                null, new ItemStack(Material.AMETHYST_SHARD), null);
                    i.setResult(ItemManager.printerWand);
                    p.openInventory(i.getInventory());
                    p.playSound(p.getLocation(), Sound.BLOCK_CHEST_OPEN,2, 2);
                } else if(arg.equalsIgnoreCase("PRINTER")) {
                    CraftingUI i = new CraftingUI();
                    i.setRecipe(new ItemStack(Material.DIAMOND), ItemManager.printerInfuser, new ItemStack(Material.DIAMOND),
                                ItemManager.printerInfuser, ItemManager.printerCore, ItemManager.printerInfuser,
                                new ItemStack(Material.DIAMOND), ItemManager.printerInfuser, new ItemStack(Material.DIAMOND));
                    i.setResult(ItemManager.printerChest);
                    p.openInventory(i.getInventory());
                    p.playSound(p.getLocation(), Sound.BLOCK_CHEST_OPEN,2, 2);
                } else if(arg.equalsIgnoreCase("POCKET_PRINTER")) {
                    CraftingUI i = new CraftingUI();
                    i.setRecipe(ItemManager.printerInfuser , ItemManager.printerCore, ItemManager.printerInfuser,
                                ItemManager.printerCore, ItemManager.printerChest, ItemManager.printerCore,
                                ItemManager.printerInfuser, ItemManager.printerCore, ItemManager.printerInfuser);
                    i.setResult(ItemManager.pocketPrinter);
                    p.openInventory(i.getInventory());
                    p.playSound(p.getLocation(), Sound.BLOCK_CHEST_OPEN,2, 2);
                }
                else {
                    p.sendMessage(Color.colorize("&cNo recipe exists named \"" + arg + "\""));
                    p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT,2, 0.1f);
                }
            }
        }
        return false;
    }

    @Override
    public List <String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return new ArrayList <>(ItemManager.itemMap.keySet());
    }
}
