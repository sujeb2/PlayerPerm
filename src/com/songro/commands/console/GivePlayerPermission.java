package com.songro.commands.console;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public class GivePlayerPermission implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Logger log = Bukkit.getLogger();
        Player player = (Player)sender;
        var target = Bukkit.getPlayer(args[1]);

        try {
            if(player.hasPermission("def.op") || player.getDisplayName().equals("CONSOLE")){
                if(args[2].equalsIgnoreCase("iron")) {
                    assert target != null;
                    if(target.isPermissionSet("perks.iron")) {
                        player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 이미 " + target.getDisplayName() + "님은  " + ChatColor.WHITE + "'perks.iron'" + ChatColor.YELLOW + " 권한을 가지고 있습니다.");
                    } else {
                        // to-do
                    }
                }
                if(args[2].equalsIgnoreCase("gold")) {
                    assert target != null;
                    if(target.isPermissionSet("perks.gold")) {
                        player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 이미 " + target.getDisplayName() + "님은 " + ChatColor.GOLD + "'perks.gold'" + ChatColor.YELLOW + " 권한을 가지고 있습니다.");
                    } else {
                        // to-do
                    }
                }
                if(args[2].equalsIgnoreCase("diamond")) {
                    assert target != null;
                    if(target.isPermissionSet("perks.diamond")) {
                        player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 이미 " + target.getDisplayName() + "님은 " + ChatColor.AQUA + "'perks.diamond'" + ChatColor.YELLOW +"권한을 가지고 있습니다.");
                    } else {

                    }
                }
                if(args[2].equalsIgnoreCase("emerald")) {
                    assert target != null;
                    if(target.isPermissionSet("perks.emerald")) {
                        player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 이미 " + target.getDisplayName() + "님은 " + ChatColor.GREEN + "'perks.emerald'" + ChatColor.YELLOW +"권한을 가지고 있습니다.");
                    } else {

                    }
                }
                if(args[2].length() == 0) {
                    player.sendMessage(ChatColor.RED + "[PlayerPerms] 추가 명령어가 없습니다");
                    player.sendMessage(ChatColor.RED + "[PlayerPerms] 사용방법: /<command> <Player> (iron/gold/diamond/emerald");
                }
                if(args[1].length() == 0) {
                    player.sendMessage(ChatColor.RED + "[PlayerPerms] 추가 명령어가 없습니다");
                    player.sendMessage(ChatColor.RED + "[PlayerPerms] 사용방법: /<command> <Player> (iron/gold/diamond/emerald");
                }
            }
            if(args.length == 0) {
                player.sendMessage(ChatColor.RED + "[PlayerPerms] 추가 명령어가 없습니다");
                player.sendMessage(ChatColor.RED + "[PlayerPerms] 사용방법: /<command> <Player> (iron/gold/diamond/emerald");
            }
        } catch (Exception e) {
            log.severe("오류가 발생했습니다");
            log.severe("오류 로그: " + e);
            log.severe("오류 코드: 0xnull");
            player.sendMessage(ChatColor.RED + "[PlayerPerms] 오류가 발생했습니다");
            player.sendMessage(ChatColor.YELLOW + "오류 로그: " + e + "\n[PlayerPerms] 오류 코드: 0xnull");
            return false;
        }

        return true;
    }
}
