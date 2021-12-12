package io.github.crepper710.neon_reborn.filesystem.bytepacking.datatypes;

import io.github.crepper710.neon_reborn.filesystem.bytepacking.BytePack;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class BytePackDouble extends BytePack<Double> {

    public BytePackDouble() {}

    public BytePackDouble(double d) {
        data = d;
    }

    @Override
    public void write(DataOutputStream outputStream) throws IOException {
        outputStream.writeDouble(data);
    }

    @Override
    public void read(DataInputStream inputStream) throws IOException {
        data = inputStream.readDouble();
    }

}
