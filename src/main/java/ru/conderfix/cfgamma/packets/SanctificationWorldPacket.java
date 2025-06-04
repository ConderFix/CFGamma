package ru.conderfix.cfgamma.packets;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.world.chunk.LightData;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerUpdateLight;
import org.bukkit.entity.Player;
import ru.conderfix.cfgamma.Config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

public class SanctificationWorldPacket implements PacketListener {

    private static final int LVL_LIGHT = 18;
    private static final byte FULL_LIGHT = (byte) 0xFF;
    private static final int SECTION_BYTES = 2048;

    @Override
    public void onPacketSend(PacketSendEvent event) {
        if (!(event.getUser() instanceof Player)) return;
        final Player player = (Player) event.getUser(); // xz

        if (!(Config.isWorldInConfig(player.getWorld()))) return; // xz
        if (event.getPacketType() != PacketType.Play.Server.UPDATE_LIGHT) return;

        final WrapperPlayServerUpdateLight wrapper = new WrapperPlayServerUpdateLight(event);
        final LightData data = wrapper.getLightData().clone();
        data.setTrustEdges(true);

        final BitSet fullMask = new BitSet(LVL_LIGHT);
        final byte[][] fullArray = new byte[LVL_LIGHT][];
        final byte[] section = new byte[SECTION_BYTES];
        Arrays.fill(section, FULL_LIGHT);

        for (int i = 0; i < LVL_LIGHT; i++) {
            fullMask.set(i);
            fullArray[i] = section.clone();
        }

        data.setSkyLightMask(fullMask);
        data.setBlockLightMask(fullMask);
        data.setEmptySkyLightMask(new BitSet(LVL_LIGHT));
        data.setEmptyBlockLightMask(new BitSet(LVL_LIGHT));
        data.setSkyLightArray(fullArray);
        data.setBlockLightArray(fullArray);
        data.setSkyLightCount(LVL_LIGHT);
        data.setBlockLightCount(LVL_LIGHT);

        wrapper.setLightData(data);
    }



}
