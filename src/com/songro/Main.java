package com.songro;

import com.songro.commands.Afk;
import com.songro.commands.HelloWorld;
import com.songro.commands.PlayerInfoNormal;
import com.songro.commands.QuietMessage;
import com.songro.commands.console.GivePlayerPermission;
import com.songro.commands.console.RemovePlayerPermission;
import com.songro.commands.op.*;
import com.songro.commands.perks.*;
import com.songro.commands.perks.plusplus.Sit;
import com.songro.event.KillHeadDrop;
import com.songro.event.MoveMent;
import com.songro.listener.AFKListener;
import com.songro.listener.MuteListener;
import com.songro.listener.PlayerChatColorGUIListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.io.IOException;
import java.net.http.WebSocket;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class Main extends JavaPlugin implements WebSocket.Listener, Listener {
    Logger log = getLogger();
    public static Main plugin;
    private FileConfiguration customConfig;

    @Override
    public void onEnable() {


        // floating arrow 30sec
        try {
            ItemStack tippedFloatingArrow = new ItemStack(Material.TIPPED_ARROW, 1);
            PotionMeta tfap = (PotionMeta) tippedFloatingArrow.getItemMeta();
            tfap.addCustomEffect(new PotionEffect(PotionEffectType.LEVITATION, 20 * 30, 0), true);
            tfap.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "공중 부양의 화살");
            tfap.setLore(List.of(ChatColor.AQUA + "화살을 맞으면 30초 동안 공중부양 효과를 얻습니다."));

            tippedFloatingArrow.setItemMeta(tfap);
            {
                NamespacedKey key = new NamespacedKey(this, "tfa");
                ShapedRecipe recipe = new ShapedRecipe(key, tippedFloatingArrow);
                recipe.shape(
                        " Z ",
                        " X ",
                        " Z ");
                recipe.setIngredient('X', Material.ARROW);
                recipe.setIngredient('Z', Material.SHULKER_SHELL);

                Bukkit.addRecipe(recipe);
            }

            // floating arrow 10sec
            ItemStack tfa10 = new ItemStack(Material.TIPPED_ARROW, 1);
            PotionMeta tfap10 = (PotionMeta) tippedFloatingArrow.getItemMeta();
            tfap10.addCustomEffect(new PotionEffect(PotionEffectType.LEVITATION, 20 * 10, 0), true);
            tfap10.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "공중 부양의 화살");
            tfap10.setLore(List.of(ChatColor.AQUA + "화살을 맞으면 10초 동안 공중부양 효과를 얻습니다."));

            tfa10.setItemMeta(tfap10);
            {
                NamespacedKey key = new NamespacedKey(this, "tfa10");
                ShapedRecipe recipe = new ShapedRecipe(key, tfa10);
                recipe.shape(
                        " Z ",
                        " X ",
                        " Z ");
                recipe.setIngredient('X', Material.ARROW);
                recipe.setIngredient('Z', new RecipeChoice.MaterialChoice.ExactChoice(tippedFloatingArrow));

                Bukkit.addRecipe(recipe);
            }
        } catch (NullPointerException e) {
            log.severe("레시피를 등록하는중에 오류가 발생했습니다.");
            log.severe("오류 로그: " + e);
        }

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
            Objects.requireNonNull(getCommand("remotecrafting")).setExecutor(new RemoteCrafting());
            Objects.requireNonNull(getCommand("remoteender")).setExecutor(new RemoteEnderChest());
            Objects.requireNonNull(getCommand("playerattachment")).setExecutor(new CheckPlayerAttachments());
            Objects.requireNonNull(getCommand("broadcast")).setExecutor(new Broadcast());
            Objects.requireNonNull(getCommand("blocklog")).setExecutor(new CheckBlockLog());
            Objects.requireNonNull(getCommand("sit")).setExecutor(new Sit());
            Objects.requireNonNull(getCommand("color")).setExecutor(new PlayerChatColor());
            Objects.requireNonNull(getCommand("helloworld")).setExecutor(new HelloWorld());
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
            try {
                getServer().getPluginManager().registerEvents(new PlayerChatColorGUIListener(), this);
            } catch (Exception e) {
                log.severe("PlayerChatColorGUIListener 이벤트를 등록중에 오류가 발생했습니다.");
                log.severe("오류 로그: " + e);
                log.severe("오류 코드: 0x09");
                plugin.setEnabled(false);
            }
            log.info("설정 확인중...");
            createCustomConfig();
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

    private void createCustomConfig() {
        File customConfigFile = new File(getDataFolder(), "config.yml");
        if (!customConfigFile.exists()) {
            log.warning("config.yml 파일이 존재하지 않아, 만드는중...");
            customConfigFile.getParentFile().mkdirs();
            saveResource("config.yml", false);
        } else {
            log.info("파일 확인됨.");
        }

        customConfig = new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            log.severe("설정을 불러오는중에 오류가 발생했습니다.");
            log.severe("오류 로그: " + e);
        }
    }

    public FileConfiguration getCustomConfig() {
        return this.customConfig;
    }
}