package com.songro.commands.op;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static com.songro.Main.plugin;

public class Reload implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        Player player = (Player)sender;

        if(player.isOp()) {
            try {
                player.sendMessage(ChatColor.YELLOW + "Reloading PlayerPerms...");
                try {
                    plugin.reloadConfig();
                    player.sendMessage(ChatColor.GREEN + "Reloaded!");
                } catch (Exception e) {
                    player.sendMessage(ChatColor.RED + "[PlayerPerms] 설정을 불러오는 중에 오류가 발생했습니다.");
                    player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 로그: " + e);
                    player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 코드: 0x09");
                }
            } catch (Exception e) {
                player.sendMessage(ChatColor.RED + "[PlayerPerms] 설정을 불러오는 중에 오류가 발생했습니다.");
                player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 로그: " + e);
                player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 코드: 0x05");
            }
        } else {
            player.sendMessage(ChatColor.RED + "[PlayerPerms] 이 명령어는 OP만 사용 가능합니다.");
        }


        return true;
    }
}
