package ru.conderfix.cfgamma.command.list;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import ru.conderfix.cfgamma.Config;
import ru.conderfix.cfgamma.command.SubCommand;
import ru.conderfix.cfgamma.light.BestLightData;

import java.util.List;

@RequiredArgsConstructor
public class SetCommand implements SubCommand {

    private final Plugin plugin;

    @Getter
    private final List<String> aliases = List.of("set");

    @Getter
    private final Permission permission = new Permission("cfgamma.set");

    @Override
    public void onCommand(@NonNull CommandSender sender, @NotNull @NonNull String[] args) {
        if (args.length <= 1) {
            sender.sendMessage("/gamma set <1-18>");
            return;
        }

        int lightLevel;

        try {
            lightLevel = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            sender.sendMessage(ChatColor.RED + "Write a specific number up to 18");
            return;
        }

        Config.setLightLevel(lightLevel);
        plugin.saveConfig();
        BestLightData.init();

        sender.sendMessage(ChatColor.GREEN+"Light changed!");
    }
}
