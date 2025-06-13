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
public class InfoCommand implements SubCommand {
    private final Plugin plugin;

    @Getter
    private final List<String> aliases = List.of("info");

    @Getter
    private final Permission permission = new Permission("cfgamma.info");

    @Override
    public void onCommand(@NonNull CommandSender sender, @NotNull @NonNull String[] args) {
        sender.sendMessage("Gamma now: " + Config.lightLevel);
        sender.sendMessage("Light level to byte: "+ BestLightData.getLightLevelByte());
    }
}
