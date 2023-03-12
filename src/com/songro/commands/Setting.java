package com.songro.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Setting implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(commandSender instanceof Player player)) {
            commandSender.sendMessage("[PlayerPerms] 플레이어만 이 명령어를 사용할수 있습니다.");
            return true;
        }
        player.sendMessage("hi");

        return true;
    }
}
