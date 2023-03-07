package me.luucka.hidecommands;

import lombok.Getter;
import me.luucka.hidecommands.config.BaseConfiguration;

import java.io.File;
import java.util.*;

public class Settings {

    private final BaseConfiguration config;

    private String prefix;

    private String noPermission;

    private String reloaded;

    public String noPermission() {
        return noPermission.replace("{PREFIX}", prefix);
    }

    public String reloaded() {
        return reloaded.replace("{PREFIX}", prefix);
    }

    @Getter
    private final List<String> defaultCommands = new ArrayList<>();

    @Getter
    private final Map<String, List<String>> groupsCommands = new HashMap<>();

    public Settings(final HideCommandsPlugin plugin) {
        this.config = new BaseConfiguration(new File(plugin.getDataFolder(), "config.yml"), "/config.yml");
        reloadConfig();
    }

    public void reloadConfig() {
        config.load();
        prefix = config.getString("prefix", "");
        noPermission = config.getString("no-permission", "");
        reloaded = config.getString("reload", "");
        defaultCommands.clear();
        defaultCommands.addAll(config.getList("default", String.class));
        groupsCommands.clear();
        groupsCommands.putAll(_getGroupsCommands());
    }

    private Map<String, List<String>> _getGroupsCommands() {
        Map<String, List<String>> groupsCommands = new HashMap<>();
        final Set<String> keys = config.getKeys(config.getSection("groups"));
        for (final String k : keys) {
            groupsCommands.put(k, config.getList("groups." + k + ".commands", String.class));
        }
        return groupsCommands;
    }
}
