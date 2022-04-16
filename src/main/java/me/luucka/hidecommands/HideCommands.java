package me.luucka.hidecommands;

import lombok.Getter;
import me.luucka.hidecommands.commands.CmdReload;
import me.luucka.hidecommands.config.Settings;
import me.luucka.hidecommands.listeners.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Map;

public final class HideCommands extends JavaPlugin {

    @Getter
    private Settings settings;

    @Override
    public void onEnable() {

        this.settings = new Settings(getDataFolder());

        getCommand("hidecommands").setExecutor(new CmdReload(this));
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
    }

    public List<String> getDefaultCommands() {
        return settings.getDefaultCommands();
    }

    public Map<String, List<String>> getGroupsCommands() {
        return settings.getGroupsCommands();
    }

    public void reload() {
        settings.reloadConfig();
    }
}
