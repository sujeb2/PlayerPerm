package com.songro.event;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.inventory.ItemStack;

public class CustomEnchant implements Listener {

    @EventHandler
    public void customEnchant (EnchantItemEvent e) {
        Player player = e.getEnchanter();
        ItemStack item = e.getItem();
        Material mat = item.getType();
    }

}
