package main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class IconReader {

    public static final BufferedImage CALENDAR_ICON;

    static {
        try {
            CALENDAR_ICON = ImageIO.read(Objects.requireNonNull(IconReader.class.getResource("/calendar.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
