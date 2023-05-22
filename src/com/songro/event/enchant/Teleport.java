package com.songro.event.enchant;

import com.songro.enchant.CustomEnchant;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
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

            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 10, 1);
            player.teleport(eyeLocation);

        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {

    }

}
