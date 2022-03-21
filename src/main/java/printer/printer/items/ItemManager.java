package printer.printer.items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import printer.printer.enchants.Ec;
import printer.printer.enchants.Glow;
import printer.printer.util.Color;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemManager {

    public static HashMap <String, ItemStack> itemMap = new HashMap<>();

    public static ItemStack printerChest;
    public static ItemStack printerWand;
    public static ItemStack printerCore;
    public static ItemStack printerInfuser;
    public static ItemStack pocketPrinter;
    public static ItemStack teleporter;

    public static void init() {
        createItemChest();
        onItemCreate(ItemCreateEvent.CREATE, "PRINTER", printerChest);

        createItemWand();
        onItemCreate(ItemCreateEvent.CREATE, "PRINTER_WAND", printerWand);
        
        createItemInfuser();
        onItemCreate(ItemCreateEvent.CREATE, "PRINTER_INFUSER", printerInfuser);

        createPrinterCore();
        onItemCreate(ItemCreateEvent.CREATE, "PRINTER_CORE", printerCore);

        createPocketPrinter();
        onItemCreate(ItemCreateEvent.CREATE, "POCKET_PRINTER", pocketPrinter);
        
        createTeleporter();
        onItemCreate(ItemCreateEvent.CREATE, "TELEPORTER", teleporter);

    }

    private static void createTeleporter() {
        ItemStack i = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(Color.colorize("&#1111e8T&#4100e4e&#5900e0l&#6b00dbe&#7b00d6p&#8800d1o&#9400ccr&#9f00c7t&#a900c1e&#b200bcr"));
        m.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        m.addItemFlags(ItemFlag.HIDE_DYE);
        m.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        m.addEnchant(Ec.glow, 13424, true);
        m.setUnbreakable(true);
        ArrayList<String> lore = new ArrayList<>();
        lore.add(Color.colorize(Color.textColor + "Allows you to teleport"));
        lore.add(Color.colorize(""));
        lore.add(Color.colorize(Color.darkTextColor + "Teleporter"));
        m.setLore(lore);
        i.setItemMeta(m);
        teleporter = i;
    }

    private static void createPocketPrinter() {
        ItemStack i = new ItemStack(Material.MAGENTA_SHULKER_BOX);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(Color.colorize("&#1180e8P&#2e7ee9o&#407ce9c&#4f79e9k&#5c77e9e&#6974e8t &#7471e7P&#7e6ee6r&#896be4i&#9267e2n&#9c63dft&#a55fdce&#ad5bd8r"));
        m.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        m.addItemFlags(ItemFlag.HIDE_DYE);
        m.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        m.addEnchant(Ec.glow, 27422, true);
        m.setUnbreakable(true);
        ArrayList<String> lore = new ArrayList<>();
        lore.add(Color.colorize(Color.textColor + "basically a &#ffa200P&#f2a400r&#e5a500i&#d8a600n&#cba700t&#bfa700e&#b2a700r in your pocket"));
        lore.add(Color.colorize(""));
        lore.add(Color.colorize(Color.darkTextColor + "Pocket Printer"));
        m.setLore(lore);
        i.setItemMeta(m);
        pocketPrinter = i;
    }


    private static void onItemCreate(ItemCreateEvent e, String id, ItemStack i) {
        if(e.equals(ItemCreateEvent.CREATE)) {
            itemMap.put(id, i);
        }
    }

    private static void createPrinterCore() {
        ItemStack i = new ItemStack(Material.NETHER_STAR);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(Color.colorize("&#8f0fe9P&#851fe9r&#7b29e9i&#7031e8n&#6637e7t&#5b3ce6e&#4f40e4r &#4243e2C&#3446e0o&#2249der&#004bdbe"));
        m.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        m.addItemFlags(ItemFlag.HIDE_DYE);
        m.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        m.addEnchant(Ec.glow, 2314, true);
        m.setUnbreakable(true);
        ArrayList<String> lore = new ArrayList<>();
        lore.add(Color.colorize(Color.textColor + "Used to craft a &#ffa200P&#f2a400r&#e5a500i&#d8a600n&#cba700t&#bfa700e&#b2a700r"));
        lore.add(Color.colorize(""));
        lore.add(Color.colorize(Color.darkTextColor + "Printer Core"));
        m.setLore(lore);
        i.setItemMeta(m);
        printerCore = i;
    }

    private static void createItemInfuser() {
        ItemStack i = new ItemStack(Material.STICK);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(Color.colorize("&#0cd51fI&#00d22en&#00cf39f&#00cc43u&#00c94bs&#00c653e&#00c35ar"));
        m.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        m.addItemFlags(ItemFlag.HIDE_DYE);
        m.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        m.addEnchant(Ec.glow, 8432, true);
        m.setUnbreakable(true);
        ArrayList<String> lore = new ArrayList<>();
        lore.add(Color.colorize(Color.textColor + "Used to craft a &#ffa200P&#f2a400r&#e5a500i&#d8a600n&#cba700t&#bfa700e&#b2a700r"));
        lore.add(Color.colorize(""));
        lore.add(Color.colorize(Color.darkTextColor + "Printer Infuser"));
        m.setLore(lore);
        i.setItemMeta(m);
        printerInfuser = i;
    }

    private static void createItemWand() {
        ItemStack i = new ItemStack(Material.STICK);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(Color.colorize("&#e90f94P&#e31b98r&#dc239ci&#d52a9fn&#ce30a2t&#c735a5e&#bf3aa8r &#b83eaaW&#b041aba&#a845acn&#a147add"));
        m.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        m.addItemFlags(ItemFlag.HIDE_DYE);
        m.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        m.addEnchant(Ec.glow, 9348, true);
        m.setUnbreakable(true);
        ArrayList<String> lore = new ArrayList<>();
        lore.add(Color.colorize(Color.textColor + "Right click a &#ffa200P&#f2a400r&#e5a500i&#d8a600n&#cba700t&#bfa700e&#b2a700r"));
        lore.add(Color.colorize(Color.textColor + "With this to print!"));
        lore.add(Color.colorize(""));
        lore.add(Color.colorize(Color.darkTextColor + "Printer Wand"));
        m.setLore(lore);
        i.setItemMeta(m);
        printerWand = i;
    }

    private static void createItemChest() {
        ItemStack i = new ItemStack(Material.CHEST);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(Color.colorize("&#ffa200P&#f2a400r&#e5a500i&#d8a600n&#cba700t&#bfa700e&#b2a700r"));
        m.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        m.addItemFlags(ItemFlag.HIDE_DYE);
        m.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        m.addEnchant(Ec.glow, 4321, true);
        m.setUnbreakable(true);
        ArrayList<String> lore = new ArrayList<>();
        lore.add(Color.colorize(Color.textColor + "Place items inside the &#ffa200P&#f2a400r&#e5a500i&#d8a600n&#cba700t&#bfa700e&#b2a700r"));
        lore.add(Color.colorize(Color.textColor + "Then right click it with a"));
        lore.add(Color.colorize(Color.textColor + "Printer Wand to print!"));
        lore.add(Color.colorize(""));
        lore.add(Color.colorize(Color.darkTextColor + "Printer Chest"));
        m.setLore(lore);
        i.setItemMeta(m);
        printerChest = i;
    }


}
