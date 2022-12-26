// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.List;
import java.util.Map;

// Referenced classes of package net.minecraft.src:
//            J_JsonNode, EnumJsonNodeType

final class J_JsonConstants extends J_JsonNode {

    static final J_JsonConstants field_27228_a;
    static final J_JsonConstants field_27227_b;
    static final J_JsonConstants field_27230_c;

    static {
        field_27228_a = new J_JsonConstants(EnumJsonNodeType.NULL);
        field_27227_b = new J_JsonConstants(EnumJsonNodeType.TRUE);
        field_27230_c = new J_JsonConstants(EnumJsonNodeType.FALSE);
    }

    private final EnumJsonNodeType field_27229_d;

    private J_JsonConstants(EnumJsonNodeType enumjsonnodetype) {
        field_27229_d = enumjsonnodetype;
    }

    public EnumJsonNodeType func_27218_a() {
        return field_27229_d;
    }

    public String func_27216_b() {
        throw new IllegalStateException("Attempt to get text on a JsonNode without text.");
    }

    public Map func_27214_c() {
        throw new IllegalStateException("Attempt to get fields on a JsonNode without fields.");
    }

    public List func_27215_d() {
        throw new IllegalStateException("Attempt to get elements on a JsonNode without elements.");
    }
}
