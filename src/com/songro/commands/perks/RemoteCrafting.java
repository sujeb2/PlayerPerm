package com.songro.commands.perks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

import static com.songro.Main.plugin;

public class RemoteCrafting implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player)commandSender;
        Logger log = Bukkit.getLogger();
        boolean isOn = plugin.getCustomConfig().getBoolean("perks.remoteCrafting");

            if (isOn) {
                try {
                    if (commandSender instanceof Player) {
                        try {
                            if (player.hasPermission("perks.plus") || player.hasPermission("def.op")) {
                                player.openWorkbench(null, true);
                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_WORK_BUTCHER, 10, 1);
                            } else {
                                player.sendMessage(ChatColor.RED + "[PlayerPerms] 권한이 없습니다, 이 명령어를 사용할려면, 'perks.iron' 보다 높거나 같은 권한이 필요합니다!");
                            }
                        } catch (Exception e) {
                            player.sendMessage(org.bukkit.ChatColor.RED + "[PlayerPerms] 오류가 발생했습니다");
                            player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 로그: " + e + "\n[PlayerPerms] 오류코드: 0x61");
                        }
                    } else {
                        log.warning("이 명령어는 플레이어만 사용할수 있습니다.");
                    }
                    if(commandSender.equals(Bukkit.getConsoleSender())) {
                        log.warning("이 명령어는 플레이어만 사용할수 있습니다.");
                    }
                } catch (Exception e) {
                    player.sendMessage(org.bukkit.ChatColor.RED + "[PlayerPerms] 오류가 발생했습니다");
                    player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 로그: " + e + "\n[PlayerPerms] 오류코드: 0x60");
                }
            } else {
                player.sendMessage(ChatColor.RED + "[PlayerPerms] 현재 설정 파일에서 이 기능이 꺼져있습니다.");
            }
            return true;
        }
    }
