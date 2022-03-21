package printer.printer.util.guis;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.ItemStack;
import printer.printer.util.Color;
import printer.printer.util.GUI;

public class CraftingUI extends GUI implements Listener {
    public CraftingUI() {
        super(Color.colorize("&#ff4200R&#ff3919e&#ff3028c&#ff2734i&#fe1e3ep&#fb1648e"), 45);
        for(int i = 0; i < 11; i++) {
            this.i.setItem(i, BlackStainedGlass.createNew());
        }
        for(int i = 14; i < 19; i++) {
            this.i.setItem(i, BlackStainedGlass.createNew());
        }
        for(int i = 14; i < 19; i++) {
            this.i.setItem(i, BlackStainedGlass.createNew());
        }
        for(int i = 23; i < 24; i++) {
            this.i.setItem(i, BlackStainedGlass.createNew());
        }
        for(int i = 26; i < 28; i++) {
            this.i.setItem(i, BlackStainedGlass.createNew());
        }
        for(int i = 32; i < 44; i++) {
            this.i.setItem(i, BlackStainedGlass.createNew());
        }
        this.i.setItem(19, BlackStainedGlass.createNew());
        this.i.setItem(24, BlackStainedGlass.createNew());
        this.i.setItem(28, BlackStainedGlass.createNew());
        this.i.setItem(44, BlackStainedGlass.createNew());
    }

    public void setRecipe(ItemStack... items) {
        int i = 0;
        for(ItemStack it : items) {
            if(it == null) {
                it = new ItemStack(Material.AIR);
            }

            if(i == 0) {
                this.i.setItem(11, it);
            } else if(i == 1) {
                this.i.setItem(12, it);
            } else if(i == 2) {
                this.i.setItem(13, it);
            } else if(i == 3) {
                this.i.setItem(20, it);
            } else if(i == 4) {
                this.i.setItem(21, it);
            } else if(i == 5) {
                this.i.setItem(22, it);
            } else if(i == 6) {
                this.i.setItem(29, it);
            } else if(i == 7) {
                this.i.setItem(30, it);
            } else if(i == 8) {
                this.i.setItem(31, it);
            }
            i++;
        }
    }

    public void setResult(ItemStack i) {
        this.i.setItem(25, i);
    }

    @EventHandler
    void onClick(InventoryClickEvent e) {
        if(e.getView().getTitle().equals(new CraftingUI().getTitle())) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    void onDrag(InventoryDragEvent e) {
        if(e.getView().getTitle().equals(new CraftingUI().getTitle())) {
            e.setCancelled(true);
        }
    }
}
