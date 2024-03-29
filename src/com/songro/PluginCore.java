package com.songro;

import com.songro.commands.Afk;
import com.songro.commands.PlayerInfoNormal;
import com.songro.commands.QuietMessage;
import com.songro.commands.console.GivePlayerPermission;
import com.songro.commands.console.RemovePlayerPermission;
import com.songro.commands.op.*;
import com.songro.commands.perks.ChatName;
import com.songro.commands.perks.PlayerChatColor;
import com.songro.commands.perks.RemoteCrafting;
import com.songro.commands.perks.RemoteEnderChest;
import com.songro.commands.perks.plusplus.Sit;
import com.songro.enchant.CustomEnchant;
import com.songro.event.KillHeadDrop;
import com.songro.event.MoveMent;
import com.songro.event.enchant.Teleport;
import com.songro.item.CustomRecipeFileConfiguration;
import com.songro.listener.AFKListener;
import com.songro.listener.PlayerChatColorGUIListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

public class PluginCore extends JavaPlugin implements Listener {
    Logger log = getLogger();
    public static PluginCore plugin;

    // config
    private FileConfiguration customConfig;
    private FileConfiguration customRecipeFile;
    public String svname;

    // recipe
    private FileConfiguration recipeFile;

    @Override
    public void onEnable() {
        plugin = this;
        Bukkit.getConsoleSender().sendMessage("[PlayerPerms] Enabled.");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("P  L  A  Y  E  R");
        Bukkit.getConsoleSender().sendMessage("P  E  R  M  S   ");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Made by. songro_, License MIT");
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "이 플러그인은 Bungeecord에서 사용을 추천드리지 않습니다.");
        Bukkit.getConsoleSender().sendMessage("");
        //try {
            log.info("설정 확인중...");
            createCustomConfig();
            log.info("레시피 파일 확인중...");
            createCustomRecipe();
            boolean isRemovedRecipeExceptions = plugin.getCustomConfig().getBoolean("debug.removeRecipeExceptions");
            Objects.requireNonNull(getCommand("playerinfoop")).setExecutor(new PlayerInfo());
            Objects.requireNonNull(getCommand("targethealth")).setExecutor(new HealthBar());
            Objects.requireNonNull(getCommand("playerinfo")).setExecutor(new PlayerInfoNormal());
            Objects.requireNonNull(getCommand("quiet")).setExecutor(new QuietMessage());
            Objects.requireNonNull(getCommand("changename")).setExecutor(new ChatName());
            Objects.requireNonNull(getCommand("gpp")).setExecutor(new GivePlayerPermission());
            Objects.requireNonNull(getCommand("removepermission")).setExecutor(new RemovePlayerPermission());
            Objects.requireNonNull(getCommand("isafk")).setExecutor(new IsAFK());
            Objects.requireNonNull(getCommand("afk")).setExecutor(new Afk());
            Objects.requireNonNull(getCommand("reloadconfig")).setExecutor(new Reload());
            Objects.requireNonNull(getCommand("remotecrafting")).setExecutor(new RemoteCrafting());
            Objects.requireNonNull(getCommand("remoteender")).setExecutor(new RemoteEnderChest());
            Objects.requireNonNull(getCommand("playerattachment")).setExecutor(new CheckPlayerAttachments());
            Objects.requireNonNull(getCommand("broadcast")).setExecutor(new Broadcast());
            Objects.requireNonNull(getCommand("sit")).setExecutor(new Sit());
            Objects.requireNonNull(getCommand("color")).setExecutor(new PlayerChatColor());
            Objects.requireNonNull(getCommand("floatingmessage")).setExecutor(new FloatingTitle());
            Objects.requireNonNull(getCommand("admin")).setExecutor(new AdminMenu());
            Objects.requireNonNull(getCommand("ench")).setExecutor(new EnchantItem());
            new CustomRecipeFileConfiguration().createItem();
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
            try {
                getServer().getPluginManager().registerEvents(new PlayerChatColorGUIListener(), this);
            } catch (Exception e) {
                log.severe("PlayerChatColorGUIListener 이벤트를 등록중에 오류가 발생했습니다.");
                log.severe("오류 로그: " + e);
                log.severe("오류 코드: 0x09");
                plugin.setEnabled(false);
            }
            boolean isOnDebug = plugin.getCustomConfig().getBoolean("debug.showDebugLog");
            boolean isOnExperimentalGui = plugin.getCustomConfig().getBoolean("experimental.bangui");
            boolean isOnExperimentalBar = plugin.getCustomConfig().getBoolean("experimental.healthbar");
            if(isOnDebug) {
                log.info("Available processors (cores): " + Runtime.getRuntime().availableProcessors());
                log.info("Free memory (bytes): " + Runtime.getRuntime().freeMemory());
                long maxMemory = Runtime.getRuntime().maxMemory();
                log.info("Maximum memory (bytes): " + (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory));
                log.info("Total memory available to JVM (bytes): " + Runtime.getRuntime().totalMemory());
                log.warning("현재 디버그 기능을 사용중입니다 이 기능은 오직 디버그 용도로만 사용되며, 다른 용도로 사용되지 않습니다.");
            }
            if(isOnExperimentalGui) {
                log.warning("현재 실험적 기능인 BanGui를 사용하고 있습니다, 이 기능은 불안정하고, 버그가 자주 일어날수 있습니다.");
            } else if(isOnExperimentalBar) {
                log.warning("현재 실험적 기능인 TargetHealthbar를 사용하고 있습니다, 불안정하고, 버그가 자주 일어날수 있습니다.");
            }
            try {
                svname = plugin.getCustomConfig().getString("svname");
            } catch (Exception e) {
                log.severe("설정에서 이름을 지정하는중에 오류가 발생하였습니다.");
                log.severe("오류 로그: " + e);
                log.severe("오류 코드: 0x09");
                svname = "[PlayerPerms]";
                log.warning("오류가 발생해 이름이 기본설정으로 지정되었습니다.");
            }
            try {
                getServer().getPluginManager().registerEvents(new Teleport(), this);
            } catch (Exception e) {
                log.severe("Teleport 이벤트를 등록중에 오류가 발생했습니다.");
                log.severe("오류 로그: " + e);
                log.severe("오류 코드: 0x09");
            }
            CustomEnchant.register();
            Bukkit.getConsoleSender().sendMessage("[PlayerPerms]" + ChatColor.GREEN + " 플러그인이 정상적으로 로드 되었습니다.");
        //} catch (Exception e) {
        //    log.severe("플러그인을 로딩하던중에 오류가 발생했습니다.");
        //    log.severe("플러그인이 제대로 설치되었는지 확인해주십시오.");
        //    log.severe("오류 로그: " + e);
        //    log.severe("오류 코드: 0x01");
        //    plugin.setEnabled(false);
        //}
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("[PlayerPerms] Disabled.");
    }

    public static PluginCore getPlugin(){
        return plugin;
    }

    public void createCustomConfig() {
        File customConfigFile = new File(getDataFolder(), "config.yml");
        if (!customConfigFile.exists()) {
            log.warning("config.yml 파일이 존재하지 않아, 만드는중...");
            customConfigFile.getParentFile().mkdirs();
            saveResource("config.yml", false);
            log.info("파일 만들어짐.");
        } else {
            log.info("파일 확인됨.");
        }

        customConfig = new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            log.severe("설정을 불러오는중에 오류가 발생했습니다.");
            log.severe("오류 로그: " + e);
            plugin.setEnabled(false);
        }
    }

    public void createCustomRecipe() {
        File recipefile = new File(getDataFolder(), "recipe.yml");
        if (!recipefile.exists()) {
            log.warning("recipe.yml 파일이 존재하지 않아, 만드는중...");
            recipefile.getParentFile().mkdirs();
            saveResource("recipe.yml", false);
            log.info("파일 만들어짐.");
        } else {
            log.info("파일 확인됨.");
        }

        customRecipeFile = new YamlConfiguration();
        try {
            customRecipeFile.load(recipefile);
        } catch (IOException | InvalidConfigurationException e) {
            log.severe("레시피를 불러오는중에 오류가 발생했습니다.");
            log.severe("오류 로그: " + e);
            plugin.setEnabled(false);
        }
    }

    public FileConfiguration getCustomConfig() {
        return this.customConfig;
    }

    public FileConfiguration getCustomRecipe() {
        return this.customRecipeFile;
    }
}