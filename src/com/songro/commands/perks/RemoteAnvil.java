package com.songro.commands.perks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public class RemoteAnvil implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player)commandSender;
        Logger log = Bukkit.getLogger();

        try {
            if (commandSender instanceof Player) {
                try {
                    player.openInventory(Bukkit.createInventory(player, InventoryType.ANVIL));
                } catch (Exception e) {
                    player.sendMessage(org.bukkit.ChatColor.RED + "[PlayerPerms] 오류가 발생했습니다");
                    player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 로그: " + e + "\n[PlayerPerms] 오류코드: 0x51");
                }
            } else {
                log.warning("이 명령어는 플레이어만 사용할수 있습니다.");
            }
        } catch (Exception e) {
            player.sendMessage(org.bukkit.ChatColor.RED + "[PlayerPerms] 오류가 발생했습니다");
            player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 로그: " + e + "\n[PlayerPerms] 오류코드: 0x50");
        }

        return true;
    }
}
