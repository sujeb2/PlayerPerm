package com.songro.commands.GUI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class SettingGUI implements InventoryHolder {
    private Inventory inv;

    private void init() {
        ItemStack item;
        for (int i = 0; i < 4; i++) {
            item = createItem(" ", Material.GRAY_STAINED_GLASS_PANE, Collections.singletonList("Test item."));
            inv.setItem(i, item);
        }
    }

    /*
    Function for making custom Item faster.
     */
    public ItemStack createItem(String name, Material mat, List<String> lore) {
        ItemStack item = new ItemStack(mat, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public void Select() {
        var inv = Bukkit.createInventory(this, 27, "설정");
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        return inv;
    }
}
