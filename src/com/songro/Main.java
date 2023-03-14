package com.songro;

import com.songro.commands.perks.ChatName;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import com.songro.ncpp.detect.*;
import com.songro.commands.op.*;
import com.songro.event.*;
import com.songro.listener.*;

import java.io.IOException;
import java.net.http.WebSocket;
import java.util.Objects;
import java.util.logging.Logger;
import java.io.File;

import com.songro.commands.*;
import org.jetbrains.annotations.NotNull;

public class Main extends JavaPlugin implements WebSocket.Listener, Listener {


    Logger log = getLogger();
    String playerName = "";


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
            Bukkit.getScheduler().runTaskTimerAsynchronously(this, new MoveMent(), 0L, 30 * 20L);
            createCustomConfig();
            new Fly();
            Bukkit.getConsoleSender().sendMessage("[PlayerPerms] config.yml 파일 체크중...");
            Bukkit.getConsoleSender().sendMessage("[PlayerPerms]" + ChatColor.GREEN + " 플러그인이 정상적으로 로드 되었습니다.");
        } catch (Exception e) {
            log.warning("플러그인을 로딩하던중에 오류가 발생했습니다.");
            log.warning("플러그인이 제대로 설치되었는지 확인해주십시오.");
            log.warning("오류 로그: " + e);
            log.warning("오류 코드: 0x01");
        }
    }

    @EventHandler
    public void onJoin(@NotNull PlayerJoinEvent e) {
        String player = String.valueOf(e.getPlayer());
        getConfig().set(player, playerName);
        e.getPlayer().setDisplayName(playerName);
    }

    private void createCustomConfig() {
        File config = new File(getDataFolder(), "config.yml");
        if(!config.exists()) {
            log.warning("config.yml 파일이 없어 파일을 만드는중...");
            config.getParentFile().mkdirs();
            saveResource("config.yml", false);
        } else {
            Bukkit.getConsoleSender().sendMessage("[PlayerPerms] " + ChatColor.GREEN + "config.yml 파일 체크됨.");
        }
        FileConfiguration customConfig = new YamlConfiguration();
        try {
            customConfig.load(config);
        } catch (IOException | InvalidConfigurationException e) {
            log.warning("플러그인을 로딩하던중에 오류가 발생했습니다.");
            log.warning("에러 로그: \n");
            e.printStackTrace();
            log.warning("에러 코드: 0x02");
        }
    }
    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("[PlayerPerms] Disabled.");
    }

}