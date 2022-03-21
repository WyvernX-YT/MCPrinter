package printer.printer;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;
import printer.printer.commands.Get;
import printer.printer.commands.Info;
import printer.printer.commands.Reload;
import printer.printer.commands.ShowRecipe;
import printer.printer.enchants.Glow;
import printer.printer.items.ItemManager;
import printer.printer.listeners.Events;
import printer.printer.recipes.RecipeManager;
import printer.printer.util.guis.CraftingUI;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.logging.Logger;

public final class Printer extends JavaPlugin {

    public static Printer instance;
    public static FileConfiguration config;
    @Override
    public void onEnable() {
        instance = this;
        ItemManager.init();
        Bukkit.getPluginManager().registerEvents(new Events(), this);
        Bukkit.getPluginManager().registerEvents(new CraftingUI(), this);
        Bukkit.getPluginCommand("get").setExecutor(new Get ());
        Bukkit.getPluginCommand("get").setTabCompleter(new Get());
        Bukkit.getPluginCommand("showrecipe").setExecutor(new ShowRecipe());
        Bukkit.getPluginCommand("showrecipe").setTabCompleter(new ShowRecipe());

        Bukkit.getPluginCommand("reloadConfig").setExecutor(new Reload());
        initializeConfig();
        Logger logger = Bukkit.getLogger();
        logger.info("Printer Successfully loaded!");
        registerGlow();
        RecipeManager.init();

    }

    @Override
    public void onDisable() {
        Logger logger = Bukkit.getLogger();
        logger.info("Printer Successfully unloaded!");
    }

    private void initializeConfig() {
        this.saveDefaultConfig();
        config = instance.getConfig();
        config.getConfigurationSection("Title").addDefault("enabled", true);
        config.getConfigurationSection("Title").getConfigurationSection("Blindness").addDefault("enabled", false);
        config.getConfigurationSection("Title").getConfigurationSection("Blindness").addDefault("time", 0);

        config.getConfigurationSection("permissions").getConfigurationSection("playerUseWand").addDefault("enabled", false);
        config.getConfigurationSection("permissions").getConfigurationSection("playerUseWand").addDefault("permission", "printer.playerUseWand");
        config.getConfigurationSection("permissions").getConfigurationSection("playerUsePrinter").addDefault("enabled", false);
        config.getConfigurationSection("permissions").getConfigurationSection("playerUsePrinter").addDefault("permission", "printer.playerUsePrinter");
        saveConfig();
        config = instance.getConfig();
    }

    public void registerGlow() {
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Glow glow = new Glow(NamespacedKey.fromString("printer:glow_enchantment"));
            Enchantment.registerEnchantment(glow);
        }
        catch (IllegalArgumentException e){
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


}
