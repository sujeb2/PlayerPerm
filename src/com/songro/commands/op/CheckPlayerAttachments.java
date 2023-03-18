package com.songro.commands.op;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CheckPlayerAttachments implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = (Player)sender;
        var target = Bukkit.getPlayer(args[0]);
        Logger log = Bukkit.getLogger();

        try {
            if(player.isOp()) {
                assert target != null;
                if (args[1].length() == 0) {
                    player.sendMessage(ChatColor.RED + "[PlayerPerms] 플레이어 이름이 없습니다.");
                    player.sendMessage(ChatColor.RED + "[PlayerPerms] 명령어 사용방법: /<command> <Player>");
                } else {
                    Set<String> permissions = target.getEffectivePermissions().stream()
                            .map(PermissionAttachmentInfo::getPermission)
                            .collect(Collectors.toSet());
                    player.sendMessage("attribute list: " + permissions);
                }
            } else {
                player.sendMessage(ChatColor.RED + "[PlayerPerms] 이 명령어는 OP만 사용 가능합니다!");
            }
        } catch (Exception e) {
            log.severe("오류가 발생했습니다");
            log.severe("오류 로그: " + e);
            log.severe("오류 코드: 0x90");
        }
        return true;
    }
}
