package com.songro.commands.op;

import com.songro.Main;
import com.songro.enchant.CustomEnchant;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class EnchantItem implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player)commandSender;
        Logger log = Bukkit.getLogger();

        ItemStack playerMainHandItem = player.getInventory().getItemInMainHand();

        if (!player.equals(Bukkit.getConsoleSender())) {
            if(player.isOp()) {
                //if (playerMainHandItem.equals(Material.STICK)) {
                    try {
                        ItemStack item = playerMainHandItem;
                        item.addUnsafeEnchantment(CustomEnchant.TELEPORT, 1);

                        ItemMeta meta = item.getItemMeta();
                        List<String> lore = new ArrayList<String>();
                        lore.add(ChatColor.GOLD + "Teleport I");

                        if (meta.hasLore()) {
                            for (String l : meta.getLore()) {
                                lore.add(l);
                            }
                        }
                        meta.setLore(lore);
                        item.setItemMeta(meta);

                        player.sendMessage(ChatColor.GREEN + Main.plugin.svname + " " + playerMainHandItem + " 에 인첸트를 적용했습니다.");
                        return true;
                    } catch (Exception e) {
                        player.sendMessage(ChatColor.RED + Main.plugin.svname + " 아이템에 인첸트를 적용하는중 오류가 발생했습니다.");
                        player.sendMessage(ChatColor.YELLOW + Main.plugin.svname + " 오류 로그: " + e);
                    }
                //} else {
                //    player.sendMessage(ChatColor.YELLOW + Main.plugin.svname + " 해당 아이템은 이 인첸트를 적용할수 없습니다!");
                //}
            } else {
                player.sendMessage(ChatColor.RED + Main.plugin.svname + " 이 명령어는 OP만 사용가능 합니다!");
            }
        } else {
            log.warning("이 명령어는 플레이어만 사용가능 합니다!");
        }
        return true;
    }
}
