package com.songro.listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerChatColorGUIListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player)e.getWhoClicked();
        ItemStack clicked = e.getCurrentItem();
        Inventory inv2 = e.getInventory();
        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.BOLD + "색깔 선택")) {
            try {
                try {
                    // op red
                    assert clicked != null;
                    if (clicked.getType().equals(inv2.getItem(0))) {
                        if (player.hasPermission("def.op") || player.hasPermission("perks.op")) {
                            player.setDisplayName(ChatColor.DARK_RED + "[STAFF] " + player.getName());
                            player.sendMessage("oped");
                        }
                        e.setCancelled(true);
                    }
                    // normal red
                    if (clicked.getType().equals(inv2.getItem(1))) {
                        if (player.hasPermission("def.op") || player.hasPermission("perks.op") || player.hasPermission("perks.plus") || player.hasPermission("perks.pp") || player.hasPermission("perks.ultra")) {
                            if (player.hasPermission("def.op") || player.hasPermission("perks.op")) {
                                player.setDisplayName(ChatColor.RED + "[STAFF] " + player.getName());
                                player.sendMessage("red");
                            } else if (player.hasPermission("perks.plus")) {
                                player.setDisplayName(ChatColor.RED + "[FRUIT+] " + player.getName());
                                player.sendMessage("red2");
                            } else if (player.hasPermission("perks.pp")) {
                                player.setDisplayName(ChatColor.RED + "[FRUIT++] " + player.getName());
                                player.sendMessage("red3");
                            } else if (player.hasPermission("perks.ultra")) {
                                player.setDisplayName(ChatColor.RED + "[FRUIT U] " + player.getName());
                                player.sendMessage("red4");
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "[PlayerPerms] 이 색상을 사용할려면, 'Fruit+' 보다 같거나 높은 권한이 있어야합니다!");
                        }
                        e.setCancelled(true);
                    }
                    // normal red
                    if (clicked.getType().equals(inv2.getItem(2))) {
                        if (player.hasPermission("def.op") || player.hasPermission("perks.op") || player.hasPermission("perks.plus") || player.hasPermission("perks.pp") || player.hasPermission("perks.ultra")) {
                            if (player.hasPermission("def.op") || player.hasPermission("perks.op")) {
                                player.setDisplayName(ChatColor.GOLD + "[STAFF] " + player.getName());
                                player.sendMessage("gold1");
                            } else if (player.hasPermission("perks.plus")) {
                                player.setDisplayName(ChatColor.GOLD + "[FRUIT+] " + player.getName());
                                player.sendMessage("gold2");
                            } else if (player.hasPermission("perks.pp")) {
                                player.setDisplayName(ChatColor.GOLD + "[FRUIT++] " + player.getName());
                                player.sendMessage("gold3");
                            } else if (player.hasPermission("perks.ultra")) {
                                player.setDisplayName(ChatColor.GOLD + "[FRUIT U] " + player.getName());
                                player.sendMessage("gold4");
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "[PlayerPerms] 이 색상을 사용할려면, 'Fruit+' 보다 같거나 높은 권한이 있어야합니다!");
                        }
                        e.setCancelled(true);
                    }
                    // null
                    if (clicked.getType() == null) {
                        player.sendMessage("null");
                        e.setCancelled(true);
                    }

                    } catch (NullPointerException npe) {
                    player.sendMessage("error" + npe);
                }
                e.setCancelled(true);
            } catch (Exception ex) {
                player.sendMessage("error2");
            }
        }
    }

}
