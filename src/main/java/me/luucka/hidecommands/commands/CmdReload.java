package me.luucka.hidecommands.commands;

import lombok.RequiredArgsConstructor;
import me.luucka.hidecommands.HideCommands;
import me.luucka.hidecommands.Perms;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class CmdReload implements CommandExecutor {

    private final HideCommands PLUGIN;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission(Perms.ADMIN)) {
            sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>You do not have permission!"));
            return true;
        }
        PLUGIN.reload();
        sender.sendMessage(MiniMessage.miniMessage().deserialize("<aqua>HideCommands <green>reloaded!"));
        PLUGIN.getServer().getOnlinePlayers().forEach(Player::updateCommands);
        return true;
    }
}
