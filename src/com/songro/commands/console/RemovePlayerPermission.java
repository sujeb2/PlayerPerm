package com.songro.commands.console;

import com.songro.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public class RemovePlayerPermission implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Logger log = Bukkit.getLogger();
        Player player = (Player)sender;
        var target = Bukkit.getPlayer(args[0]);

        try {
            if(player.isOp()) {
                if (player.hasPermission("def.op") || player.getDisplayName().equals("CONSOLE")) {
                    if (args[1].equalsIgnoreCase("plus")) {
                        assert target != null;
                        if (!target.isPermissionSet("perks.p") || !target.hasPermission("def.op")) {
                            player.sendMessage("already removed");
                        } else {
                            player.sendMessage("removed");
                            try {
                                if(target != null) {
                                    target.addAttachment(Main.getPlugin(), "perks.pp", false);
                                    target.addAttachment(Main.getPlugin(), "perks.ultra", false);
                                    target.addAttachment(Main.getPlugin(), "perks.plus", false);
                                    target.recalculatePermissions();
                                    target.setDisplayName(target.getName());
                                    target.setCustomName(target.getName());
                                    target.setCustomNameVisible(true);
                                } else {
                                    player.sendMessage(ChatColor.RED + "[PlayerPerms] 해당 플레이어는 현재 온라인이 아닙니다");
                                }
                            } catch (Exception e) {
                                log.severe("오류가 발생했습니다");
                                log.severe("오류 로그: " + e);
                                log.severe("오류 코드: 0x81");
                                player.sendMessage(ChatColor.RED + "[PlayerPerms] 오류가 발생했습니다");
                                player.sendMessage(ChatColor.YELLOW + "오류 로그: " + e + "\n[PlayerPerms] 오류 코드: 0x81");
                            }
                            if (!target.isOnline()) {
                                player.sendMessage("[PlayerPerms] 해당 플레이어가 온라인이 아니여 메세지를 보내지 못했습니다.");
                            }
                        }
                    }
                    if (args[1].equalsIgnoreCase("plusplus")) {
                        assert target != null;
                        if (!target.isPermissionSet("perks.pp") || !target.hasPermission("def.op")) {
                            player.sendMessage("already removed");
                        } else {
                            player.sendMessage("removed");
                            try {
                                if(target != null) {
                                    target.addAttachment(Main.getPlugin(), "perks.pp", false);
                                    target.addAttachment(Main.getPlugin(), "perks.ultra", false);
                                    target.addAttachment(Main.getPlugin(), "perks.plus", false);
                                    target.recalculatePermissions();
                                    target.setDisplayName(target.getName());
                                    target.setCustomName(target.getName());
                                    target.setCustomNameVisible(true);
                                } else {
                                    player.sendMessage(ChatColor.RED + "[PlayerPerms] 해당 플레이어는 현재 온라인이 아닙니다");
                                }
                            } catch (Exception e) {
                                log.severe("오류가 발생했습니다");
                                log.severe("오류 로그: " + e);
                                log.severe("오류 코드: 0x81");
                                player.sendMessage(ChatColor.RED + "[PlayerPerms] 오류가 발생했습니다");
                                player.sendMessage(ChatColor.YELLOW + "오류 로그: " + e + "\n[PlayerPerms] 오류 코드: 0x81");
                            }
                            if (!target.isOnline()) {
                                player.sendMessage("[PlayerPerms] 해당 플레이어가 온라인이 아니여 메세지를 보내지 못했습니다.");
                            }
                        }
                    }
                    if (args[1].equalsIgnoreCase("ultra")) {
                        assert target != null;
                        if (!target.isPermissionSet("perks.ultra") || !target.hasPermission("def.op")) {
                            player.sendMessage("already removed");
                        } else {
                            player.sendMessage("removed");
                            try {
                                if(target != null) {
                                    target.addAttachment(Main.getPlugin(), "perks.pp", false);
                                    target.addAttachment(Main.getPlugin(), "perks.ultra", false);
                                    target.addAttachment(Main.getPlugin(), "perks.plus", false);
                                    target.recalculatePermissions();
                                    target.setDisplayName(target.getName());
                                    target.setCustomName(target.getName());
                                    target.setCustomNameVisible(true);
                                } else {
                                    player.sendMessage(ChatColor.RED + "[PlayerPerms] 해당 플레이어는 현재 온라인이 아닙니다");
                                }
                            } catch (Exception e) {
                                log.severe("오류가 발생했습니다");
                                log.severe("오류 로그: " + e);
                                log.severe("오류 코드: 0x81");
                                player.sendMessage(ChatColor.RED + "[PlayerPerms] 오류가 발생했습니다");
                                player.sendMessage(ChatColor.YELLOW + "오류 로그: " + e + "\n[PlayerPerms] 오류 코드: 0x81");
                            }
                            if (!target.isOnline()) {
                                player.sendMessage("[PlayerPerms] 해당 플레이어가 온라인이 아니여 메세지를 보내지 못했습니다.");
                            }
                        }
                    }
                    if (args[0].length() == 0) {
                        player.sendMessage(ChatColor.RED + "[PlayerPerms] 추가 명령어가 없습니다");
                        player.sendMessage(ChatColor.RED + "[PlayerPerms] 사용방법: /<command> <Player> (plus/plusplus/ultra");
                    }
                    if (args[1].length() == 0) {
                        player.sendMessage(ChatColor.RED + "[PlayerPerms] 추가 명령어가 없습니다");
                        player.sendMessage(ChatColor.RED + "[PlayerPerms] 사용방법: /<command> <Player> (plus/plusplus/ultra");
                    }
                }
                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "[PlayerPerms] 추가 명령어가 없습니다");
                    player.sendMessage(ChatColor.RED + "[PlayerPerms] 사용방법: /<command> <Player> (plus/plusplus/ultra");
                }
            } else {
                player.sendMessage(ChatColor.RED + "[PlayerPerms] 이 명령어는 OP만 사용가능 합니다.");
            }
        } catch (Exception e) {
            log.severe("오류가 발생했습니다");
            log.severe("오류 로그: " + e);
            log.severe("오류 코드: 0x80");
            player.sendMessage(ChatColor.RED + "[PlayerPerms] 오류가 발생했습니다");
            player.sendMessage(ChatColor.YELLOW + "오류 로그: " + e + "\n[PlayerPerms] 오류 코드: 0x80");
            return false;
        }

        return true;
    }
}
