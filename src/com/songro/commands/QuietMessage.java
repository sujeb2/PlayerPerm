package com.songro.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class QuietMessage implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = (Player)sender;
        Player target = Bukkit.getPlayer(args[1]);
        String msg = args[2];

        try {
            if(target == null) {
                player.sendMessage(ChatColor.RED + "[PlayerPerms] " + target.getDisplayName() + "이라는 플레이어가 존재하지 않거나, 서버에 들어온적이 없습니다.");
            }
            if (args.length <= 0) {
                player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 플레이어 이름이나, 메세지가 없습니다.");
                player.sendMessage(ChatColor.GREEN + "[PlayerPerms] 명령어 사용 방법: /<commands> <Player> <Message>");
            }
            try {
                if(target.equals(player)) {
                    player.sendMessage( ChatColor.YELLOW + "[PlayerPerms] 자기 자신에게 귓속말을 보낼수 없습니다.");
                }

                for (int i = 1; i < args.length; i++)
                    msg.indent(Integer.parseInt(args[i])).indent(Integer.parseInt(" "));
                // 정상적으로 명령어를 쳤을때
                sender.sendMessage("§aYou §7→ §f" + target.getName() + "§7: §f" + msg);
                String senderName;
                if (sender instanceof Player p) senderName = p.getName();
                else senderName = "§d§lCONSOLE";
                target.sendMessage(senderName + " §7→ §aYou§7: §f" + msg);
                Bukkit.getConsoleSender().sendMessage(senderName + " §7→  §e" + target.getName() + "§7: §f" + msg);
            } catch (IndexOutOfBoundsException e2) {
                player.sendMessage(ChatColor.RED + "[PlayerPerms] 오류가 발생했습니다");
                player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 로그: " + e2 + "\n[PlayerPerms] 오류코드: 0x19");
            }
        } catch (Exception e) {
            player.sendMessage(ChatColor.RED + "[PlayerPerms] 오류가 발생했습니다");
            player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 로그: " + e + "\n[PlayerPerms] 오류코드: 0x18");
        }

        return false;
    }
}
