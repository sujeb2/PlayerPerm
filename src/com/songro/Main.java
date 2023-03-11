package com.songro;

import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import java.net.http.WebSocket;
import java.util.logging.Logger;

import com.songro.commands.*;

public class Main extends JavaPlugin implements WebSocket.Listener {

    Logger log = getLogger();
    Plugin playerperms = Bukkit.getPluginManager().getPlugin("PlayerPerms");

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("[PlayerPerms] Enabled.");
        try {
            getCommand("friend").setExecutor(new FriendHandler());
            getCommand("").setExecutor(new joinMessage());
        } catch (Exception e) {
            log.warning("플러그인을 로딩하던중에 오류가 발생했습니다.");
            log.warning("'LuckPerms' 플러그인이 제대로 설치되었는지 확인해주십시오.");
        }
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("[PlayerPerms] Disabled.");
    }

    public void registerEvents(){

        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents((Listener) new joinMessage(),  this);
    }

}
