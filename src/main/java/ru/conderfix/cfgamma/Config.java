package ru.conderfix.cfgamma;

import lombok.experimental.UtilityClass;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashSet;
import java.util.Set;

@UtilityClass
public class Config {

    public void load(FileConfiguration config) {
        WORLDS.clear();
        WORLDS.addAll(config.getStringList("worlds"));
    }

    private Set<String> WORLDS = new HashSet<>();

    public boolean isWorldInConfig(World world) {
        return WORLDS.contains(world);
    }
}
