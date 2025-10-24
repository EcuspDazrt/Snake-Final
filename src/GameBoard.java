public class GameBoard {
    // initializing the gameboard, can change size here
    int rows;
    int cols;
    int[][] grid;
    public GameBoard(int rows) {
        this.rows = rows;
        this.cols = rows;
        grid = new int[this.rows][this.cols];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                grid[i][j] = 0;
            }
        }
    }
}