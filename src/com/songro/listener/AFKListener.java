package com.songro.listener;

import com.songro.handler.AFK;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class AFKListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        AFK.playerJoined(e.getPlayer());
        Player p = e.getPlayer();
        if(p.hasPermission("perks.plus")) {
            p.setDisplayName(ChatColor.GREEN + "[FRUIT" + ChatColor.YELLOW + "+" + ChatColor.GREEN + "] " + p.getName() + ChatColor.WHITE);
            p.setCustomName(ChatColor.GREEN + "[FRUIT" + ChatColor.YELLOW + "+" + ChatColor.GREEN + "] " + p.getName() + ChatColor.WHITE);
            p.setCustomNameVisible(true);
            e.setJoinMessage(p.getDisplayName() + "님이 서버에 들어왔습니다!");
        } else if (p.hasPermission("perks.pp")) {
            p.setDisplayName(ChatColor.GREEN + "[FRUIT" + ChatColor.AQUA + "++" + ChatColor.GREEN + "] " + p.getName() + ChatColor.WHITE);
            p.setCustomName(ChatColor.GREEN + "[FRUIT" + ChatColor.AQUA + "++" + ChatColor.GREEN + "] " + p.getName() + ChatColor.WHITE);
            p.setCustomNameVisible(true);
            e.setJoinMessage(p.getDisplayName() + "님이 서버에 들어왔습니다!");
        } else if (p.hasPermission("perks.ultra")) {
            p.setDisplayName(ChatColor.GREEN + "[FRUIT" + ChatColor.DARK_GRAY + " U" + ChatColor.GREEN + "] " + p.getName() + ChatColor.WHITE);
            p.setCustomName(ChatColor.GREEN + "[FRUIT" + ChatColor.DARK_GRAY + " U" + ChatColor.GREEN + "] " + p.getName() + ChatColor.WHITE);
            p.setCustomNameVisible(true);
            e.setJoinMessage(p.getDisplayName() + "님이 서버에 들어왔습니다!");
        } else if(p.hasPermission("def.normal")) {
            e.setJoinMessage(p.getDisplayName() + "님이 서버에 들어왔습니다!");
        }

        if(p.isOp()) {
            p.setDisplayName(ChatColor.DARK_RED + "[STAFF] " + p.getName() + ChatColor.WHITE);
            p.setCustomName(ChatColor.DARK_RED + "[STAFF] " + p.getName() + ChatColor.WHITE);
            p.setCustomNameVisible(true);
            e.setJoinMessage(p.getDisplayName() + "님이 서버에 들어왔습니다!");
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        AFK.playerMoved(e.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        AFK.playerLeft(e.getPlayer());
    }

}