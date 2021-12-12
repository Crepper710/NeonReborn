package io.github.crepper710.neon_reborn.filesystem.bytepacking.datatypes;

import io.github.crepper710.neon_reborn.filesystem.bytepacking.BytePack;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class BytePackByte extends BytePack<Byte> {

    public BytePackByte() {}

    public BytePackByte(byte b) {
        this.data = b;
    }

    @Override
    public void write(DataOutputStream outputStream) throws IOException {
        outputStream.writeByte(data);
    }

    @Override
    public void read(DataInputStream inputStream) throws IOException {
        data = inputStream.readByte();
    }

}
