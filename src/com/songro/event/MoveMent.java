package com.songro.event;

import com.songro.handler.AFK;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;

public class MoveMent implements Runnable {

    @Override
    public void run() {

        var log = Bukkit.getLogger();
        log.info("[PlayerPerms] 모든 플레이어 상태 확인중");

        AFK.checkAllStatuses();

    }

}