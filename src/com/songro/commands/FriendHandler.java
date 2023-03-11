package com.songro.commands;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.*;
import java.util.concurrent.CompletableFuture;


public class FriendHandler implements CommandExecutor {

    LuckPerms api = LuckPermsProvider.get();
    private boolean isAdmin = false;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        try {
            if (sender instanceof Player) {
                RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
                if (provider != null) {
                    LuckPerms api = provider.getProvider();

                }

                Player player = (Player) sender;
                User user = api.getPlayerAdapter(Player.class).getUser(player);
                if(isAdmin == true) {
                    player.sendMessage(ChatColor.RED + ": " + player.getName());
                } else {
                    player.sendMessage(ChatColor.GREEN + ": " + player.getName());
                }
                return true;
            } else if (sender instanceof ConsoleCommandSender) {
                sender.sendMessage(ChatColor.RED + "[PlayerPerms] 이 명령어는 콘솔에서 사용할수 없습니다!");
                return false;
            }

            return false;
        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "[PlayerPerms] 오류가 발생했습니다!");
            sender.sendMessage(ChatColor.GRAY + "[PlayerPerms] 오류 로그: " + e + "\n[PlayerPerms] 오류 코드: 0x12");
        }
        return false;
    }

    public CompletableFuture<Boolean> isAdmin(UUID who) {
        return api.getUserManager().loadUser(who)
                .thenApplyAsync(user -> {
                    Collection<Group> inheritedGroups = user.getInheritedGroups(user.getQueryOptions());
                    return inheritedGroups.stream().anyMatch(g -> g.getName().equals("admin"));
                });
    }

    public void informIfAdmin(CommandSender sender, UUID who) {
        isAdmin(who).thenAcceptAsync(result -> {
            if (result) {
                isAdmin = true;
            } else {
                isAdmin = false;
            }
        });
    }

}
