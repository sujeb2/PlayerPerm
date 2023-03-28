package com.songro.commands.perks;

import com.songro.Main;
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
                    if(player.hasPermission("perks.plus") || player.hasPermission("def.op") || player.hasPermission("perks.pp") || player.hasPermission("perks.ultra")) {
                        player.openInventory(Bukkit.createInventory(player, InventoryType.ANVIL));
                    } else {
                        player.sendMessage(ChatColor.RED + Main.plugin.svname + " 권한이 없습니다, 이 명령어를 사용할려면, 'perks.plus' 보다 높거나 같은 권한이 필요합니다!");
                    }
                } catch (Exception e) {
                    player.sendMessage(org.bukkit.ChatColor.RED + Main.plugin.svname + " 오류가 발생했습니다");
                    player.sendMessage(ChatColor.YELLOW + Main.plugin.svname + " 오류 로그: " + e + Main.plugin.svname + "\n 오류코드: 0x51");
                }
            } else {
                log.warning("이 명령어는 플레이어만 사용할수 있습니다.");
            }
        } catch (Exception e) {
            player.sendMessage(org.bukkit.ChatColor.RED + Main.plugin.svname + Main.plugin.svname + Main.plugin.svname + " 오류가 발생했습니다");
            player.sendMessage(ChatColor.YELLOW + Main.plugin.svname + " 오류 로그: " + e + Main.plugin.svname + "\n 오류코드: 0x50");
        }

        return true;
    }
}
