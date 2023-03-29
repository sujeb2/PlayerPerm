package com.songro.item;

import com.songro.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import static com.songro.Main.plugin;

public class CustomRecipeFileConfiguration {

    private String itemName;
    private String itemLore1;
    private String itemLore2;
    public String item;
    Logger log = Bukkit.getLogger();
    ItemStack customItem;
    ItemMeta itemMeta;

    // line
    // "LINE1", "LINE2", "LINE3",
    // "LINE4", "LINE5", "LINE6",
    // "LINE7", "LINE8", "LINE9"
    private String line1;
    private String line2;
    private String line3;
    private String line4;
    private String line5;
    private String line6;
    private String line7;
    private String line8;
    private String line9;

    public void createItem() throws NullPointerException {
        itemName = Main.plugin.getCustomRecipe().getString("*.main.name");
        itemLore1 = Main.plugin.getCustomRecipe().getString("*.main.lore");
        itemLore2 = Main.plugin.getCustomRecipe().getString("*.main.lore2");
        item = Main.plugin.getCustomRecipe().getString("*.recipeSetting.item");
        try {
            customItem = new ItemStack(Material.getMaterial(item), 1);
            itemMeta = customItem.getItemMeta();
            assert itemMeta != null;
            if(itemMeta.getDisplayName() == null) {
                itemMeta.setDisplayName(ChatColor.RED + "설정되지 않음");
            } else {
                itemMeta.setDisplayName(itemName);
            }
        } catch (NullPointerException np2) {
            log.severe("아이템을 설정하는중에 오류가 발생했습니다.");
            log.severe("오류 로그: " + np2);
        }
        try {
            if (itemLore1.length() == 0 || itemLore2.length() == 0) {
                itemMeta.setLore(List.of(""));
            } else if (itemLore1.length() == 0) {
                itemMeta.setLore(List.of(itemLore2));
            } else if (itemLore2.length() == 0) {
                itemMeta.setLore(List.of(itemLore1));
            }
        } catch (NullPointerException nullpointer) {
            log.severe(String.valueOf(nullpointer));
        }
        itemMeta.setLore(List.of(ChatColor.WHITE + itemLore1, itemLore2));

        customItem.setItemMeta(itemMeta);
        {
            NamespacedKey key = new NamespacedKey(plugin, "customItem");
            ShapedRecipe recipe = new ShapedRecipe(key, customItem);
            recipe.shape("A", "B", "C",
                        "D", "E", "F",
                        "G", "H", "I");
            recipe.setIngredient('A', Objects.requireNonNull(Material.getMaterial(line1)));
            recipe.setIngredient('B', Objects.requireNonNull(Material.getMaterial(line2)));
            recipe.setIngredient('C', Objects.requireNonNull(Material.getMaterial(line3)));
            recipe.setIngredient('D', Objects.requireNonNull(Material.getMaterial(line4)));
            recipe.setIngredient('E', Objects.requireNonNull(Material.getMaterial(line5)));
            recipe.setIngredient('F', Objects.requireNonNull(Material.getMaterial(line6)));
            recipe.setIngredient('G', Objects.requireNonNull(Material.getMaterial(line7)));
            recipe.setIngredient('H', Objects.requireNonNull(Material.getMaterial(line8)));
            recipe.setIngredient('I', Objects.requireNonNull(Material.getMaterial(line9)));

            Bukkit.addRecipe(recipe);
        }
    }

}
