import java.awt.*;

public class SnakeColor {
    private static Color color;

        public SnakeColor(Color color) {
            SnakeColor.color = color;
        }
        public static void set(Color color) {
            SnakeColor.color = color;
        }
        public static Color get() {
            return SnakeColor.color;
        }
    }

