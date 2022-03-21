package printer.printer.items;

import org.bukkit.inventory.ItemStack;

public enum ItemCreateEvent {
    CHANGE,
    UPDATE,
    CREATE;
    ItemStack i;

    ItemCreateEvent() {

    }

    public ItemStack setItem(ItemStack i) {
        this.i = i;
        return i;
    }
}
