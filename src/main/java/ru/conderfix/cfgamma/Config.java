package ru.conderfix.cfgamma;

import lombok.AllArgsConstructor;
import lombok.experimental.UtilityClass;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashSet;
import java.util.Set;

@UtilityClass
public class Config {

    private FileConfiguration config;

    public void load(FileConfiguration file) {
        config = file;

        WORLDS.clear();

        WORLDS.addAll(config.getStringList("worlds"));
        lightLevel = config.getInt("light-level");
    }

    private final Set<String> WORLDS = new HashSet<>();
    public int lightLevel;

    public boolean isWorldInConfig(World world) {
        return WORLDS.contains(world.getName());
    }

    public void setLightLevel(int level) {
        lightLevel = level;
        config.set("light-level", level);
    }
}
