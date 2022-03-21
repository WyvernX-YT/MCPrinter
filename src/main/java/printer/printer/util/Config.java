package printer.printer.util;

import org.bukkit.configuration.ConfigurationSection;
import printer.printer.Printer;

public class Config {

    public static Object get(String path) {
        return Printer.config.get(path);
    }

    public static ConfigurationSection getSection(String path) {
        return Printer.config.getConfigurationSection(path);
    }
}
