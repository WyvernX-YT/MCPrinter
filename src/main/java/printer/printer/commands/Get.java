package printer.printer.commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import printer.printer.items.ItemManager;
import printer.printer.util.Color;
import printer.printer.util.Config;

import java.util.ArrayList;
import java.util.List;

public class Get implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {
           sender.sendMessage(Color.colorize("&cYou must be a player to do this"));
        } else {
            Player p = (Player) sender;
            if(p.hasPermission("printer.get")) {
                if(args.length >= 1) {
                    String arg = args[0];
                    int i = 0;
                    if(args.length >= 2) {
                        String arg2 =  args[1];
                        int num = 0;
                        try {
                            num = Integer.parseInt(arg2);
                            for(String id : ItemManager.itemMap.keySet()) {
                                if(id.toLowerCase().equals(arg.toLowerCase())) {
                                    for(int j = 1; j <= num; j++ ) {
                                        if(p.getInventory().firstEmpty() > -1) {
                                            p.getInventory().addItem(ItemManager.itemMap.get(id));
                                        } else {
                                            Bukkit.getWorld(p.getWorld().getUID()).dropItemNaturally(p.getLocation(), ItemManager.itemMap.get(id));
                                        }
                                    }
                                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 4,2);
                                    p.sendMessage(Color.colorize("&aGave you " + ItemManager.itemMap.get(id).getItemMeta().getDisplayName() + " &#10c765x" + num));
                                    i++;
                                }

                            }

                        } catch (Exception e) {
                            p.sendMessage(Color.colorize("&cPlease only use numbers in amount eg /get ITEM 64"));
                            p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT,2, 0.1f);
                        }
                    } else {
                        for(String id : ItemManager.itemMap.keySet()) {
                            if(id.toLowerCase().equals(arg.toLowerCase())) {
                                p.getInventory().addItem(ItemManager.itemMap.get(id));
                                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 4,2);
                                p.sendMessage(Color.colorize("&aGave you " + ItemManager.itemMap.get(id).getItemMeta().getDisplayName()));
                                i++;
                            }

                        }
                    }


                    if(i == 0) {
                        if(arg.equalsIgnoreCase("all")) {
                            if(args.length == 1) {
                                for(ItemStack it : ItemManager.itemMap.values()) {
                                    p.getInventory().addItem(it);
                                }
                            } else if(args.length == 2) {
                                int num = 0;
                                try {
                                    num = Integer.parseInt(args[1]);
                                    for(int j = 1; j <= num; j++) {
                                        for(ItemStack it : ItemManager.itemMap.values()) {
                                            p.getInventory().addItem(it);
                                        }
                                    }
                                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 4,2);
                                    p.sendMessage(Color.colorize("&aGave you &c&LALL " + "&#10c765x" + num));
                                } catch(Exception e) { ;
                                   //
                                }
                            }
                        } else {
                            p.sendMessage(Color.colorize("&cNo item exists named \"" + arg + "\""));
                            p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 4, 0.1f);
                        }
                    }

                } else {
                    p.sendMessage(Color.colorize("&cToo little arguments"));
                }
            } else {
                p.sendMessage(Color.colorize("&cYou do not have permission to do this."));
                if(Config.getSection("Title").getConfigurationSection("Blindness").getBoolean("enabled", true)) {
                    int duration = Config.getSection("Title").getConfigurationSection("Blindness").getInt("time");
                    p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, duration,1));
                }
                if(Config.getSection("Title").getBoolean("enabled")) {
                    p.sendTitle(Color.colorize("&cNo permission"), "", 10, 10, 10);
                }
            }
        }
        return false;
    }

    @Override
    public List <String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> arg = new ArrayList<>();
        for(String id : ItemManager.itemMap.keySet()) {
            arg.add(id.toUpperCase());
        }
        arg.add("ALL");
        return arg;
    }
}
