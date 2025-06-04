package ru.conderfix.cfgamma;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import com.github.retrooper.packetevents.protocol.world.chunk.LightData;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerUpdateLight;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;
import ru.conderfix.cfgamma.command.GammaCommand;
import ru.conderfix.cfgamma.packets.SanctificationWorldPacket;

import java.util.BitSet;

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

        PacketEvents.getAPI().getEventManager().registerListener(
                new SanctificationWorldPacket(), PacketListenerPriority.HIGH);

        super.getCommand("gamma").setExecutor(new GammaCommand(this));
    }

    @Override
    public void onDisable() {
        PacketEvents.getAPI().terminate();
    }


}
