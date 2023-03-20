package com.songro;

import com.songro.commands.console.GivePlayerPermission;
import com.songro.commands.console.RemovePlayerPermission;
import com.songro.commands.perks.*;
import com.songro.commands.perks.plusplus.Sit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import com.songro.commands.op.*;
import com.songro.event.*;
import com.songro.listener.*;

import java.net.http.WebSocket;
import java.util.Objects;
import java.util.logging.Logger;

import com.songro.commands.*;

public class Main extends JavaPlugin implements WebSocket.Listener, Listener {
    Logger log = getLogger();
    public static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;
        Bukkit.getConsoleSender().sendMessage("[PlayerPerms] Enabled.");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("P  L  A  Y  E  R");
        Bukkit.getConsoleSender().sendMessage("P  E  R  M  S    " + ChatColor.DARK_GRAY + "-- FOR FRUIT NET EDITION");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Made by. songro_, License MIT");
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "이 버전은 FRUIT NET 서버를 위한 전용 버전입니다. 배포금지");
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "이 플러그인은 Bungeecord에서 사용을 추천드리지 않습니다.");
        Bukkit.getConsoleSender().sendMessage("");
        try {
            //Objects.requireNonNull(getCommand("friend")).setExecutor(new Friend());
            Objects.requireNonNull(getCommand("playerinfoop")).setExecutor(new PlayerInfo());
            Objects.requireNonNull(getCommand("targethealth")).setExecutor(new HealthBar());
            Objects.requireNonNull(getCommand("playerinfo")).setExecutor(new PlayerInfoNormal());
            Objects.requireNonNull(getCommand("ban")).setExecutor(new Ban());
            Objects.requireNonNull(getCommand("quiet")).setExecutor(new QuietMessage());
            Objects.requireNonNull(getCommand("changename")).setExecutor(new ChatName());
            Objects.requireNonNull(getCommand("gpp")).setExecutor(new GivePlayerPermission());
            Objects.requireNonNull(getCommand("removepermission")).setExecutor(new RemovePlayerPermission());
            Objects.requireNonNull(getCommand("isafk")).setExecutor(new IsAFK());
            Objects.requireNonNull(getCommand("afk")).setExecutor(new Afk());
            Objects.requireNonNull(getCommand("reloadconfig")).setExecutor(new Reload());
            //Objects.requireNonNull(getCommand("remoteanvil")).setExecutor(new RemoteAnvil());
            Objects.requireNonNull(getCommand("remotecrafting")).setExecutor(new RemoteCrafting());
            Objects.requireNonNull(getCommand("remoteender")).setExecutor(new RemoteEnderChest());
            Objects.requireNonNull(getCommand("playerattachment")).setExecutor(new CheckPlayerAttachments());
            Objects.requireNonNull(getCommand("broadcast")).setExecutor(new Broadcast());
            Objects.requireNonNull(getCommand("blocklog")).setExecutor(new CheckBlockLog());
            Objects.requireNonNull(getCommand("sit")).setExecutor(new Sit());
            new Mute();
            try {
                getServer().getPluginManager().registerEvents(new MuteListener(), this);
            } catch (Exception e) {
                log.severe("MuteListener 이벤트를 등록중에 오류가 발생했습니다.");
                log.severe("오류 로그: " + e);
                log.severe("오류 코드: 0x07");
                plugin.setEnabled(false);
            }
            try {
                Bukkit.getScheduler().runTaskTimerAsynchronously(this, new MoveMent(), 0L, 30 * 20L);
                log.info("MoveMent TaskTimer added.");
                log.info("서버성능 이슈가 있다면, 제발 말해주세요.");
            } catch (Exception e) {
                log.severe("플레이어 움직임을 확인중에 오류가 발생했습니다.");
                log.severe("오류 로그: " + e);
                log.severe("오류 코드: 0x05");
                plugin.setEnabled(false);
            }
            try {
                getServer().getPluginManager().registerEvents(new AFKListener(), this);
            } catch (Exception e) {
                log.severe("AFKListener 이벤트를 등록중에 오류가 발생했습니다.");
                log.severe("오류 로그: " + e);
                log.severe("오류 코드: 0x06");
                plugin.setEnabled(false);
            }
            try {
                getServer().getPluginManager().registerEvents(new KillHeadDrop(), this);
            } catch (Exception e) {
                log.severe("KillHeadDrop 이벤트를 등록중에 오류가 발생했습니다.");
                log.severe("오류 로그: " + e);
                log.severe("오류 코드: 0x09");
                plugin.setEnabled(false);
            }
            Bukkit.getConsoleSender().sendMessage("[PlayerPerms]" + ChatColor.GREEN + " 플러그인이 정상적으로 로드 되었습니다.");
        } catch (Exception e) {
            log.severe("플러그인을 로딩하던중에 오류가 발생했습니다.");
            log.severe("플러그인이 제대로 설치되었는지 확인해주십시오.");
            log.severe("오류 로그: " + e);
            log.severe("오류 코드: 0x01");
            plugin.setEnabled(false);
        }
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("[PlayerPerms] Disabled.");
    }

    public static Main getPlugin(){
        return plugin;
    }
}