package io.github.crepper710.neon_reborn.filesystem.bytepacking.datatypes;

import io.github.crepper710.neon_reborn.filesystem.bytepacking.BytePack;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class BytePackLong extends BytePack<Long> {

    public BytePackLong() {}

    public BytePackLong(long l) {
        data = l;
    }

    @Override
    public void write(DataOutputStream outputStream) throws IOException {
        outputStream.writeLong(data);
    }

    @Override
    public void read(DataInputStream inputStream) throws IOException {
        data = inputStream.readLong();
    }

}
