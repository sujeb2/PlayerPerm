package com.songro.data;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class config {

    private static File file;
    private static FileConfiguration customFile;
    static Logger log = Bukkit.getLogger();

    public static void setup(){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("CustomConfigPlugin").getDataFolder(), "config.yml");

        if (!file.exists()){
            try{
                log.warning("설정 파일이 없어, 파일을 만드는중..");
                file.createNewFile();
            } catch (IOException e){
                log.severe("오류가 발생했습니다.");
                log.severe("에러 로그: " + e);
                log.severe("에러 코드: 0x04");
            }
        } else {
            log.info(ChatColor.GREEN + "파일 확인됨");
        }
        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get(){
        return customFile;
    }

    public static void save(){
        try{
            customFile.save(file);
        } catch (IOException e){
            log.severe("설정 파일을 만들수가 없습니다, 권한이 있는지 확인해주세요.");
            log.severe("에러 로그: " + e);
            log.severe("에러 코드: 0x03");
        }
    }

    public static void reload(){
        customFile = YamlConfiguration.loadConfiguration(file);
    }

}
