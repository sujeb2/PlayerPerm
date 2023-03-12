package com.songro.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class QuietMessage implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = (Player)sender;
        Player target = Bukkit.getPlayer(args[0]);
        String msg = args[1];

        try {
            if(target == null) {
                try {
                    player.sendMessage(ChatColor.RED + "[PlayerPerms] " + target.getDisplayName() + "이라는 플레이어가 존재하지 않거나, 서버에 들어온적이 없습니다.");
                } catch (NullPointerException npe) {
                    player.sendMessage(ChatColor.RED + "[PlayerPerms] 오류가 발생했습니다");
                    player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 로그: " + npe + "\n[PlayerPerms] 오류코드: 0x16");
                    return false;
                }
            }
            if (args.length == 0) {
                player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 플레이어 이름이나, 메세지가 없습니다.");
                player.sendMessage(ChatColor.GREEN + "[PlayerPerms] 명령어 사용 방법: /<commands> <Player> <Message>");
            }
            if (!Bukkit.getOnlinePlayers().contains(target))
                player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] '" + target.getDisplayName() +"' 플레이어는 온라인이 아닙니다.");
            try {
                if(target.equals(player)) {
                    player.sendMessage( ChatColor.YELLOW + "[PlayerPerms] 자기 자신에게 귓속말을 보낼수 없습니다.");
                } else {
                target.sendMessage(ChatColor.GRAY + "[PlayerPerms] 새로운 메세지를 받았습니다.\n• " + player.getDisplayName() + "(이)가 보낸 메세지: " + msg);
                player.sendMessage(ChatColor.GREEN + "[PlayerPerms] " + target.getDisplayName() + "에게 성공적으로 " + msg + "라고 메세지를 보냈습니다.");
                return true;
                }


            } catch (IndexOutOfBoundsException e2) {
                player.sendMessage(ChatColor.RED + "[PlayerPerms] 오류가 발생했습니다");
                player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 로그: " + e2 + "\n[PlayerPerms] 오류코드: 0x19");
                return false;
            }
        } catch (Exception e) {
            player.sendMessage(ChatColor.RED + "[PlayerPerms] 오류를 보내는중에 오류가 발생했습니다");
            player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 로그: " + e + "\n[PlayerPerms] 오류코드: 0x18");
            return false;
        }

        return false;
    }
}