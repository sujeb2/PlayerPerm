package com.songro.commands.perks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PlayerChatColor implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player)commandSender;

        // lore
        ArrayList<String> op_lore = new ArrayList<>();
        ArrayList<String> green_lore = new ArrayList<>();
        ArrayList<String> yellow_lore = new ArrayList<>();
        ArrayList<String> orange_lore = new ArrayList<>();

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

        ItemStack orange = new ItemStack(Material.ORANGE_CONCRETE);
        ItemMeta orange_meta = red.getItemMeta();
        assert orange_meta != null;
        orange_meta.setDisplayName(ChatColor.GOLD + "주황색");
        orange_lore.add(ChatColor.DARK_GRAY + "========================");
        orange_lore.add(ChatColor.GRAY + "주황색 입니다.");
        orange_lore.add(ChatColor.GRAY + "오랜지와 색깔이 비슷해서 상큼할꺼 같은 색입니다.");
        orange_meta.setLore(orange_lore);
        orange.setItemMeta(orange_meta);

        try {
            Inventory inv = Bukkit.createInventory(null, 9, ChatColor.BOLD + "색깔 선택");
            if(player.hasPermission("def.op") || player.hasPermission("perks.op")) {
                inv.setItem(0, red);
            }
            inv.setItem(2, orange);
            inv.setItem(3, yellow);
            inv.setItem(4, green);

            player.openInventory(inv);
        } catch (Exception e) {
            player.sendMessage(ChatColor.RED + "[PlayerPerms] 오류가 발생했습니다.");
            player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 로그: " + e);
            player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 코드: 0x09");
        }
        return true;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player)e.getWhoClicked();
        ItemStack clicked = e.getCurrentItem();
        Inventory inv = e.getInventory();
        try {
            if(e.getRawSlot() == e.getSlot()) {
                switch(e.getSlot()) {
                    case 0:
                        player.setDisplayName(ChatColor.DARK_RED + "[STAFF] " + player.getName());
                        break;
                    case 1:
                        if(player.hasPermission("def.op") || player.hasPermission("perks.op") || player.hasPermission("perks.plus") || player.hasPermission("perks.pp") || player.hasPermission("perks.ultra")) {
                            if(player.hasPermission("def.op") || player.hasPermission("perks.op")) {
                                player.setDisplayName(ChatColor.GOLD + "[STAFF] " + player.getName());
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "[PlayerPerms] 이 색상을 사용할려면, 'Fruit+' 보다 같거나 높은 권한이 있어야합니다!");
                        }
                        break;
                    case 3:
                        if(player.hasPermission("def.op") || player.hasPermission("perks.op") || player.hasPermission("perks.plus") || player.hasPermission("perks.pp") || player.hasPermission("perks.ultra")) {
                            if(player.hasPermission("def.op") || player.hasPermission("perks.op")) {
                                player.setDisplayName(ChatColor.YELLOW + "[STAFF] " + player.getName());
                            } else if(player.hasPermission("perks.plus")) {
                                player.setDisplayName(ChatColor.YELLOW + "[FRUIT+] " + player.getName());
                            } else if(player.hasPermission("perks.pp")) {
                                player.setDisplayName(ChatColor.YELLOW + "[FRUIT++] " + player.getName());
                            } else if(player.hasPermission("perks.ultra")) {
                                player.setDisplayName(ChatColor.YELLOW + "[FRUIT U] " + player.getName());
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "[PlayerPerms] 이 색상을 사용할려면, 'Fruit+' 보다 같거나 높은 권한이 있어야합니다!");
                        }
                        break;
                    case 4:
                        if(player.hasPermission("def.op") || player.hasPermission("perks.op") || player.hasPermission("perks.plus") || player.hasPermission("perks.pp") || player.hasPermission("perks.ultra")) {
                            if(player.hasPermission("def.op") || player.hasPermission("perks.op")) {
                                player.setDisplayName(ChatColor.GREEN + "[STAFF] " + player.getName());
                            } else if(player.hasPermission("perks.plus")) {
                                player.setDisplayName(ChatColor.GREEN + "[FRUIT+] " + player.getName());
                            } else if(player.hasPermission("perks.pp")) {
                                player.setDisplayName(ChatColor.GREEN + "[FRUIT++] " + player.getName());
                            } else if(player.hasPermission("perks.ultra")) {
                                player.setDisplayName(ChatColor.GREEN + "[FRUIT U] " + player.getName());
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "[PlayerPerms] 이 색상을 사용할려면, 'Fruit+' 보다 같거나 높은 권한이 있어야합니다!");
                        }
                        break;
                    case 5:
                        player.sendMessage("wip");
                        break;
                }
                e.setCancelled(true);
            }
        } catch (Exception ex) {
            player.sendMessage("");
        }
    }
}