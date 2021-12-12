package io.github.crepper710.neon_reborn.filesystem.bytepacking.datatypes;

import io.github.crepper710.neon_reborn.filesystem.bytepacking.BytePack;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class BytePackShort extends BytePack<Short> {

    public BytePackShort() {}

    public BytePackShort(short s) {
        data = s;
    }

    @Override
    public void write(DataOutputStream outputStream) throws IOException {
        outputStream.writeShort(data);
    }

    @Override
    public void read(DataInputStream inputStream) throws IOException {
        data = inputStream.readShort();
    }

}
