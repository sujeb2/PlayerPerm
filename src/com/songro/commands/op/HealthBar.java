package com.songro.commands.op;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.boss.BarColor.*;

public class HealthBar implements CommandExecutor {
    boolean isOn = false;
    @Override
    public boolean onCommand(@NotNull CommandSender send, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player target = Bukkit.getPlayer(args[0]);
        Player player = (Player)send;
        float healthPercent = (float) (target.getHealth() / target.getMaxHealth());

        try {
            BossBar bossBar = Bukkit.createBossBar(target.getDisplayName() + "의 현재 체력: " + healthPercent * 100 + "%", BarColor.RED, BarStyle.SOLID);
            bossBar.setProgress(healthPercent);
            switch (args[0]) {
                case "on":
                    bossBar.addPlayer(player);
                    break;
                case "off":
                    bossBar.removePlayer(player);
                    break;
                default:
                    player.sendMessage(ChatColor.RED + "[PlayerPerms] 추가 명령어가 없습니다.");
                    break;
            }
        } catch (Exception e) {
            player.sendMessage(ChatColor.RED + "[PlayerPerms] 오류가 발생했습니다");
            player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 로그: " + e + "\n[PlayerPerms] 오류코드: 0x17");
        return true;
        }
        return false;
    }
}
