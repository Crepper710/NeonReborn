package io.github.crepper710.neon_reborn.filesystem.bytepacking.datatypes;

import io.github.crepper710.neon_reborn.filesystem.bytepacking.BytePack;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class BytePackFloat extends BytePack<Float> {

    public BytePackFloat() {}

    public BytePackFloat(float f) {
        data = f;
    }

    @Override
    public void write(DataOutputStream outputStream) throws IOException {
        outputStream.writeFloat(data);
    }

    @Override
    public void read(DataInputStream inputStream) throws IOException {
        data = inputStream.readFloat();
    }

}
