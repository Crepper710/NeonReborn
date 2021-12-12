package io.github.crepper710.neon_reborn.filesystem.bytepacking.datatypes;

import io.github.crepper710.neon_reborn.filesystem.bytepacking.BytePack;
import io.github.crepper710.neon_reborn.filesystem.bytepacking.BytePacks;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BytePackList extends BytePack<List<BytePack<?>>> {

    public BytePackList() {
        data = new ArrayList<>();
    }

    public BytePackList(List<BytePack<?>> l) {
        data = l;
    }

    @Override
    public void write(DataOutputStream outputStream) throws IOException {
        outputStream.writeInt(data.size());
        for (BytePack<?> bp : data) {
            outputStream.writeByte(BytePacks.CLASS_TO_ID.get(bp.getClass()));
            bp.write(outputStream);
        }
    }

    @Override
    public void read(DataInputStream inputStream) throws IOException {
        int length = inputStream.readInt();
        data = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            byte id = inputStream.readByte();
            BytePack<?> bp;
            try {
                bp = BytePacks.ID_TO_CLASS.get(id).getConstructor(new Class[0]).newInstance();
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                throw new IllegalStateException("could not get a matching byte-pack class for id:" + id, e);
            }
            bp.read(inputStream);
            data.add(bp);
        }
    }

    @Override
    public String toString() {
        return data.stream().map(BytePack::toString).collect(Collectors.joining(", ", "[", "]"));
    }

}
