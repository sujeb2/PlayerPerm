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

    public String item;
    Logger log = Bukkit.getLogger();
    ItemStack customItem;
    ItemMeta itemMeta;

    public void createItem() throws NullPointerException {
        boolean isRemovedRecipeExceptions = plugin.getCustomConfig().getBoolean("debug.removeRecipeExceptions");
        var isSection = Main.getPlugin().getCustomRecipe().isConfigurationSection("item");
        if (!isSection) {
            log.severe("[PlayerPerms] 레시피 설정을 불러오는중 오류가 발생하였습니다.");
            log.severe("[PlayerPerms] 설정이 제대로 되어있는지 확인해주십시오.");
        } else {
            var loc = Main.getPlugin().getCustomRecipe().getConfigurationSection("item").getKeys(false);
            log.info(String.valueOf(loc));
            String itemName = Main.getPlugin().getCustomRecipe().getString("item." + loc + ".name");
            String itemLore1 = Main.getPlugin().getCustomRecipe().getString("item." + loc + ".lore");
            String itemLore2 = Main.getPlugin().getCustomRecipe().getString("item." + loc + ".lore2");
            item = Main.getPlugin().getCustomRecipe().getString("item." + loc + ".crafted_item");

            // line
            // "LINE1", "LINE2", "LINE3",
            // "LINE4", "LINE5", "LINE6",
            // "LINE7", "LINE8", "LINE9"
            String line1 = Main.getPlugin().getCustomRecipe().getString("item." + loc + ".line1");
            String line2 = Main.getPlugin().getCustomRecipe().getString("item." + loc + ".line2");
            String line3 = Main.getPlugin().getCustomRecipe().getString("item." + loc + ".line3");
            String line4 = Main.getPlugin().getCustomRecipe().getString("item." + loc + ".line4");
            String line5 = Main.getPlugin().getCustomRecipe().getString("item." + loc + ".line5");
            String line6 = Main.getPlugin().getCustomRecipe().getString("item." + loc + ".line6");
            String line7 = Main.getPlugin().getCustomRecipe().getString("item." + loc + ".line7");
            String line8 = Main.getPlugin().getCustomRecipe().getString("item." + loc + ".line8");
            String line9 = Main.getPlugin().getCustomRecipe().getString("item." + loc + ".line9");
            if (!isRemovedRecipeExceptions) {
                try {
                    customItem = new ItemStack(Objects.requireNonNull(Material.getMaterial(item)), 1);
                    if (customItem == null) {
                        customItem = new ItemStack(Material.BARRIER, 1);
                    }
                    itemMeta = customItem.getItemMeta();
                    assert itemMeta != null;
                    if (itemMeta.getDisplayName() == null) {
                        itemMeta.setDisplayName(ChatColor.RED + "설정되지 않음");
                    } else {
                        itemMeta.setDisplayName(itemName);
                    }
                } catch (NullPointerException np2) {
                    log.severe("아이템을 설정하는중에 오류가 발생했습니다.");
                    log.severe("오류 로그: " + np2);
                }
            } else {
                customItem = new ItemStack(Objects.requireNonNull(Material.getMaterial(item)), 1);
                if (customItem == null) {
                    customItem = new ItemStack(Material.BARRIER, 1);
                }
                itemMeta = customItem.getItemMeta();
                assert itemMeta != null;
                if (itemMeta.getDisplayName() == null) {
                    itemMeta.setDisplayName(ChatColor.RED + "설정되지 않음");
                } else {
                    itemMeta.setDisplayName(itemName);
                }
            }
            if (!isRemovedRecipeExceptions) {
                try {
                    if (itemLore1 == null || itemLore2 == null) {
                        itemMeta.setLore(List.of("설명 없음"));
                    } else if (itemLore1 == null) {
                        itemMeta.setLore(List.of(itemLore2));
                    } else if (itemLore2 == null) {
                        itemMeta.setLore(List.of(itemLore1));
                    } else {
                        itemMeta.setLore(List.of(ChatColor.WHITE + itemLore1, itemLore2));
                    }
                } catch (NullPointerException nullpointer) {
                    log.severe(String.valueOf(nullpointer));
                }
            } else {
                if (itemLore1 == null || itemLore2 == null) {
                    itemMeta.setLore(List.of("설명 없음"));
                } else if (itemLore1 == null) {
                    itemMeta.setLore(List.of(itemLore2));
                } else if (itemLore2 == null) {
                    itemMeta.setLore(List.of(itemLore1));
                } else {
                    itemMeta.setLore(List.of(ChatColor.WHITE + itemLore1, itemLore2));
                }
            }

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
}
