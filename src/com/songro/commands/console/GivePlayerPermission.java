package com.songro.commands.console;

import com.songro.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
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
        var target = Bukkit.getPlayer(args[0]);

        try {
            if(player.isOp()) {
                if (player.hasPermission("def.op") || player.getDisplayName().equals("CONSOLE")) {
                    if (args[1].equalsIgnoreCase("plus")) {
                        assert target != null;
                        if (target.isPermissionSet("perks.p") || target.hasPermission("def.op")) {
                            player.sendMessage(ChatColor.YELLOW + Main.plugin.svname + " 이미 " + target.getDisplayName() + "님은  " + ChatColor.WHITE + "'perks.plus'" + ChatColor.YELLOW + " 보다 높거나 같은 권한을 가지고 있습니다.");
                        } else {
                            player.sendMessage(ChatColor.GREEN + Main.plugin.svname + target.getDisplayName() + "님에게 " + ChatColor.AQUA + "'perks.plus'" + ChatColor.GREEN + " 권한을 주었습니다!");
                            try {
                                if(target != null) {
                                    target.addAttachment(Main.getPlugin(), "perks.pp", false);
                                    target.addAttachment(Main.getPlugin(), "perks.ultra", false);
                                    target.addAttachment(Main.getPlugin(), "perks.plus", true);
                                    target.recalculatePermissions();
                                    target.setDisplayName(ChatColor.GREEN + "[FRUIT" + ChatColor.YELLOW + "+" + ChatColor.GREEN + "] " + target.getName() + ChatColor.WHITE);
                                    target.setCustomName(ChatColor.GREEN + "[FRUIT" + ChatColor.YELLOW + "+" + ChatColor.GREEN + "] " + target.getName() + ChatColor.WHITE);
                                    target.setCustomNameVisible(true);
                                } else {
                                    player.sendMessage(ChatColor.RED + "[PlayerPerms] 해당 플레이어는 현재 온라인이 아닙니다");
                                }
                            } catch (Exception e) {
                                log.severe("오류가 발생했습니다");
                                log.severe("오류 로그: " + e);
                                log.severe("오류 코드: 0x81");
                                player.sendMessage(ChatColor.RED + Main.plugin.svname + " 오류가 발생했습니다");
                                player.sendMessage(ChatColor.YELLOW + "오류 로그: " + e + Main.plugin.svname + "\n 오류 코드: 0x81");
                            }
                            if (target.isOnline()) {
                                target.playSound(target.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 1);
                                target.sendMessage(ChatColor.GREEN + Main.plugin.svname + " 축하합니다! 이제 " + ChatColor.GREEN + "'Fruit+'" + ChatColor.GREEN + " 해택을 경험할수 있습니다!");
                            } else {
                                player.sendMessage(Main.plugin.svname + " 해당 플레이어가 온라인이 아니여 메세지를 보내지 못했습니다.");
                            }
                        }
                    }
                    if (args[1].equalsIgnoreCase("plusplus")) {
                        assert target != null;
                        if (target.isPermissionSet("perks.pp") || target.hasPermission("def.op")) {
                            player.sendMessage(ChatColor.YELLOW + Main.plugin.svname + " 이미 " + target.getDisplayName() + "님은 " + ChatColor.GOLD + "'perks.plusplus'" + ChatColor.YELLOW + " 권한을 가지고 있습니다.");
                        } else {
                            player.sendMessage(ChatColor.GREEN + Main.plugin.svname +  target.getDisplayName() + "님에게 " + ChatColor.GOLD + "'perks.plusplus'" + ChatColor.GREEN + " 권한을 주었습니다!");
                            try {
                                if(target != null) {
                                    target.addAttachment(Main.getPlugin(), "perks.pp", true);
                                    target.addAttachment(Main.getPlugin(), "perks.ultra", false);
                                    target.addAttachment(Main.getPlugin(), "perks.plus", false);
                                    target.recalculatePermissions();
                                    target.setDisplayName(ChatColor.GREEN + "[FRUIT" + ChatColor.AQUA + "++" + ChatColor.GREEN + "] " + target.getName() + ChatColor.WHITE);
                                    target.setCustomName(ChatColor.GREEN + "[FRUIT" + ChatColor.AQUA + "++" + ChatColor.GREEN + "] " + target.getName() + ChatColor.WHITE);
                                    target.setCustomNameVisible(true);
                                } else {
                                    player.sendMessage(ChatColor.RED + Main.plugin.svname + " 해당 플레이어는 현재 온라인이 아닙니다");
                                }
                            } catch (Exception e) {
                                log.severe("오류가 발생했습니다");
                                log.severe("오류 로그: " + e);
                                log.severe("오류 코드: 0x81");
                                player.sendMessage(ChatColor.RED + Main.plugin.svname + " 오류가 발생했습니다");
                                player.sendMessage(ChatColor.YELLOW +Main.plugin.svname +  "오류 로그: " + e + Main.plugin.svname + "\n 오류 코드: 0x81");
                            }
                            if (target.isOnline()) {
                                target.playSound(target.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 1);
                                target.sendMessage(ChatColor.GREEN + Main.plugin.svname + " 축하합니다! 이제 " + ChatColor.GOLD + "'Fruit++'" + ChatColor.GREEN + " 해택을 경험할수 있습니다!");
                            } else {
                                player.sendMessage("[PlayerPerms] 해당 플레이어가 온라인이 아니여 메세지를 보내지 못했습니다.");
                            }
                        }
                    }
                    if (args[1].equalsIgnoreCase("ultra")) {
                        assert target != null;
                        if (target.isPermissionSet("perks.ultra") || target.hasPermission("def.op")) {
                            player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 이미 " + target.getDisplayName() + "님은 " + ChatColor.AQUA + "'perks.ultra'" + ChatColor.YELLOW + " 권한을 가지고 있습니다.");
                        } else {
                            player.sendMessage(ChatColor.GREEN + "[PlayerPerms] " + target.getDisplayName() + "님에게 " + ChatColor.AQUA + "'perks.ultra'" + ChatColor.GREEN + " 권한을 주었습니다!");
                            try {
                                if(target != null) {
                                    target.addAttachment(Main.getPlugin(), "perks.pp", false);
                                    target.addAttachment(Main.getPlugin(), "perks.ultra", true);
                                    target.addAttachment(Main.getPlugin(), "perks.plus", false);
                                    target.setDisplayName(ChatColor.GREEN + "[FRUIT" + ChatColor.DARK_GRAY + " U" + ChatColor.GREEN + "] " + target.getName() + ChatColor.WHITE);
                                    target.setCustomName(ChatColor.GREEN + "[FRUIT" + ChatColor.DARK_GRAY + " U" + ChatColor.GREEN + "] " + target.getName() + ChatColor.WHITE);
                                    target.setCustomNameVisible(true);
                                    target.recalculatePermissions();
                                    player.sendMessage("1");
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
                            if (target.isOnline()) {
                                target.playSound(target.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 1);
                                target.sendMessage(ChatColor.GREEN + "[PlayerPerm] 축하합니다! 이제 " + ChatColor.AQUA + "'Fruit U'" + ChatColor.GREEN + " 해택을 경험할수 있습니다!");
                            } else {
                                player.sendMessage("[PlayerPerms] 해당 플레이어가 온라인이 아니여 메세지를 보내지 못했습니다.");
                            }
                        }
                    }
                    if (args[1].equalsIgnoreCase("mutebypass")) {
                        assert target != null;
                        if (target.isPermissionSet("perks.mutebypass") || target.hasPermission("def.op")) {
                            player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 이미 " + target.getDisplayName() + "님은 " + ChatColor.AQUA + "'def.mutebypass'" + ChatColor.YELLOW + " 권한을 가지고 있습니다.");
                        } else {
                            player.sendMessage(ChatColor.GREEN + "[PlayerPerms] " + target.getDisplayName() + "님에게 " + ChatColor.AQUA + "'def.mutebypass'" + ChatColor.GREEN + " 권한을 주었습니다!");
                            try {
                                if(target != null) {
                                    target.addAttachment(Main.getPlugin(), "def.mutebypass", true);
                                    target.recalculatePermissions();
                                    player.sendMessage("1");
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
                            if (target.isOnline()) {
                                target.playSound(target.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 1);
                                target.sendMessage(ChatColor.GREEN + "[PlayerPerm] def.mutebypass 권한을 가지게 되었습니다.");
                            } else {
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
                if (args[0].length() == 0) {
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
