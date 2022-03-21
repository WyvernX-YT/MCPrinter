package printer.printer.util;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public abstract class GUI implements InventoryHolder {
    public Inventory i;
    private String name;
    private int size;

    public GUI(String name, int size) {
        this.name = name;
        this.size = size;
        i = Bukkit.createInventory(this, size, name);
    }

    @Override
    public Inventory getInventory() {
        return i;
    }

    public String getTitle() {
        return name;
    }

}
