package com.songro.commands.perks.plusplus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public class ChangeHomeColor implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Logger log = Bukkit.getLogger();
        Player player = (Player)commandSender;

        if(commandSender instanceof Player) {
            if(player.hasPermission("perks.pp")) {
                player.sendMessage("todo");
            } else{
                player.sendMessage(ChatColor.RED + "[PlayerPerms] 권한이 없습니다, 이 명령어를 사용할려면, 'perks.plusplus' 보다 높거나 같은 권한이 필요합니다!");
            }
        } else {
            log.warning("이 명령어는 플레이어만 사용할수 있습니다.");
        }

        return true;
    }
}
