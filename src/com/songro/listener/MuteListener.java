package com.songro.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MuteListener implements Listener {

    public boolean isMuted = false;

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (isMuted) {
            Player p = e.getPlayer();
            if (p.hasPermission("def.mutebypass")) {
                p.sendMessage("1");
            } else {
                p.sendMessage("[PlayerPerms] 현재 채팅이 금지되었습니다.");
                e.setCancelled(true);
            }
        }
    }

}
