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
        ArrayList<String> nr_lore = new ArrayList<>();
        ArrayList<String> green_lore = new ArrayList<>();
        ArrayList<String> yellow_lore = new ArrayList<>();
        ArrayList<String> orange_lore = new ArrayList<>();
        ArrayList<String> noperm_lore = new ArrayList<>();
        ArrayList<String> black_lore = new ArrayList<>();
        ArrayList<String> white_lore = new ArrayList<>();
        ArrayList<String> na_lore = new ArrayList<>();

        // colors
        ItemStack red = new ItemStack(Material.RED_CONCRETE);
        ItemMeta op_meta = red.getItemMeta();
        assert op_meta != null;
        op_meta.setDisplayName(ChatColor.DARK_RED + "어두운 빨강색");
        op_lore.add(ChatColor.DARK_GRAY + "========================");
        op_lore.add(ChatColor.GRAY + "관리자를 위한 어두운 빨강색입니다.");
        op_lore.add(ChatColor.GRAY + "생각보다 괜찮을지도...?");
        op_meta.setLore(op_lore);
        red.setItemMeta(op_meta);

        ItemStack normal_red = new ItemStack(Material.RED_CONCRETE);
        ItemMeta nr_meta = red.getItemMeta();
        assert nr_meta != null;
        nr_meta.setDisplayName(ChatColor.RED + "빨강색");
        nr_lore.add(ChatColor.DARK_GRAY + "========================");
        nr_lore.add(ChatColor.GRAY + "기본 빨강색입니다.");
        nr_lore.add(ChatColor.GRAY + "생각보다 괜찮을지도...?");
        nr_meta.setLore(nr_lore);
        normal_red.setItemMeta(nr_meta);

        ItemStack green = new ItemStack(Material.GREEN_CONCRETE);
        ItemMeta green_meta = red.getItemMeta();
        assert green_meta != null;
        green_meta.setDisplayName(ChatColor.GREEN + "연두색");
        green_lore.add(ChatColor.DARK_GRAY + "========================");
        green_lore.add(ChatColor.GRAY + "연두색 입니다.");
        green_lore.add(ChatColor.GRAY + "싱싱한 상추색과 비슷합니다.");
        green_meta.setLore(green_lore);
        green.setItemMeta(green_meta);

        ItemStack yellow = new ItemStack(Material.YELLOW_CONCRETE);
        ItemMeta yellow_meta = red.getItemMeta();
        assert yellow_meta != null;
        yellow_meta.setDisplayName(ChatColor.YELLOW + "노란색");
        yellow_lore.add(ChatColor.DARK_GRAY + "========================");
        yellow_lore.add(ChatColor.GRAY + "노란색 입니다.");
        yellow_lore.add(ChatColor.GRAY + "개나리 색깔과 생각보다 비슷한편입니다.");
        yellow_meta.setLore(yellow_lore);
        yellow.setItemMeta(yellow_meta);

        ItemStack orange = new ItemStack(Material.BLACK_CONCRETE);
        ItemMeta orange_meta = red.getItemMeta();
        assert orange_meta != null;
        orange_meta.setDisplayName(ChatColor.DARK_GRAY + "검은색");
        black_lore.add(ChatColor.DARK_GRAY + "========================");
        black_lore.add(ChatColor.GRAY + "검은색 입니다.");
        black_lore.add(ChatColor.GRAY + "쿠앤크 쿠키 색깔과 비슷합니다.");
        orange_meta.setLore(black_lore);
        orange.setItemMeta(orange_meta);

        ItemStack black = new ItemStack(Material.ORANGE_CONCRETE);
        ItemMeta black_meta = red.getItemMeta();
        assert black_meta != null;
        black_meta.setDisplayName(ChatColor.GOLD + "주황색");
        orange_lore.add(ChatColor.DARK_GRAY + "========================");
        orange_lore.add(ChatColor.GRAY + "주황색 입니다.");
        orange_lore.add(ChatColor.GRAY + "오랜지와 색깔이 비슷해서 상큼할꺼 같은 색입니다.");
        black_meta.setLore(orange_lore);
        black.setItemMeta(black_meta);

        ItemStack nopermission = new ItemStack(Material.GRAY_DYE);
        ItemMeta noperm_meta = red.getItemMeta();
        assert noperm_meta != null;
        noperm_meta.setDisplayName(ChatColor.GRAY + "권한 없음");
        noperm_lore.add(ChatColor.DARK_GRAY + "========================");
        noperm_lore.add(ChatColor.GRAY + "현재 이 색상을 사용할수 있는 권한이 없습니다.");
        noperm_meta.setLore(noperm_lore);
        nopermission.setItemMeta(noperm_meta);

        ItemStack notaliv = new ItemStack(Material.BARRIER);
        ItemMeta na_meta = red.getItemMeta();
        assert na_meta != null;
        na_meta.setDisplayName(ChatColor.RED + "존재하지 않음");
        na_lore.add(ChatColor.DARK_GRAY + "========================");
        na_lore.add(ChatColor.GRAY + "현재 존재하지 않는 색상입니다.");
        na_meta.setLore(na_lore);
        notaliv.setItemMeta(na_meta);

        ItemStack white = new ItemStack(Material.WHITE_CONCRETE);
        ItemMeta white_meta = red.getItemMeta();
        assert white_meta != null;
        white_meta.setDisplayName(ChatColor.WHITE + "흰색");
        white_lore.add(ChatColor.DARK_GRAY + "========================");
        white_lore.add(ChatColor.GRAY + "흰색 입니다.");
        white_lore.add(ChatColor.GRAY + "기본으로 제공되는 색상입니다.");
        white_meta.setLore(white_lore);
        white.setItemMeta(white_meta);


        try {
            Inventory inv = Bukkit.createInventory(null, 9, ChatColor.BOLD + "색깔 선택");
            // reset
            inv.remove(red);
            inv.remove(normal_red);
            inv.remove(orange);
            inv.remove(green);
            inv.remove(black);
            inv.remove(notaliv);
            if(player.hasPermission("def.op") || player.hasPermission("perks.op")) {
                inv.setItem(0, red);
            }
            inv.setItem(1, normal_red);
            if(player.hasPermission("perks.plus")) {
                inv.setItem(2, black);
            } else {
                inv.setItem(2, nopermission);
            }
            if(player.hasPermission("perks.plus") || player.hasPermission("perks.pp")) {
                inv.setItem(3, yellow);
            } else {
                inv.setItem(3, nopermission);
            }
            if (player.hasPermission("perks.plus") || player.hasPermission("perks.pp") || player.hasPermission("perks.ultra")) {
                inv.setItem(4, green);
            } else {
                inv.setItem(4, nopermission);
            }
            inv.setItem(5, notaliv);
            inv.setItem(6, notaliv);
            inv.setItem(7, white);
            inv.setItem(8, orange);
            player.openInventory(inv);
        } catch (Exception e) {
            player.sendMessage(ChatColor.RED + "[PlayerPerms] 오류가 발생했습니다.");
            player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 로그: " + e);
            player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 코드: 0x09");
        }
        return true;
    }
}