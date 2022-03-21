package printer.printer.recipes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.material.MaterialData;
import printer.printer.Printer;
import printer.printer.items.ItemManager;

public class RecipeManager {

    public static void init() {
        createPrinterRecipe();
        createCoreRecipe();
        createInfuserRecipe();
        createPrinterWandRecipe();
        createPocketPrinterRecipe();
    }

    private static void createPocketPrinterRecipe() {
        NamespacedKey key = new NamespacedKey(Printer.instance, "pocket_printer_recipe");
        ShapedRecipe recipe = new ShapedRecipe(key, ItemManager.pocketPrinter);

        recipe.shape("ABA", "BCB", "ABA");
        recipe.setIngredient('C', new RecipeChoice.ExactChoice(ItemManager.printerChest));
        recipe.setIngredient('A', new RecipeChoice.ExactChoice(ItemManager.printerInfuser));
        recipe.setIngredient('B', new RecipeChoice.ExactChoice(ItemManager.printerCore));
        Bukkit.addRecipe(recipe);
    }

    private static void createPrinterWandRecipe() {
        NamespacedKey key = new NamespacedKey(Printer.instance, "printer_wand_recipe");
        ShapedRecipe recipe = new ShapedRecipe(key, ItemManager.printerWand);

        recipe.shape(" B ", " A ", " A ");
        recipe.setIngredient('A', Material.AMETHYST_SHARD);
        recipe.setIngredient('B', Material.GOLDEN_APPLE);
        Bukkit.addRecipe(recipe);

    }

    private static void createInfuserRecipe() {
        NamespacedKey key = new NamespacedKey(Printer.instance, "printer_infuser_recipe");
        ShapedRecipe recipe = new ShapedRecipe(key, ItemManager.printerInfuser);

        recipe.shape(" B ", " A ", " A ");
        recipe.setIngredient('A', Material.IRON_BLOCK);
        recipe.setIngredient('B', Material.GOLDEN_APPLE);
        Bukkit.addRecipe(recipe);
    }

    private static void createCoreRecipe() {
        NamespacedKey key = new NamespacedKey(Printer.instance, "printer_core_recipe");
        ShapedRecipe recipe = new ShapedRecipe(key, ItemManager.printerCore);

        recipe.shape("ABA", "BCB", "ABA");

        recipe.setIngredient('A', Material.IRON_BLOCK);
        recipe.setIngredient('B', Material.DIAMOND);
        recipe.setIngredient('C', Material.BEACON);
        Bukkit.addRecipe(recipe);
    }

    private static void createPrinterRecipe() {
        NamespacedKey key = new NamespacedKey(Printer.instance, "printer_recipe");
        ShapedRecipe recipe = new ShapedRecipe(key, ItemManager.printerChest);

        recipe.shape("ABA", "BCB", "ABA");
        recipe.setIngredient('A', Material.DIAMOND);
        recipe.setIngredient('B', new RecipeChoice.ExactChoice(ItemManager.printerInfuser));
        recipe.setIngredient('C', new RecipeChoice.ExactChoice(ItemManager.printerCore));
        Bukkit.addRecipe(recipe);
    }
}
