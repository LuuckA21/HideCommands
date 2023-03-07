package me.luucka.hidecommands.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public final class MMColor {

    private MMColor() {

    }

    public static Component toComponent(final String input) {
        return MiniMessage.miniMessage().deserialize(input);
    }

}
