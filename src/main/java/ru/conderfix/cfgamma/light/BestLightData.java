package ru.conderfix.cfgamma.light;

import com.github.retrooper.packetevents.protocol.world.chunk.LightData;
import lombok.Getter;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.BitSet;

@UtilityClass
public class BestLightData {

    private final int LVL_LIGHT = 18;
    private final byte FULL_LIGHT = (byte) 0xFF;
    private final int SECTION_BYTES = 2048;

    @Getter
    private LightData lightData;

    public static void init() {
        lightData = new LightData();

        lightData.setTrustEdges(true);

        final BitSet fullMask = new BitSet(LVL_LIGHT);
        final byte[][] fullArray = new byte[LVL_LIGHT][];
        final byte[] section = new byte[SECTION_BYTES];
        Arrays.fill(section, FULL_LIGHT);

        for (int i = 0; i < LVL_LIGHT; i++) {
            fullMask.set(i);
            fullArray[i] = section.clone();
        }

        lightData.setSkyLightMask(fullMask);
        lightData.setBlockLightMask(fullMask);
        lightData.setEmptySkyLightMask(new BitSet(LVL_LIGHT));
        lightData.setEmptyBlockLightMask(new BitSet(LVL_LIGHT));
        lightData.setSkyLightArray(fullArray);
        lightData.setBlockLightArray(fullArray);
        lightData.setSkyLightCount(LVL_LIGHT);
        lightData.setBlockLightCount(LVL_LIGHT);
    }
}
