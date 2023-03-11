package com.songro.commands;

import com.songro.Main;
import com.songro.data.FD;
import com.songro.handler.Timer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class Friend implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        try {
            Player player = (Player) sender;
            if (strings.length == 0) {
                player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 플레이어 이름이 없습니다.");
                player.sendMessage(ChatColor.GREEN + "[PlayerPerms] 명령어 사용 방법: /<commands> 추가 (Player)");
            }
            switch (strings[0]) {
                case "추가":
                    Player f = Bukkit.getPlayer(strings[1]);
                    if (strings.length == 1) {
                        player.sendMessage(ChatColor.GREEN + "[PlayerPerms] 명령어 사용 방법: /<commands> 추가 (Player)");
                    }
                    /*
                    온라인이 아닐때
                     */
                    else if (!Bukkit.getOnlinePlayers().contains(f)) {
                        player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 등록할 친구가 온라인이 아닙니다.");
                    }
                    /*
                    null 일때
                     */
                    else if (f == null) {
                        player.sendMessage(ChatColor.RED + "[PlayerPerms] info is null");
                    } else if (f.equals(player)) {
                        player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 자신에게 친구추가를 보낼수 없습니다.");
                    } else {
                        req(player, f);
                    }
                    break;
                case "ㅊ":
                    Player f2 = Bukkit.getPlayer(strings[1]);
                    if (strings.length == 1) {
                        player.sendMessage(ChatColor.GREEN + "[PlayerPerms] 명령어 사용 방법: /<commands> 추가 (Player)");
                    }
                    /*
                    온라인이 아닐때
                     */
                    else if (!Bukkit.getOnlinePlayers().contains(f2)) {
                        player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 등록할 친구가 온라인이 아닙니다.");
                    }
                    /*
                    null 일때
                     */
                    else if (f2 == null) {
                        player.sendMessage(ChatColor.RED + "[PlayerPerms] info is null");
                    } else if (f2.equals(player)) {
                        player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 자신에게 친구추가를 보낼수 없습니다.");
                    } else {
                        req2(player, f2);
                    }
                    break;
                case "add":
                    Player f3 = Bukkit.getPlayer(strings[1]);
                    if (strings.length == 1) {
                        player.sendMessage(ChatColor.GREEN + "[PlayerPerms] 명령어 사용 방법: /<commands> 추가 (Player)");
                    }
                    /*
                    온라인이 아닐때
                     */
                    else if (!Bukkit.getOnlinePlayers().contains(f3)) {
                        player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 등록할 친구가 온라인이 아닙니다.");
                    }
                    /*
                    null 일때
                     */
                    else if (f3 == null) {
                        player.sendMessage(ChatColor.RED + "[PlayerPerms] info is null");
                    } else if (f3.equals(player)) {
                        player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 자신에게 친구추가를 보낼수 없습니다.");
                    } else {
                        req3(player, f3);
                    }
                    break;
                case "수락":
                    if (Timer.getPlayerInviteTime().containsKey(player)) {
                        FD.addFriend(player.getUniqueId(), Timer.getPlayerInviteOwner().get(player).getUniqueId());
                        player.sendMessage(ChatColor.GREEN + "[PlayerPerms] 당신은 이제 " + Timer.getPlayerInviteOwner().get(player).getName() + "님과 친구입니다.");
                        Timer.getPlayerInviteOwner().get(player).sendMessage( ChatColor.GREEN + "[PlayerPerms] 당신은 이제 " + player.getName() + "님과 친구입니다.");
                        Timer.getPlayerInviteTime().remove(player);
                        Timer.getPlayerInviteOwner().remove(player);
                    } else player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 받은 친구 요청이 존재하지 않습니다.");
                case "거절":
                    if (Timer.getPlayerInviteTime().containsKey(player)) {
                        player.sendMessage(ChatColor.GREEN + "[PlayerPerms] " + Timer.getPlayerInviteOwner().get(player).getName() + "님의 친구 추가 요청을 거절했습니다.");
                        Timer.getPlayerInviteOwner().get(player).sendMessage(ChatColor.GREEN + "[PlayerPerms] " + player.getName() + "님이 친구 추가 요청을 거절했습니다.");
                        Timer.getPlayerInviteTime().remove(player);
                        Timer.getPlayerInviteOwner().remove(player);
                    } else player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 받은 친구 요청이 존재하지 않습니다.");
            }
            return true;
        } catch (Exception e) {
            Player player = (Player) sender;
            player.sendMessage(ChatColor.RED + "[PlayerPerms] 오류가 발생했습니다");
            player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 로그: " + e + "\n[PlayerPerms] 오류코드: 0x14");
        }
        return false;
    }

    public void req(Player player, Player f) {
        try {
            Timer.getPlayerInviteOwner().put(f, player);
            Timer.getPlayerInviteTime().put(f, 60);
            player.sendMessage(ChatColor.GREEN + "[PlayerPerms] 해당 플레이어에게 성공적으로 친구 요청을 보냈습니다.");
            f.sendMessage(ChatColor.GREEN + player.getName() + "님이 친구 추가 요청을 보냈습니다. 60초 이내에 응답해주세요. (/친구 수락/거절)");
        } catch (Exception e) {
            player.sendMessage(ChatColor.RED + "[PlayerPerm] 친구 추가 요청을 보내는중에 오류가 발생했습니다.");
            player.sendMessage(ChatColor.YELLOW + "[PlayerPerm] 오류 로그: " + e + "\n[PlayerPerm] 오류 코드: 0x15");
        }
    }

    public void req2(Player player, Player f2) {
        try {
            Timer.getPlayerInviteOwner().put(f2, player);
            Timer.getPlayerInviteTime().put(f2, 60);
            player.sendMessage(ChatColor.GREEN + "해당 플레이어에게 성공적으로 친구 요청을 보냈습니다.");
            f2.sendMessage(ChatColor.GREEN + player.getName() + "님이 친구 추가 요청을 보냈습니다. 60초 이내에 응답해주세요. (/친구 수락/거절)");
        } catch (Exception e) {
            player.sendMessage(ChatColor.RED + "[PlayerPerm] 친구 추가 요청을 보내는중에 오류가 발생했습니다.");
            player.sendMessage(ChatColor.YELLOW + "[PlayerPerm] 오류 로그: " + e + "\n[PlayerPerm] 오류 코드: 0x15");
        }
    }

    public void req3(Player player, Player f3) {
        try {
            Timer.getPlayerInviteOwner().put(f3, player);
            Timer.getPlayerInviteTime().put(f3, 60);
            player.sendMessage(ChatColor.GREEN + "해당 플레이어에게 성공적으로 친구 요청을 보냈습니다.");
            f3.sendMessage(ChatColor.GREEN + player.getName() + "님이 친구 추가 요청을 보냈습니다. 60초 이내에 응답해주세요. (/친구 수락/거절)");
        } catch (Exception e) {
            player.sendMessage(ChatColor.RED + "[PlayerPerm] 친구 추가 요청을 보내는중에 오류가 발생했습니다.");
            player.sendMessage(ChatColor.YELLOW + "[PlayerPerm] 오류 로그: " + e + "\n[PlayerPerm] 오류 코드: 0x15");
        }
    }
}
