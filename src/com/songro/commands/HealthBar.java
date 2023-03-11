package com.songro.commands;

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
        try {
            assert target != null;
            BossBar bar = Bukkit.createBossBar(
                    target.getDisplayName() + "의 현재 체력",
                    BarColor.RED,
                    BarStyle.SOLID
            );
            if (args.length <= 0) {
                player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 플레이어 이름이 없습니다.");
                player.sendMessage(ChatColor.GREEN + "[PlayerPerms] 명령어 사용 방법: /<commands> (on) (off) <Player>");
            }

            switch (args[0]) {
                case "on":
                    isOn = true;
                    player.sendMessage(ChatColor.GREEN + "[PlayerPerms] " + target.getDisplayName() + "의 체력바가 켜졌습니다.");
                    while (isOn) {
                        var currentHealth = target.getHealth();
                        bar.addPlayer(player);
                        bar.setColor(RED);
                        bar.setProgress(currentHealth);
                    }
                    if(target == null) {
                        player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 해당 플레이어는 없거나, 서버에 들어오지 않았습니다.");
                    }
                    if(target.equals(player)){
                        player.sendMessage( ChatColor.YELLOW + "[PlayerPerms] 자신에게 자신의 체력바를 보여줄수 없습니다.");
                    }
                case "off":
                    isOn = false;
                    player.sendMessage(ChatColor.RED + "[PlayerPerms] " + target.getDisplayName() + "의 체력바가 꺼졌습니다.");
                    bar.removePlayer(player);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + args[0]);
            }
        } catch (Exception e) {
            player.sendMessage(ChatColor.RED + "[PlayerPerms] 오류가 발생했습니다");
            player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 로그: " + e + "\n[PlayerPerms] 오류코드: 0x17");
        return true;
        }
        return false;
    }
}
