package ru.conderfix.cfgamma.light;

import com.github.retrooper.packetevents.protocol.world.chunk.LightData;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import ru.conderfix.cfgamma.Config;

import java.util.Arrays;
import java.util.BitSet;

@UtilityClass
public class BestLightData {

    private final int SECTION_BYTES = 2048;

    @Getter
    private final byte lightLevelByte = getLightByteForLevel(Config.lightLevel);

    @Getter
    private LightData lightData;

    public void init() {
        lightData = new LightData();

        lightData.setTrustEdges(true);

        final BitSet fullMask = new BitSet(Config.lightLevel);
        fullMask.set(0, Config.lightLevel);

        final byte[] section = new byte[SECTION_BYTES];
        Arrays.fill(section, lightLevelByte);

        final byte[][] fullArray = new byte[Config.lightLevel][];
        Arrays.fill(fullArray, section);

        lightData.setSkyLightMask(fullMask);
        lightData.setBlockLightMask(fullMask);
        lightData.setEmptySkyLightMask(new BitSet(Config.lightLevel));
        lightData.setEmptyBlockLightMask(new BitSet(Config.lightLevel));
        lightData.setSkyLightArray(fullArray);
        lightData.setBlockLightArray(fullArray);
        lightData.setSkyLightCount(Config.lightLevel);
        lightData.setBlockLightCount(Config.lightLevel);
    }

    private byte getLightByteForLevel(int level) {
        final int halfByte = Math.max(0, Math.min(15, level));
        return (byte)((halfByte << 4) | halfByte);
    }
}
