package io.github.crepper710.neon_reborn.filesystem.bytepacking;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class BytePackingOutputStream extends DataOutputStream {

    public BytePackingOutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    public void writeBytePack(BytePack<?> bytePack) throws IOException {
        Class<? extends BytePack> bytePackClass = bytePack.getClass();
        if (!BytePacks.CLASS_TO_ID.containsKey(bytePackClass)) {
            throw new IllegalArgumentException(bytePackClass.getName() + " is not registered in ");
        }
        super.writeByte(BytePacks.CLASS_TO_ID.get(bytePackClass));
        bytePack.write(this);
    }

}
