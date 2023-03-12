package com.songro.commands;

import com.songro.data.FD;
import com.songro.handler.Timer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class Friend implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        try {
            if (commandSender instanceof Player player) {
                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "[PlayerPerms] 추가 명령어가 없습니다.");
                    return false;
                }
                switch (args[0]) {
                    case "추가":
                        Player f = Bukkit.getPlayer(args[1]);
                        if (f == null) player.sendMessage(ChatColor.RED + "[PlayerPerms] 존재하지 않는 플레이어거나, 서버에 들어온적이 없습니다.");
                        else {
                            if (!Bukkit.getOnlinePlayers().contains(f))
                                player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] '" + f.getDisplayName() +"' 플레이어는 온라인이 아닙니다.");
                            else if (f.equals(player))
                                player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 자기 자신에게 친구 요청을 보낼 수 없습니다.");
                            else if (FD.getPlayerFriendList(player.getUniqueId()).contains(f.getUniqueId().toString()))
                                player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 이미 " + f.getDisplayName() + " 플레이어와 친구입니다.");
                            else if (Timer.getPlayerInviteTime().containsKey(f))
                                player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 누군가 해당 플레이어에게 친구 요청을 보냈습니다.");
                            else if (FD.getPlayerIgnoreList(player.getUniqueId()).contains(f.getUniqueId().toString()) || FD.getPlayerIgnoreList(f.getUniqueId()).contains(player.getUniqueId().toString()))
                                player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 해당 플레이어에게 친구 요청을 보낼 수 없습니다.");
                            else {
                                request(player, f);
                            }
                        }

                    case "수락":
                        if (Timer.getPlayerInviteTime().containsKey(player)) {
                            FD.addFriend(player.getUniqueId(), Timer.getPlayerInviteOwner().get(player).getUniqueId());
                            player.sendMessage(ChatColor.GREEN + "[PlayerPerms] 이제 " + Timer.getPlayerInviteOwner().get(player).getName() + "님과 친구입니다.");
                            Timer.getPlayerInviteOwner().get(player).sendMessage(ChatColor.GREEN + "[PlayerPerms] 이제 " + player.getName() + "님과 친구입니다.");
                            Timer.getPlayerInviteTime().remove(player);
                            Timer.getPlayerInviteOwner().remove(player);
                        } else player.sendMessage(ChatColor.GRAY + "[PlayerPerms] 받은 친구 요청이 존재하지 않습니다.");
                        break;

                    case "거절":
                        if (Timer.getPlayerInviteTime().containsKey(player)) {
                            player.sendMessage(ChatColor.RED + "[PlayerPerms] " + Timer.getPlayerInviteOwner().get(player).getDisplayName() + "님의 친구 추가 요청을 거절했습니다.");
                            Timer.getPlayerInviteOwner().get(player).sendMessage( ChatColor.RED + "[PlayerPerms] " + player.getDisplayName() + "님이 친구 추가 요청을 거절했습니다.");
                            Timer.getPlayerInviteTime().remove(player);
                            Timer.getPlayerInviteOwner().remove(player);
                        } else player.sendMessage(ChatColor.GRAY + "[PlayerPerms] 받은 친구 요청이 존재하지 않습니다.");
                        break;

                    case "삭제":
                        Player target = Bukkit.getPlayer(args[1]);
                        if (target == null) player.sendMessage(ChatColor.RED + "[PlayerPerms] 존재하지 않는 플레이어거나, 서버에 들어온적이 없습니다.");
                        else if (FD.getPlayerFriendList(player.getUniqueId()).contains(target.getUniqueId().toString())) {
                            FD.removeFriend(player.getUniqueId(), target.getUniqueId());
                            player.sendMessage(ChatColor.RED + "[PlayerPerms] " + args[1] + "님을 친구 목록에서 삭제했습니다.");
                        } else player.sendMessage(ChatColor.RED + "[PlayerPerms] 이미 그 플레이어와는 친구가 아닙니다.");

                    case "목록":
                        StringBuilder message = new StringBuilder("친구 목록");
                        for (String uuid : FD.getPlayerFriendList(player.getUniqueId())) {
                            if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(UUID.fromString(uuid))))
                                message.append("\n").append(ChatColor.GREEN).append(Bukkit.getPlayer(UUID.fromString(uuid)));
                            else
                                message.append("\n").append(ChatColor.RED).append(Bukkit.getPlayer(UUID.fromString(uuid)));
                        }
                        player.sendMessage(message.toString());

                    case "차단":
                        target = Bukkit.getPlayer(args[1]);
                        if (target == null) player.sendMessage(ChatColor.RED + "[PlayerPerms] 존재하지 않는 플레이어거나, 서버에 들어온적이 없습니다.");
                        else if (args[2].equals("추가")) {
                            if (FD.addIgnore(player.getUniqueId(), target.getUniqueId()))
                                player.sendMessage(ChatColor.RED + "[PlayerPerms] " + args[1] + "님을 차단했습니다, 이제 더 이상" + args[1] + "플레이어에게 친추요청을 받을수 없습니다.");
                            else player.sendMessage("[PlayerPerms] 이미 그 플레이어는 차단됐습니다.");
                        } else if (args[2].equals("해제")) {
                            if (FD.removeIgnore(player.getUniqueId(), target.getUniqueId()))
                                player.sendMessage( ChatColor.GREEN + "[PlayerPerms] " + args[1] + "님을 차단 해제 했습니다.");
                            else player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 그 플레이어는 차단이 돼 있지 않습니다.");
                        }
                    case "차단목록":
                        StringBuilder messages = new StringBuilder("차단 목록");
                        for (String uuid : FD.getPlayerIgnoreList(player.getUniqueId())) {
                            messages.append("\n").append(ChatColor.RED).append(Bukkit.getPlayer(UUID.fromString(uuid)));
                        }
                        player.sendMessage(messages.toString());
                }
            } else commandSender.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 이 명령어는 플레이어만 사용할 수 있습니다.");
        } catch (Exception e) {
            commandSender.sendMessage(ChatColor.RED + "[PlayerPerms] 오류가 발생했습니다.");
            commandSender.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 로그: " + e + "\n[PlayerPerms] 오류 코드 0x14");
        }
        return false;
    }


    private static void request(Player player, Player friend) {
        try {
            Timer.getPlayerInviteOwner().put(friend, player);
            Timer.getPlayerInviteTime().put(friend, 60);
            player.sendMessage("해당 플레이어에게 성공적으로 친구 요청을 보냈습니다.");
            friend.sendMessage(player.getName() + "님이 친구 추가 요청을 보냈습니다. 1분이내에만 수락이나 거절할수 있습니다. (/친구 수락/거절)");
        } catch (Exception e) {
            player.sendMessage(ChatColor.RED + "[PlayerPerms] 오류가 발생했습니다.");
            player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 로그: " + e + "\n[PlayerPerms] 오류 코드 0x15");
        }
    }

}