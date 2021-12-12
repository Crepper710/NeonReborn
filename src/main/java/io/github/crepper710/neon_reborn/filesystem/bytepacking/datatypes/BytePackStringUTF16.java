package io.github.crepper710.neon_reborn.filesystem.bytepacking.datatypes;

import io.github.crepper710.neon_reborn.filesystem.bytepacking.BytePack;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class BytePackStringUTF16 extends BytePack<String> {

    public BytePackStringUTF16() {}

    public BytePackStringUTF16(String s) {
        data = s;
    }

    @Override
    public void write(DataOutputStream outputStream) throws IOException {
        byte[] bytes = data.getBytes(StandardCharsets.UTF_16);
        outputStream.writeInt(bytes.length);
        outputStream.write(bytes);
    }

    @Override
    public void read(DataInputStream inputStream) throws IOException {
        int length = inputStream.readInt();
        byte[] bytes = new byte[length];
        inputStream.read(bytes);
        data = new String(bytes, StandardCharsets.UTF_16);
    }

}