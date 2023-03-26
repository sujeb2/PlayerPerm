package com.songro.commands.op;

import com.songro.util.BanUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.logging.Logger;

import static com.songro.Main.plugin;

public class Ban implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        boolean isOn = plugin.getCustomConfig().getBoolean("experimental.bangui");
        Player p = (Player)sender;

        if(isOn) {
            Logger log = Bukkit.getLogger();

            if (sender instanceof Player player) {
                try {
                    ArrayList<Player> list = new ArrayList<>(player.getServer().getOnlinePlayers());

                    Inventory bangui = Bukkit.createInventory(player, 45, ChatColor.BOLD + "list");
                    try {
                        BanUtil.openBanGUI(player);
                    } catch (Exception e) {
                        player.sendMessage(ChatColor.RED + "[PlayerPerms] 오류가 발생했습니다");
                        player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 로그: " + e + "\n[PlayerPerms] 오류 코드: 0x22");
                        return false;
                    }
                    player.openInventory(bangui);
                } catch (Exception e) {
                    player.sendMessage(ChatColor.RED + "[PlayerPerms] 오류가 발생했습니다");
                    player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 로그: " + e + "\n[PlayerPerms] 오류 코드: 0x20");
                    return false;
                }

            } else {
                log.warning("[PlayerPerms] 이 명령어는 플레이어만 사용할수 있습니다.");
            }

            return true;
        } else {
            p.sendMessage(ChatColor.RED + "[PlayerPerms] 현재 설정 파일에서 이 기능이 꺼져있습니다.");
        }
        return true;
    }
}
