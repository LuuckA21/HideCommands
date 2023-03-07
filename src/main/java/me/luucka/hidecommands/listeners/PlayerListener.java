package me.luucka.hidecommands.listeners;

import lombok.RequiredArgsConstructor;
import me.luucka.hidecommands.Settings;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class PlayerListener implements Listener {

    private final Settings settings;

    @EventHandler
    public void onCommandSuggestion(final PlayerCommandSendEvent event) {
        final Player player = event.getPlayer();
        if (player.hasPermission("hidecommands.admin")) return;

        // Clear all commands
        event.getCommands().clear();

        // Add 'default' commands section
        event.getCommands().addAll(settings.getDefaultCommands());

        // Add any group commands section
        for (Map.Entry<String, List<String>> entry : settings.getGroupsCommands().entrySet()) {
            if (player.hasPermission("hidecommands.group." + entry.getKey())) {
                event.getCommands().addAll(entry.getValue());
            }
        }
    }
}
