package dozer.module;

import lombok.Getter;

import java.awt.*;

@Getter
public enum ModuleCategory {

    COMBAT("Combat", new Color(205, 35, 31).getRGB()),
    MOVEMENT("Movement", new Color(152, 151, 26).getRGB()),
    RENDER("Render", new Color(214, 151, 35).getRGB()),
    PLAYER("Player", new Color(68, 130, 133).getRGB()),
    MISC("Misc", new Color(177, 97, 134).getRGB()),
    EXPLOIT("Exploit", new Color(40, 39, 48).getRGB());

    final String name;
    final int color;

    ModuleCategory(String name, int color) {
        this.name = name;
        this.color = color;
    }

}
