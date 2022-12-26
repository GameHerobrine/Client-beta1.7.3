// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

// Referenced classes of package net.minecraft.src:
//            NBTBase

public class NBTTagDouble extends NBTBase {

    public double doubleValue;

    public NBTTagDouble() {
    }

    public NBTTagDouble(double d) {
        doubleValue = d;
    }

    void writeTagContents(DataOutput dataoutput)
            throws IOException {
        dataoutput.writeDouble(doubleValue);
    }

    void readTagContents(DataInput datainput)
            throws IOException {
        doubleValue = datainput.readDouble();
    }

    public byte getType() {
        return 6;
    }

    public String toString() {
        return (new StringBuilder()).append("").append(doubleValue).toString();
    }
}
