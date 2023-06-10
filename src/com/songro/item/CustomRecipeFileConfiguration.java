package com.songro.item;

import com.songro.PluginCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.logging.Logger;

import static com.songro.PluginCore.plugin;

public class CustomRecipeFileConfiguration {

    public ItemStack item;
    Logger log = Bukkit.getLogger();
    ItemStack customItem;
    ItemMeta itemMeta;

    public void createItem() throws NullPointerException {
        boolean isRemovedRecipeExceptions = plugin.getCustomConfig().getBoolean("debug.removeRecipeExceptions");
        var isSection = PluginCore.getPlugin().getCustomRecipe().isConfigurationSection("item");
        if (!isSection) {
            log.severe("[PlayerPerms] 레시피 설정을 불러오는중 오류가 발생하였습니다.");
            log.severe("[PlayerPerms] 설정이 제대로 되어있는지 확인해주십시오.");
        } else {
            var loc = PluginCore.getPlugin().getCustomRecipe().getConfigurationSection("item").getKeys(false);
            log.info("[PlayerPerms] 확인된 아이템: " + String.valueOf(loc));
            String itemName = PluginCore.getPlugin().getCustomRecipe().getString("item." + loc + ".name");
            String itemLore1 = PluginCore.getPlugin().getCustomRecipe().getString("item." + loc + ".lore");
            String itemLore2 = PluginCore.getPlugin().getCustomRecipe().getString("item." + loc + ".lore2");
            item = PluginCore.getPlugin().getCustomRecipe().getItemStack("item." + loc + ".crafted_item");

            // line
            try {
            String line1 = PluginCore.getPlugin().getCustomRecipe().getIteStack("item." + loc + ".line1");
            String line2 = PluginCore.getPlugin().getCustomRecipe().getString("item." + loc + ".line2");
            String line3 = PluginCore.getPlugin().getCustomRecipe().getString("item." + loc + ".line3");
            String line4 = PluginCore.getPlugin().getCustomRecipe().getString("item." + loc + ".line4");
            String line5 = PluginCore.getPlugin().getCustomRecipe().getString("item." + loc + ".line5");
            String line6 = PluginCore.getPlugin().getCustomRecipe().getString("item." + loc + ".line6");
            String line7 = PluginCore.getPlugin().getCustomRecipe().getString("item." + loc + ".line7");
            String line8 = PluginCore.getPlugin().getCustomRecipe().getString("item." + loc + ".line8");
            String line9 = PluginCore.getPlugin().getCustomRecipe().getString("item." + loc + ".line9");

            log.info(line1);
            log.info(line2);
            log.info(line3);
            log.info(line4);
            log.info(line5);
            log.info(line6);
            log.info(line7);
            log.info(line8);
            log.info(line9);
            } except (NullPointException e) {
                log.severe("[PlayerPerms] 아이템 " + e + "이(가) 설정 방식이 틀렸습니다.")
            }

            if (!isRemovedRecipeExceptions) {
                try {
                    customItem = item;
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
                customItem = item;
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
                recipe.shape("ABC", "DEF", "GHI");
                recipe.setIngredient('A', Material.matchMaterial(line1));
                recipe.setIngredient('B', Material.matchMaterial(line2));
                recipe.setIngredient('C', Material.matchMaterial(line3));
                recipe.setIngredient('D', Material.matchMaterial(line4));
                recipe.setIngredient('E', Material.matchMaterial(line5));
                recipe.setIngredient('F', Material.matchMaterial(line6));
                recipe.setIngredient('G', Material.matchMaterial(line7));
                recipe.setIngredient('I', Material.matchMaterial(line8));
                recipe.setIngredient('I', Material.matchMaterial(line9));

                Bukkit.addRecipe(recipe);
            }
        }
    }
}
