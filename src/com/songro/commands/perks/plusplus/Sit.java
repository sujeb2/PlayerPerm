package com.songro.commands.perks.plusplus;

import com.songro.Main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Sit implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = (Player)sender;
        Location loc = player.getLocation();

        try {

        } catch(Exception e) {
            player.sendMessage(ChatColor.RED + Main.plugin.svname + " 오류가 발생했습니다");
            player.sendMessage(ChatColor.YELLOW + Main.plugin.svname + " 오류 로그: " + e + "\n[PlayerPerms] 오류 코드: 0x120");
        }

        return true;
    }
}
