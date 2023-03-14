package com.songro;

import com.songro.commands.perks.ChatName;
import com.songro.data.config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import com.songro.ncpp.detect.*;
import com.songro.commands.op.*;
import com.songro.event.*;
import com.songro.listener.*;

import java.net.http.WebSocket;
import java.util.Objects;
import java.util.logging.Logger;

import com.songro.commands.*;

public class Main extends JavaPlugin implements WebSocket.Listener, Listener {
    Logger log = getLogger();

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("[PlayerPerms] Enabled.");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("P  L  A  Y  E  R");
        Bukkit.getConsoleSender().sendMessage("P  E  R  M  S    " + ChatColor.DARK_GRAY + "-- FOR FRUIT NET EDITION");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Made by. songro_, License MIT");
        Bukkit.getConsoleSender().sendMessage("");
        try {
            Objects.requireNonNull(getCommand("friend")).setExecutor(new Friend());
            Objects.requireNonNull(getCommand("playerinfoop")).setExecutor(new PlayerInfo());
            Objects.requireNonNull(getCommand("targethealth")).setExecutor(new HealthBar());
            Objects.requireNonNull(getCommand("playerinfo")).setExecutor(new PlayerInfoNormal());
            Objects.requireNonNull(getCommand("ban")).setExecutor(new Ban());
            Objects.requireNonNull(getCommand("quiet")).setExecutor(new QuietMessage());
            Objects.requireNonNull(getCommand("changename")).setExecutor(new ChatName());
            getServer().getPluginManager().registerEvents(new AFKListener(), this);
            Objects.requireNonNull(getCommand("isafk")).setExecutor(new IsAFK());
            Objects.requireNonNull(getCommand("afk")).setExecutor(new Afk());
            Objects.requireNonNull(getCommand("reloadconfig")).setExecutor(new Reload());
            //if (config.get().getBoolean("isAfkLogMessage")) {
                Bukkit.getScheduler().runTaskTimerAsynchronously(this, new MoveMent(), 0L, 30 * 20L);
            //}
            new Fly();
            //Bukkit.getConsoleSender().sendMessage("[PlayerPerms] config.yml 파일 체크중...");
            //try {
            //    getConfig().options().copyDefaults();
            //    saveDefaultConfig();

            //    config.setup();
            //    config.get().addDefault("isAfkCheckingLog", "true");
            //    config.get().options().copyDefaults(true);
            //    config.save();
            //} catch (Exception e) {
            //    log.severe("오류가 발생했습니다.");
            //    log.severe("오류 로그: " + e);
            //    log.severe("오류 코드: 0x05");
            //}
            Bukkit.getConsoleSender().sendMessage("[PlayerPerms]" + ChatColor.GREEN + " 플러그인이 정상적으로 로드 되었습니다.");
        } catch (Exception e) {
            log.severe("플러그인을 로딩하던중에 오류가 발생했습니다.");
            log.severe("플러그인이 제대로 설치되었는지 확인해주십시오.");
            log.severe("오류 로그: " + e);
            log.severe("오류 코드: 0x01");
        }
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("[PlayerPerms] Disabled.");
    }

}