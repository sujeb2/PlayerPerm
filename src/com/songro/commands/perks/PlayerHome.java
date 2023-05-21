package com.songro.commands.perks;

import com.songro.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlayerHome implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player)commandSender;
        try {

        } catch (Exception ex) {
            player.sendMessage(ChatColor.RED + Main.plugin.svname + " 플레이어의 집을 불러오는중에 오류가 발생하였습니다.");
            return false;
        }

        return true;
    }
}
