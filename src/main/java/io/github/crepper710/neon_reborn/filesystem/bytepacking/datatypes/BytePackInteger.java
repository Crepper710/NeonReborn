package io.github.crepper710.neon_reborn.filesystem.bytepacking.datatypes;

import io.github.crepper710.neon_reborn.filesystem.bytepacking.BytePack;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class BytePackInteger extends BytePack<Integer> {

    public BytePackInteger() {}

    public BytePackInteger(int i) {
        data = i;
    }

    @Override
    public void write(DataOutputStream outputStream) throws IOException {
        outputStream.writeInt(data);
    }

    @Override
    public void read(DataInputStream inputStream) throws IOException {
        data = inputStream.readInt();
    }

}
