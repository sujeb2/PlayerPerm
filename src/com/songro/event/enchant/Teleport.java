package com.songro.event.enchant;

import com.songro.enchant.CustomEnchant;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Teleport implements Listener {

    @EventHandler
    public void onLeftClick(PlayerInteractEvent event) {
        Player player = (Player)event.getPlayer();
        Location eyeLocation = player.getEyeLocation();

        if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (player.getInventory().getItemInMainHand() == null) {
                return;
            }
            if (!player.getInventory().getItemInMainHand().hasItemMeta()) {
                return;
            }
            if (!player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchant.TELEPORT)) {
                return;
            }

            player.teleport(eyeLocation);

        }
    }

}
