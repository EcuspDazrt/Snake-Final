import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameBoardPanel extends JPanel {
    public GameBoard board;
    private int cellSize;

    //private Image snakeSprite;
    private Image foodSpriteDark;
    private Image foodSpriteLight;

    public GameBoardPanel(GameBoard board) {
        this.board = board;
        setPreferredSize(new Dimension(800, 800));

        try {
//        snakeSprite = ImageIO.read(getClass().getResource("/sprites/snake.png"));
            foodSpriteDark = ImageIO.read(getClass().getResource("/sprites/foodDark.png"));
            foodSpriteLight = ImageIO.read(getClass().getResource("/sprites/foodLight.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        cellSize = 800 / board.cols;
        for (int y = 0; y < board.rows; y++) {
            for (int x = 0; x < board.cols; x++) {
                Image sprite;
                Color customGreenLight = new Color(170, 215, 81);
                Color customGreenDark = new Color(162, 209, 73);
                Color customBlue = new Color(71, 117, 235);
                switch(board.grid[y][x]) {
                    case 0:
                        if ((x + y) % 2 == 0) {g.setColor(customGreenLight);
                        } else {g.setColor(customGreenDark); }
                        g.fillRect(x*cellSize, y*cellSize, cellSize, cellSize);
                        break;
                    case 1: g.setColor(customBlue); g.fillRect(x*cellSize, y*cellSize, cellSize, cellSize); break;
                    case 2: if ((x + y) % 2 == 0) { sprite = foodSpriteLight; } else { sprite = foodSpriteDark; }
                        g.drawImage(sprite, x * cellSize, y * cellSize, cellSize, cellSize, this);
                        break;
                }
            }
        }
    }
}
