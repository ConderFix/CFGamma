package ru.conderfix.cfgamma;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import ru.conderfix.cfgamma.command.GammaCommand;
import ru.conderfix.cfgamma.light.BestLightData;
import ru.conderfix.cfgamma.packets.SanctificationWorldPacket;

public final class CFGamma extends JavaPlugin implements Listener {

    @Override
    public void onLoad() {
        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
        PacketEvents.getAPI().load();
    }

    @Override
    public void onEnable() {
        PacketEvents.getAPI().init();

        super.saveDefaultConfig();
        Config.load(super.getConfig());

        BestLightData.init();

        PacketEvents.getAPI().getEventManager().registerListener(
                new SanctificationWorldPacket(), PacketListenerPriority.HIGH);

        super.getCommand("gamma").setExecutor(new GammaCommand(this));
    }


    @Override
    public void onDisable() {
        PacketEvents.getAPI().terminate();
    }


}
