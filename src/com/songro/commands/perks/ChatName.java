package com.songro.commands.perks;

import com.songro.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ChatName implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = (Player)sender;
        StringBuilder builder = new StringBuilder();
        String msg = args[0];
        String changedName = player.getCustomName();

        try {
            try {
                if(msg.equals(player.getDisplayName())) {
                    player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 변경된게 없습니다.");
                    return true;
                }
                if (s.length() == 0) {
                    player.sendMessage(ChatColor.RED + "[PlayerPerms] 추가 명령어가 없습니다. 공백인경우 다른걸로 해주세요.");
                    player.sendMessage(ChatColor.GREEN + "[PlayerPerms] 명령어 사용방법: /<command> <바꿀 이름>");
                    return true;
                } else if (args.length == 1) {
                    try {
                        player.setCustomName(msg);
                        player.setCustomNameVisible(true);
                        player.setDisplayName(msg);
                        player.sendMessage(ChatColor.GREEN + "[PlayerPerms] 성공적으로 이름을 " + changedName + "으로 바꾸었습니다.");
                        return true;
                    } catch (Exception e) {
                        player.sendMessage(ChatColor.RED + "[PlayerPerms] 오류가 발생했습니다");
                        player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 로그: " + e + "\n[PlayerPerms] 오류코드: 0x33");
                        return false;
                    }
                } else {
                    try {
                        for (int i = 1; i < args.length; i++) {
                            builder.append(args[i]);
                            builder.append(" ");
                        }
                        String finalMessage = builder.toString();
                        finalMessage = finalMessage.stripTrailing();
                        player.setCustomName(finalMessage);
                        player.setCustomNameVisible(true);
                        player.sendMessage(ChatColor.GREEN + "[PlayerPerms] 성공적으로 이름을 " + changedName + "으로 바꾸었습니다.");
                        return true;
                    } catch (Exception e) {
                        player.sendMessage(ChatColor.RED + "[PlayerPerms] 오류가 발생했습니다");
                        player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 로그: " + e + "\n[PlayerPerms] 오류코드: 0x31");
                        return false;
                    }
                }
            } catch (Exception e) {
                player.sendMessage(ChatColor.RED + "[PlayerPerms] 오류가 발생했습니다");
                player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 로그: " + e + "\n[PlayerPerms] 오류코드: 0x32");
                return false;
            }
        } catch (Exception e) {
            player.sendMessage(ChatColor.RED + "[PlayerPerms] 오류를 보내는중에 오류가 발생했습니다");
            player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 로그: " + e + "\n[PlayerPerms] 오류코드: 0x30");
            return false;
        }
    }
}
