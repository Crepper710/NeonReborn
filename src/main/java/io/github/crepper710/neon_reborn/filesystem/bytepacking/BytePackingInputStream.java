package io.github.crepper710.neon_reborn.filesystem.bytepacking;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BytePackingInputStream extends DataInputStream {

    public BytePackingInputStream(InputStream inputStream) {
        super(inputStream);
    }

    public BytePack readBytePack() throws IOException {
        byte id = super.readByte();
        BytePack bp;
        try {
            bp = BytePacks.ID_TO_CLASS.get(id).getConstructor(new Class[0]).newInstance();
        } catch (Exception e) {
            throw new IllegalStateException("could not get a matching byte-pack class for id: " + id, e);
        }
        bp.read(this);
        return bp;
    }

}
