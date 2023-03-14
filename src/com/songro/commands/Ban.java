package com.songro.commands;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public class Ban implements CommandExecutor {
    public Logger logger;

    @Override
    public boolean onCommand(@NotNull CommandSender send, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Logger log = getLogger();
        String reason = args[3];
        Player player = (Player) send;
        Player target = Bukkit.getPlayer(args[2]);
        int ban = 0;
        int ipban = 0;

        if (player.isOp()) {
            try {
                try {
                    if (args.length <= 0) {
                        player.sendMessage(ChatColor.GREEN + "[PlayerPerms] 사용방법: /<command> (ip/list/normal/unban) <Player>");
                    }
                    if (args[0].equals("ip")) {
                        try {
                            if (target == null) {
                                try {
                                    player.sendMessage(ChatColor.RED + "[PlayerPerms] " + target.getDisplayName() + "이라는 플레이어가 존재하지 않거나, 서버에 들어온적이 없습니다.");
                                } catch (NullPointerException npe2) {
                                    player.sendMessage("[PlayerPerms] 오류가 발생했습니다");
                                    player.sendMessage("[PlayerPerms] 오류 로그: " + npe2 + "\n[PlayerPerms] 오류 코드: 0x22");
                                }
                            }
                            try {
                                assert target != null;
                                Bukkit.getBanList(BanList.Type.IP).addBan(target.getAddress().getAddress().getHostAddress(), reason, null, target.getName());
                                player.sendMessage(ChatColor.GREEN + "[PlayerPerms] " + target.getDisplayName() + "이(가) 성공적으로 " + reason + " 이유로 IP밴 당했습니다.");
                                ban++;
                                ipban++;
                            } catch (NullPointerException npe) {
                                player.sendMessage(ChatColor.RED + "[PlayerPerms] 오류가 발생했습니다");
                                player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 로그: " + npe + "\n[PlayerPerms] 오류 코드: 0x22");
                            }
                        } catch (Exception e) {
                            player.sendMessage(ChatColor.RED + "[PlayerPerms] 오류가 발생했습니다");
                            player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 로그: " + e + "\n[PlayerPerms] 오류코드: 0x23");
                        }
                    }
                    //if (args[0].equals("list")) {
                    //    player.sendMessage("[PlayerPerms] 밴 리스트");
                    //    player.sendMessage(ChatColor.DARK_GRAY + "===============================");
                    //    player.sendMessage(ChatColor.DARK_GRAY + ": " + ChatColor.RED + "일반 밴 \n" + Bukkit.getBannedPlayers());
                    //    player.sendMessage(ChatColor.DARK_GRAY + ": " + ChatColor.DARK_RED + "IP 밴 \n" + Bukkit.getIPBans() + ", 이유:" + reason);
                    //    player.sendMessage(ChatColor.DARK_GRAY + ": " + ChatColor.WHITE + "현재 총 " + ban + "명의 사람이 밴되고, " + ipban + "명의 사람들이 IP밴됨.");
                    //}
                    if (args[0].equals("unban")) {
                        try {
                            if (!target.isBanned())
                                player.sendMessage(ChatColor.RED + "[PlayerPerms] 해당 플레이어는 밴 되지 않았습니다.");
                            else {
                                Bukkit.unbanIP(target.getAddress().getHostName());
                                player.sendMessage(ChatColor.GREEN + "[PlayerPerms] 성공적으로 " + target.getDisplayName() + "의 밴을 풀었습니다.");
                                ipban--;
                                ban--;
                            }
                        } catch (NullPointerException npe3) {
                            player.sendMessage(ChatColor.RED + "[PlayerPerms] 오류가 발생했습니다");
                            player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 로그: " + npe3 + "\n[PlayerPerms] 오류 코드: 0x22");
                        }
                    }
                    if (args[0].equals("normal")) {
                        try {
                            try {
                                var banned = target.isBanned();

                                player.sendMessage(String.valueOf(banned));
                            } catch (NullPointerException npe4) {
                                player.sendMessage(ChatColor.RED + "[PlayerPerms] 오류가 발생했습니다");
                                player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 로그: " + npe4 + "\n[PlayerPerms] 오류 코드: 0x22");
                            }
                        } catch (Exception e) {
                            player.sendMessage(ChatColor.RED + "[PlayerPerms] 오류가 발생했습니다");
                            player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 로그: " + e + "\n[PlayerPerms] 오류 코드: 0x22");
                        }
                    }
                } catch (Exception e) {
                    player.sendMessage(ChatColor.RED + "[PlayerPerms] 오류가 발생했습니다");
                    player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 로그: " + e + "\n[PlayerPerms] 오류코드: 0x21");
                }
            } catch (Exception e) {
                player.sendMessage(ChatColor.RED + "[PlayerPerms] 오류가 발생했습니다");
                player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 로그: " + e + "\n[PlayerPerms] 오류코드: 0x20");
            }
        } else {
            player.sendMessage(ChatColor.RED + "[PlayerPerms] 이 명령어는 OP만 사용할수 있습니다.");
        }
        return false;
    }

    public Logger getLogger() {
        return logger;
    }
}
