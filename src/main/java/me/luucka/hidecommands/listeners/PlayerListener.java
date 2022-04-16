package me.luucka.hidecommands.listeners;

import com.destroystokyo.paper.event.server.AsyncTabCompleteEvent;
import lombok.RequiredArgsConstructor;
import me.luucka.hidecommands.HideCommands;
import me.luucka.hidecommands.Perms;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class PlayerListener implements Listener {

    private final HideCommands PLUGIN;

    @EventHandler
    public void onCmdSuggestion(PlayerCommandSendEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission(Perms.ADMIN)) return;

        // Clear all commands
        event.getCommands().clear();

        // Add 'default' commands section
        event.getCommands().addAll(PLUGIN.getDefaultCommands());

        // Add any group commands section
        for (Map.Entry<String, List<String>> entry : PLUGIN.getGroupsCommands().entrySet()) {
            if (player.hasPermission(Perms.GROUP + entry.getKey())) {
                event.getCommands().addAll(entry.getValue());
            }
        }
    }

    /*@EventHandler
    public void onTab(AsyncTabCompleteEvent event) {
        if (!(event.getSender() instanceof Player)) return;

        String buffer = event.getBuffer();
        PLUGIN.getLogger().severe(buffer.substring(1, buffer.indexOf(" ")));

        List<String> compl = event.getCompletions();
        compl.clear();
        compl.add("ciao");
        event.setCompletions(compl);
    }*/

}
