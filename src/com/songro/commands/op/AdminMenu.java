package com.songro.commands.op;

import com.songro.PluginCore;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class AdminMenu implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        Player player = (Player)commandSender;

        // btn
        ItemStack plyList = new ItemStack(Material.LEGACY_SKULL_ITEM, 1);
        /*
        Barrier dummy item
         */
        ItemStack chkLog = new ItemStack(Material.BARRIER, 1);

        // item meta
        ItemMeta chkLogBtnMeta = chkLog.getItemMeta();
        ItemMeta plyListBtnMeta = plyList.getItemMeta();

        try {
            if(!player.isOp()) {
                player.sendMessage(ChatColor.RED + PluginCore.plugin.svname + " 이 명령어는 관리자만 사용가능합니다.");
            } else {
                try {

                } catch (Exception e) {
                    player.sendMessage(ChatColor.RED + PluginCore.plugin.svname + " GUI를 여는도중 오류가 발생했습니다.");
                    player.sendMessage(ChatColor.YELLOW + PluginCore.plugin.svname + " 오류 로그: " + e + PluginCore.plugin.svname + "\n 오류 코드: 0x121");
                }
            }
        } catch (Exception e) {
            player.sendMessage(ChatColor.RED + PluginCore.plugin.svname + " GUI를 여는도중 오류가 발생했습니다.");
            player.sendMessage(ChatColor.YELLOW + PluginCore.plugin.svname + " 오류 로그: " + e + PluginCore.plugin.svname + "\n 오류 코드: 0x120");
        }

        return true;
    }
}
