package com.songro.commands.perks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public class RemoteEnderChest implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player)commandSender;
        Logger log = Bukkit.getLogger();

        try {
            if (commandSender instanceof Player) {
                try {
                    if (player.hasPermission("perks.iron") || player.hasPermission("def.op")) {
                        Inventory ender = player.getEnderChest();
                        player.openInventory(ender);
                        player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 10, 1);
                    } else {
                        player.sendMessage(ChatColor.RED + "[PlayerPerms] 권한이 없습니다, 이 명령어를 사용할려면, 'perks.iron' 보다 높거나 같은 권한이 필요합니다!");
                    }
                } catch (Exception e) {
                    player.sendMessage(org.bukkit.ChatColor.RED + "[PlayerPerms] 오류가 발생했습니다");
                    player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 로그: " + e + "\n[PlayerPerms] 오류코드: 0x71");
                }
            } else {
                log.warning("이 명령어는 플레이어만 사용할수 있습니다.");
            }
        } catch (Exception e) {
            player.sendMessage(org.bukkit.ChatColor.RED + "[PlayerPerms] 오류가 발생했습니다");
            player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 로그: " + e + "\n[PlayerPerms] 오류코드: 0x70");
        }

        return true;
    }
}
