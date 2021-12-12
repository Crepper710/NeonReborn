package io.github.crepper710.neon_reborn.filesystem.bytepacking;

import io.github.crepper710.neon_reborn.filesystem.bytepacking.datatypes.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BytePacks {

    public static final Map<Byte, Class<? extends BytePack>> ID_TO_CLASS;
    public static final Map<Class<? extends BytePack>, Byte> CLASS_TO_ID;

    static {
        HashMap<Byte, Class<? extends BytePack>> temp1 = new HashMap<>();
        temp1.put((byte) 0x00, BytePackByte.class);
        temp1.put((byte) 0x01, BytePackShort.class);
        temp1.put((byte) 0x02, BytePackInteger.class);
        temp1.put((byte) 0x03, BytePackLong.class);
        temp1.put((byte) 0x04, BytePackFloat.class);
        temp1.put((byte) 0x05, BytePackDouble.class);
        temp1.put((byte) 0x06, BytePackStringASCII.class);
        temp1.put((byte) 0x07, BytePackStringUTF8.class);
        temp1.put((byte) 0x08, BytePackStringUTF16.class);
        temp1.put((byte) 0xFE, BytePackList.class);
        temp1.put((byte) 0xFF, BytePackMap.class);
        HashMap<Class<? extends BytePack>, Byte> temp2 = new HashMap<>();
        temp1.forEach((i, clazz) -> temp2.put(clazz, i));
        ID_TO_CLASS = Collections.unmodifiableMap(temp1);
        CLASS_TO_ID = Collections.unmodifiableMap(temp2);
    }

}
