package com.songro.listener;

import com.songro.handler.AFK;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class AFKListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        AFK.playerJoined(e.getPlayer());
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