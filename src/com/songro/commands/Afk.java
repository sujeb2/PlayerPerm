package com.songro.commands;

import com.songro.handler.AFK;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Afk implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;
        try {
            if (sender != null) {
                try {

                    if (AFK.toggleAFK(player)) {
                        player.sendMessage(ChatColor.BLUE + "[PlayerPerms] 잠수모드에 들어갑니다");
                        AFK.announceAFK(player, true);
                    } else {
                        player.sendMessage(ChatColor.GREEN + "[PlayerPerms] 잠수가 풀렸습니다");
                        AFK.announceAFK(player, false);
                    }
                } catch (Exception e) {
                    player.sendMessage(ChatColor.RED + "[PlayerPerms] 오류가 발생했습니다");
                    player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 로그: " + e + "\n[PlayerPerms] 오류 코드: 0x41");
                }

            }
        } catch (Exception e) {
            player.sendMessage(ChatColor.RED + "[PlayerPerms] 오류가 발생했습니다");
            player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 로그: " + e + "\n[PlayerPerms] 오류 코드: 0x40");
        }

        return true;
    }
}