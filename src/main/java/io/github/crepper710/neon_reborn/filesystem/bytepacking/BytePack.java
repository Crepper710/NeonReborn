package io.github.crepper710.neon_reborn.filesystem.bytepacking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class BytePack<T> {

    protected T data;

    public BytePack(T data) {
        this.data = data;
    }

    public BytePack() {
        this(null);
    }

    public abstract void write(DataOutputStream outputStream) throws IOException;

    public abstract void read(DataInputStream inputStream) throws IOException;

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return data.toString();
    }

}
