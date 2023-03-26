package com.songro.event;

import com.songro.handler.AFK;
import org.bukkit.Bukkit;

import static com.songro.Main.plugin;

public class MoveMent implements Runnable {

    @Override
    public void run() {

        boolean isOn = plugin.getCustomConfig().getBoolean("log.isPlayerAfkLog");

        if(isOn) {
            var log = Bukkit.getLogger();
            log.info("[PlayerPerms] 모든 플레이어 상태 확인중");
        }

        AFK.checkAllStatuses();

    }

}