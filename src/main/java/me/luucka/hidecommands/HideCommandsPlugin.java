package me.luucka.hidecommands;

import lombok.Getter;
import me.luucka.hidecommands.commands.ReloadCommand;
import me.luucka.hidecommands.listeners.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class HideCommandsPlugin extends JavaPlugin {

    @Getter
    private Settings settings;

    @Override
    public void onEnable() {
        settings = new Settings(this);
        new ReloadCommand(this);
        getServer().getPluginManager().registerEvents(new PlayerListener(settings), this);
    }

    public void reload() {
        settings.reloadConfig();
    }
}
