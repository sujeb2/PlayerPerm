package com.songro.commands.op;

import com.songro.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ReloadConfiguration implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player)commandSender;
        try {
            if(player.isOp()) {
                Main.plugin.getCustomConfig();
                player.sendMessage(ChatColor.GREEN + "Reloaded!");
            } else {
                player.sendMessage(ChatColor.YELLOW + Main.getPlugin().svname + " OP만 이 명령어를 사용할수 있습니다!");
            }
        } catch (Exception e) {
            player.sendMessage(ChatColor.RED + Main.getPlugin().svname + " 설정을 다시 불러오는중에 오류가 발생하였습니다.");
            player.sendMessage(ChatColor.RED + Main.getPlugin().svname + " 에러 로그: " + e);
        }

        return true;
    }
}
