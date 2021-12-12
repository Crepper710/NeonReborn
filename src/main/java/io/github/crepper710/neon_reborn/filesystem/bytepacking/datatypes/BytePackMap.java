package io.github.crepper710.neon_reborn.filesystem.bytepacking.datatypes;

import io.github.crepper710.neon_reborn.filesystem.bytepacking.BytePack;
import io.github.crepper710.neon_reborn.filesystem.bytepacking.BytePacks;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class BytePackMap extends BytePack<HashMap<String, BytePack<?>>> {

    public BytePackMap() {
        data = new HashMap<>();
    }

    public BytePackMap(HashMap<String, BytePack<?>> m) {
        data = m;
    }

    @Override
    public void write(DataOutputStream outputStream) throws IOException {
        outputStream.writeInt(data.size());
        for (Map.Entry<String, BytePack<?>> entry : data.entrySet()) {
            String s = entry.getKey();
            BytePack<?> bp = entry.getValue();
            outputStream.writeByte(BytePacks.CLASS_TO_ID.get(bp.getClass()));
            byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
            outputStream.writeInt(bytes.length);
            outputStream.write(bytes);
            bp.write(outputStream);
        }
    }

    @Override
    public void read(DataInputStream inputStream) throws IOException {
        int length = inputStream.readInt();
        data = new HashMap<>();
        for (int i = 0; i < length; i++) {
            byte id = inputStream.readByte();
            BytePack<?> bp;
            try {
                bp = BytePacks.ID_TO_CLASS.get(id).getConstructor(new Class[0]).newInstance();
            } catch (Exception e) {
                throw new IllegalStateException("could not get a matching byte-pack class for id:" + id, e);
            }
            int sLength = inputStream.readInt();
            byte[] bytes = new byte[sLength];
            inputStream.read(bytes);
            String s = new String(bytes, StandardCharsets.UTF_8);
            bp.read(inputStream);
            data.put(s, bp);
        }
    }

    public BytePack get(String key) {
        return data.get(key);
    }

    public byte getAsByte(String key) {
        try {
            return (byte) (Byte) data.get(key).getData();
        } catch (Exception e) {
            throw new IllegalStateException("key:\"" + key + "\" is present in all entries ", e);
        }
    }

    public short getAsShort(String key) {
        try {
            return (short) (Short) data.get(key).getData();
        } catch (Exception e) {
            throw new IllegalStateException("", e);
        }
    }

    public int getAsInt(String key) {
        try {
            return (int) (Integer) data.get(key).getData();
        } catch (Exception e) {
            throw new IllegalStateException("", e);
        }
    }

    public long getAsLong(String key) {
        try {
            return (long) (Long) data.get(key).getData();
        } catch (Exception e) {
            throw new IllegalStateException("", e);
        }
    }

    public float getAsFloat(String key) {
        try {
            return (float) (Float) data.get(key).getData();
        } catch (Exception e) {
            throw new IllegalStateException("", e);
        }
    }

    public double getAsDouble(String key) {
        try {
            return (double) (Double) data.get(key).getData();
        } catch (Exception e) {
            throw new IllegalStateException("", e);
        }
    }

    public String getAsString(String key) {
        try {
            return (String) data.get(key).getData();
        } catch (Exception e) {
            throw new IllegalStateException("", e);
        }
    }

    public BytePackMap getAsBytePackMap(String key) {
        try {
            return (BytePackMap) data.get(key);
        } catch (Exception e) {
            throw new IllegalStateException("", e);
        }
    }

    public BytePackList getAsBytePackList(String key) {
        try {
            return (BytePackList) data.get(key);
        } catch (Exception e) {
            throw new IllegalStateException("", e);
        }
    }

    public void set(String key, BytePack<?> bytePack) {
        data.put(key, bytePack);
    }

    public void setByte(String key, byte b) {
        set(key, new BytePackByte(b));
    }

    public void setShort(String key, short s) {
        set(key, new BytePackShort(s));
    }

    public void setInteger(String key, int i) {
        set(key, new BytePackInteger(i));
    }

    public void setLong(String key, long l) {
        set(key, new BytePackLong(l));
    }

    public void setFloat(String key, float f) {
        set(key, new BytePackFloat(f));
    }

    public void setDouble(String key, double d) {
        set(key, new BytePackDouble(d));
    }

    public void setStringASCII(String key, String s) {
        set(key, new BytePackStringASCII(s));
    }

    public void setStringUTF8(String key, String s) {
        set(key, new BytePackStringUTF8(s));
    }

    public void setStringUTF16(String key, String s) {
        set(key, new BytePackStringUTF16(s));
    }

    @Override
    public String toString() {
        return data.entrySet().stream().map((e) -> "\"" + e.getKey() + "\" = " + e.getValue().toString()).collect(Collectors.joining(", ", "[", "]"));
    }

}
