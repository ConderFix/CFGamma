package ru.conderfix.cfgamma.packets;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerUpdateLight;
import org.bukkit.entity.Player;
import ru.conderfix.cfgamma.Config;
import ru.conderfix.cfgamma.light.BestLightData;

public class SanctificationWorldPacket implements PacketListener {

    @Override
    public void onPacketSend(PacketSendEvent event) {
        if (event.getPacketType() != PacketType.Play.Server.UPDATE_LIGHT) return;

        final Player player = event.getPlayer();
        if (!(Config.isWorldInConfig(player.getWorld()))) return; 

        final WrapperPlayServerUpdateLight wrapper = new WrapperPlayServerUpdateLight(event);
        wrapper.setLightData(BestLightData.getLightData());
    }
}
