public class SnakeSpeed {
    static int speed;
    public SnakeSpeed(int speed) {
        this.speed = speed;
    }
    public static void set(int num) {
        speed = num;
    }
    public static int get() {
        return speed;
    }
}
