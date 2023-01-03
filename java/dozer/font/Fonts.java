package dozer.font;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Fonts {

    private static final Map<String, TTFFontRenderer> fontMap = new HashMap<>();

    public static TTFFontRenderer getFont(String type, int size) {
        if (!fontMap.containsKey(type + size)) {
            try {
                fontMap.put(type + size, new TTFFontRenderer(
                        Font.createFont(0, (Objects.requireNonNull(Fonts.class.getClassLoader().getResourceAsStream(type + ".ttf"))))
                                .deriveFont(Font.PLAIN, size)));
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
        }
        return fontMap.get(type + size);
    }


}