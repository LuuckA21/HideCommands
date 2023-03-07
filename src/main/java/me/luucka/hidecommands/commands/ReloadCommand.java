package me.luucka.hidecommands.commands;

import me.luucka.hidecommands.HideCommandsPlugin;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;


public class ReloadCommand extends BaseCommand {

    private final HideCommandsPlugin plugin;

    public ReloadCommand(final HideCommandsPlugin plugin) {
        super("hidecommands", "HideCommands reload", "hidecommands.admin");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSource sender, String[] args) throws Exception {
        if (!testPermissionSilent(sender.getSender())) throw new Exception(plugin.getSettings().noPermission());
        plugin.reload();
        sender.sendMessage(plugin.getSettings().reloaded());
        plugin.getServer().getOnlinePlayers().forEach(Player::updateCommands);
    }

    @Override
    public List<String> onTabComplete(CommandSource sender, String[] args) {
        return Collections.emptyList();
    }
}
