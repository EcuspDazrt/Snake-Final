import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameBoardPanel extends JPanel {
    public GameBoard board;
    private SnakeGame game;
    private Snake snake;
    private int cellSize;

    //private Image snakeSprite;
    private Image foodSpriteDark;
    private Image foodSpriteLight;

    public GameBoardPanel(GameBoard board, SnakeGame game, Snake snake) {
        this.board = board;
        this.game = game;
        this.snake = snake;
        setPreferredSize(new Dimension(800, 800));

        try {
//        snakeSprite = ImageIO.read(getClass().getResource("/sprites/snake.png"));
            foodSpriteDark = ImageIO.read(getClass().getResource("/sprites/foodDark.png"));
            foodSpriteLight = ImageIO.read(getClass().getResource("/sprites/foodLight.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setBoard(GameBoard board) {
        this.board = board;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
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
                switch (board.grid[y][x]) {
                    case 0, 1:
                        if ((x + y) % 2 == 0) {
                            g.setColor(customGreenLight);
                        } else {
                            g.setColor(customGreenDark);
                        }
                        g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                        break;
                    case 2:
                        if ((x + y) % 2 == 0) {
                            sprite = foodSpriteLight;
                        } else {
                            sprite = foodSpriteDark;
                        }
                        g.drawImage(sprite, x * cellSize, y * cellSize, cellSize, cellSize, this);
                        break;
                }
            }
        }

        if (snake == null || game == null) return;

        long now = System.nanoTime();
        double t = Math.min(1.0, (now - game.lastUpdateTime) / (double) game.tickDuration);

        var current = snake.getBody();
        var prev = snake.getPrevBody();

        // if first frame, prevBody may be empty; skip interpolation
        if (prev.size() != current.size()) t = 1.0;

        for (int i = 0; i < current.size(); i++) {
            Point c = current.get(i);
            Point p = (i < prev.size()) ? prev.get(i) : c;

            double x = p.x + (c.x - p.x) * t;
            double y = p.y + (c.y - p.y) * t;

            g.setColor(i == 0 ? SnakeColor.get() : SnakeColor.get());
            g.fillRect((int) (x * cellSize), (int) (y * cellSize), cellSize, cellSize);
        }
    }
}
