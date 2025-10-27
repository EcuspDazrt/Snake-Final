import java.awt.*;
import java.util.Random;

public class Food  {
    Random random = new Random();
    int[] currentFoodPos = new int[2];
    Point foodPointer = new Point();

    public Food(GameBoard board) {
        currentFoodPos = new int[]{8, 8};
        foodPointer.setLocation(8, 8);
        board.grid[8][8] = 2;
    }

    public void updateFood(GameBoard board) {
        // generating new coordinates
        int yCoord = random.nextInt(board.rows);
        int xCoord = random.nextInt(board.cols);
        int[] nextFoodPos = {xCoord, yCoord};
        // put position into grid, recalculate if same or on snake (haven't done snake logic yet)
        if (board.grid[xCoord][yCoord] != 1) {
            currentFoodPos = nextFoodPos;
            foodPointer.setLocation(currentFoodPos[1], currentFoodPos[0]);
            board.grid[currentFoodPos[0]][currentFoodPos[1]] = 2;
        } else {
            updateFood(board);
        }
    }

    public Point getPosition() {
        return foodPointer;
    }
}