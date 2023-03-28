package com.songro.commands.op;

import com.songro.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Broadcast implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = (Player)commandSender;
        String msg = args[1];
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        StringBuilder builder = new StringBuilder();

        try {
            if(args[0].length() == 0) {
                player.sendMessage(ChatColor.RED + Main.plugin.svname + " 추가 명령어가 없습니다");
                player.sendMessage(ChatColor.RED + Main.plugin.svname + " 명령어 사용방법: /<command> (announce/patch/fix) <Message>");
            } else {
                try {
                    if(args[1].length() == 1) {
                        if (args[0].equals("announce")) {
                            Bukkit.broadcastMessage(ChatColor.RED + " : 공지사항 " + ChatColor.DARK_GRAY + ChatColor.ITALIC + "(Upload: " + dtf.format(now) + ") " + ChatColor.RESET + ChatColor.DARK_GRAY + ">> " + ChatColor.WHITE + msg);
                        }
                        if (args[0].equals("patch")) {
                            Bukkit.broadcastMessage(ChatColor.BLUE + " : 패치노트 " + ChatColor.DARK_GRAY + ChatColor.ITALIC + "(Upload: " + dtf.format(now) + ") " + ChatColor.RESET + ChatColor.DARK_GRAY + ">> " + ChatColor.WHITE + msg);
                        }
                        if (args[0].equals("fix")) {
                            Bukkit.broadcastMessage(ChatColor.GRAY + " : 서버 점검 " + ChatColor.DARK_GRAY + ChatColor.ITALIC + "(Upload: " + dtf.format(now) + ") " + ChatColor.RESET + ChatColor.DARK_GRAY + ">> " + ChatColor.WHITE + "서버 점검이 일어날 예정입니다\n" + ChatColor.DARK_GRAY + " : " + "서버 점검 날짜: " + ChatColor.AQUA + msg);
                        }
                        if (args[0].equalsIgnoreCase(msg)) {
                            player.sendMessage(ChatColor.RED + Main.plugin.svname + " 명령어 사용방법이 잘못됐습니다.");
                            player.sendMessage(ChatColor.RED + Main.plugin.svname + " 명령어 사용방법: /<command> (announce/patch/fix) <Message>");
                        }
                    } else {
                        for (int i = 1; i < args.length; i++) {
                            builder.append(args[i]);
                            builder.append(" ");
                        }

                        String finalMessage = builder.toString();
                        finalMessage = finalMessage.stripTrailing();
                        if (args[0].equals("announce")) {
                            Bukkit.broadcastMessage(ChatColor.RED + " : 공지사항 " + ChatColor.DARK_GRAY + ChatColor.ITALIC + "(Upload: " + dtf.format(now) + ") " + ChatColor.RESET + ChatColor.DARK_GRAY + ">> " + ChatColor.WHITE + finalMessage);
                        }
                        if (args[0].equals("patch")) {
                            Bukkit.broadcastMessage(ChatColor.BLUE + " : 패치노트 " + ChatColor.DARK_GRAY + ChatColor.ITALIC + "(Upload: " + dtf.format(now) + ") " + ChatColor.RESET + ChatColor.DARK_GRAY + ">> " + ChatColor.WHITE + finalMessage);
                        }
                        if (args[0].equals("fix")) {
                            Bukkit.broadcastMessage(ChatColor.GRAY + " : 서버 점검 " + ChatColor.DARK_GRAY + ChatColor.ITALIC + "(Upload: " + dtf.format(now) + ") " + ChatColor.RESET + ChatColor.DARK_GRAY + ">> " + ChatColor.WHITE + "서버 점검이 일어날 예정입니다\n" + ChatColor.DARK_GRAY + " : " + "서버 점검 날짜: " + ChatColor.AQUA + finalMessage);
                        }
                        if (args[0].equalsIgnoreCase(msg)) {
                            player.sendMessage(ChatColor.RED + Main.plugin.svname + " 명령어 사용방법이 잘못됐습니다.");
                            player.sendMessage(ChatColor.RED + Main.plugin.svname + " 명령어 사용방법: /<command> (announce/patch/fix) <Message>");
                        }
                    }
                } catch (Exception e) {
                    player.sendMessage(ChatColor.RED + Main.plugin.svname + " 오류가 발생했습니다");
                    player.sendMessage(ChatColor.YELLOW + Main.plugin.svname + " 오류 로그: " + e + Main.plugin.svname + "\n 오류 코드: 0x112");
                }
            }
            if(args[1].length() == 0) {
                player.sendMessage(ChatColor.RED + Main.plugin.svname + " 추가 명령어가 없습니다");
                player.sendMessage(ChatColor.RED + Main.plugin.svname + " 명령어 사용방법: /<command> (announce/patch/fix) <Message>");
            }
        } catch (Exception e) {
            player.sendMessage(ChatColor.RED + Main.plugin.svname + " 오류가 발생했습니다");
            player.sendMessage(ChatColor.YELLOW + Main.plugin.svname + " 오류 로그: " + e + Main.plugin.svname + "\n 오류 코드: 0x110");
        }

        return true;
    }
}
