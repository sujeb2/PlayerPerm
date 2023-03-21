package com.songro.commands.perks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PlayerChatColor implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player)commandSender;

        // lore
        ArrayList<String> op_lore = new ArrayList<>();

        // item
        ItemStack red = new ItemStack(Material.RED_CONCRETE);
        ItemMeta op_meta = red.getItemMeta();
        assert op_meta != null;
        op_meta.setDisplayName(ChatColor.DARK_RED + "어두운 빨강색");
        op_lore.add(ChatColor.DARK_GRAY + "========================");
        op_lore.add(ChatColor.GRAY + "관리자를 위한 어두운 빨강색입니다.");
        op_lore.add(ChatColor.GRAY + "생각보다 괜찮을지도...?");
        op_meta.setLore(op_lore);
        red.setItemMeta(op_meta);

        try {
            Inventory inv = Bukkit.createInventory(null, 9, ChatColor.BOLD + "색깔 선택");
            inv.setItem(0, red);

            player.openInventory(inv);
        } catch (Exception e) {
            player.sendMessage(ChatColor.RED + "[PlayerPerms] 오류가 발생했습니다.");
            player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 로그: " + e);
            player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 코드: 0x09");
        }
        return true;
    }
}