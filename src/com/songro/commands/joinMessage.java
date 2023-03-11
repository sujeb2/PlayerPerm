package com.songro.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

public class joinMessage implements CommandExecutor {
    boolean isOn = true;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, @NotNull String s, @NotNull String[] strings) {
        // 씨 이거 왜 안됨
        Player player = (Player) player;
        try {
            if(strings[0].equalsIgnoreCase("on")) {
                isOn = true;
                player.sendMessage(ChatColor.GREEN + "[PlayerPerms] 접속 메세지가 변경되게 하였습니다!");
            } else if(strings[0].equalsIgnoreCase("off")) {
                isOn = false;
                player.sendMessage(ChatColor.RED + "[PlayerPerms] 접속 메세지가 변경되지 않게 하였습니다.");
            }
        } catch (Exception e) {
            player.sendMessage(ChatColor.RED + "[PlayerPerms] 오류가 발생했습니다!");
            player.sendMessage(ChatColor.GRAY + "[PlayerPerms] 오류 로그: " + e + "\n[PlayerPerms] 오류 코드: 0x12");
        }

        return false;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if(isOn = true) {
            event.setJoinMessage(ChatColor.AQUA + "");
        }
    }
}
