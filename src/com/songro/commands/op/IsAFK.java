package com.songro.commands.op;

import com.songro.handler.AFK;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class IsAFK implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        Player player = (Player)sender;
        var log = Bukkit.getLogger();

        if (player.isOp()) {
            if (sender instanceof Player) {

                if (args.length == 0) {
                    if (AFK.isAFK(player)) {
                        player.sendMessage(ChatColor.BLUE + "[PlayerPerms] 현재 잠수중입니다.");
                    } else {
                        player.sendMessage(ChatColor.DARK_GRAY + "[PlayerPerms] 잠수중이 아닙니다.");
                    }
                } else {
                    if (AFK.isAFK(Bukkit.getPlayerExact(args[0]))) {
                        player.sendMessage(ChatColor.BLUE + "[PlayerPerms] 현재 " + args[0] + "님은 잠수중입니다, " + ChatColor.ITALIC + ChatColor.DARK_GRAY + "(건들이지 않는게 좋을수도..?)");
                    } else {
                        player.sendMessage(ChatColor.DARK_GRAY + "[PlayerPerms] 현재 " + args[0] + "님은 잠수중이 아닙니다.");
                    }
                }

            } else {
                log.warning("이 명령어는 플레이어만 사용할수 있습니다.");
            }
        } else {
            player.sendMessage(ChatColor.RED + "[PlayerPerms] 이 명령어는 OP만 사용가능합니다.");
        }

        return true;
    }
}