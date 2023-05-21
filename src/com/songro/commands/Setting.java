package com.songro.commands;

import com.songro.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class Setting implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(commandSender instanceof Player player)) {
            commandSender.sendMessage("[PlayerPerms] 플레이어만 이 명령어를 사용할수 있습니다.");
            return true;
        } else {
            try {
                Inventory settingInv = Bukkit.createInventory(null, 9, "설정");

                player.openInventory(settingInv);
            } catch (Exception e) {
                player.sendMessage(Main.plugin.svname + "오류가 발생했습니다.");
            }
        }


        return true;
    }
}
