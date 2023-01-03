package dozer.systems.module;

import lombok.Getter;

import java.awt.*;

@Getter
public enum ModuleCategory {

    COMBAT("Combat", new Color(205, 35, 31)),
    MOVEMENT("Movement", new Color(152, 151, 26)),
    RENDER("Render", new Color(214, 151, 35)),
    PLAYER("Player", new Color(68, 130, 133)),
    MISC("Misc", new Color(177, 97, 134)),
    EXPLOIT("Exploit", new Color(40, 39, 48)),
    CLIENT("Client", new Color(235, 219, 178));

    final String name;
    final Color color;

    ModuleCategory(String name, Color color) {
        this.name = name;
        this.color = color;
    }

}
