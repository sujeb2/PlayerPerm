package com.songro.handler;

import com.songro.PluginCore;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class AFK {

    private static final long TIME_TO_AFK = 60000L;

    private static final HashMap<Player, Long> lastMovement = new HashMap<>();

    private static final HashMap<Player, Boolean> previousData = new HashMap<>();

    public static void playerMoved(Player player) {
        lastMovement.put(player, System.currentTimeMillis());

        checkPlayerAFKStatus(player);
    }

    public static void playerJoined(Player player) {
        lastMovement.put(player, System.currentTimeMillis());
    }

    public static void playerLeft(Player player) {
        lastMovement.remove(player);
    }

    public static boolean isAFK(Player player) {

        if(lastMovement.get(player) != null){
            long timeElapsed = System.currentTimeMillis() - lastMovement.get(player);

            return timeElapsed >= TIME_TO_AFK || lastMovement.get(player) == -1L;
        }

        lastMovement.put(player, System.currentTimeMillis());

        return false;
    }

    public static boolean toggleAFK(Player player) {

        if(isAFK(player)) {
            previousData.put(player, false);
            lastMovement.put(player, System.currentTimeMillis());
            return false;
        }

        previousData.put(player, true);
        lastMovement.put(player, -1L);
        return true;
    }

    public static void checkAllStatuses(){

        for(Map.Entry<Player, Long> entry : lastMovement.entrySet()){
            checkPlayerAFKStatus(entry.getKey());
        }

    }
    public static void checkPlayerAFKStatus(Player player){

        if(lastMovement.containsKey(player)){
            long whenLastMoved = lastMovement.get(player);
            long timeElapsed = System.currentTimeMillis() - whenLastMoved;
            boolean isAFK = whenLastMoved == -1L || timeElapsed >= AFK.TIME_TO_AFK;

            if (previousData.containsKey(player)) {

                boolean wasAFK = previousData.get(player);

                if (wasAFK && !isAFK) {
                    previousData.put(player, false);
                    AFK.announceAFK(player, false);
                    player.sendMessage(ChatColor.GREEN + "[PlayerPerms] 잠수가 풀렸습니다");
                } else if (!wasAFK && isAFK) {
                    previousData.put(player, true);
                    AFK.announceAFK(player, true);
                    player.sendMessage(ChatColor.BLUE + PluginCore.plugin.svname +" 잠수모드에 들어갑니다");
                }

            }else{
                previousData.put(player, isAFK);
            }
        }

    }

    public static void announceAFK(Player player, boolean isAFK){

        if(isAFK){

        }else{

        }

    }

}