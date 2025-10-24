public class BoardSize {
    static int boardSize;
    public BoardSize(int boardSize) { this.boardSize = boardSize; }
    public static void set(int num) { boardSize = num; }
    public static int get() { return boardSize; }
}