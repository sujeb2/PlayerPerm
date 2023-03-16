package com.songro.event;

import com.songro.data.config;
import com.songro.handler.AFK;
import org.bukkit.Bukkit;

public class MoveMent implements Runnable {

    @Override
    public void run() {

        //if(config.get().getBoolean("isAfkLogMessage")) {
            var log = Bukkit.getLogger();
            //log.info("[PlayerPerms] 모든 플레이어 상태 확인중");
        //}

        AFK.checkAllStatuses();

    }

}