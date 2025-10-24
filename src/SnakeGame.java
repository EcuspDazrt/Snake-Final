import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeGame extends JFrame {
        private Snake.Direction currentDirection = Snake.Direction.RIGHT;
        private Snake.Direction nextDirection = Snake.Direction.NONE;
        private Snake.Direction futureDirection = Snake.Direction.NONE;
        private Snake snake;
        private GameBoard board;
        private Food food;
        private GameBoardPanel boardPanel;
        private CardLayout cardLayout;
        private JPanel mainPanel;
        private MenuPanel menuPanel;
        private WinPanel winPanel;
        public static SnakeSpeed snakeSpeed;
        public static BoardSize boardSize;
        private boolean isPaused = false;
        public long lastUpdateTime;
        public long tickDuration;

        public SnakeGame() {

            cardLayout = new CardLayout();
            mainPanel = new JPanel(cardLayout);
            snakeSpeed = new SnakeSpeed(50);
            boardSize = new BoardSize(15);
            SnakeColor.set(new Color(71, 117, 235));

            menuPanel = new MenuPanel(this);
            winPanel = new WinPanel(this);
            mainPanel.add(menuPanel, "Menu");
            mainPanel.add(winPanel, "Win");

            snake = new Snake(boardSize.get() / 2, boardSize.get() / 2);
            board = new GameBoard(boardSize.get());
            food = new Food(board);

            boardPanel = new GameBoardPanel(board, this, snake);
            mainPanel.add(boardPanel, "Game");

            this.add(mainPanel);
            showMenu();

            gameLoop();

            this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                Snake.Direction newDir = currentDirection;
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP:
                            newDir = Snake.Direction.UP;
                            break;
                        case KeyEvent.VK_DOWN:
                            newDir = Snake.Direction.DOWN;
                            break;
                        case KeyEvent.VK_LEFT:
                            newDir = Snake.Direction.LEFT;
                            break;
                        case KeyEvent.VK_RIGHT:
                            newDir = Snake.Direction.RIGHT;
                            break;
                        case KeyEvent.VK_ESCAPE:
                            isPaused = !isPaused;
                            return;
                        case KeyEvent.VK_ENTER:
                            if (menuPanel.isVisible()) {startGame();}
                            return;
                        case KeyEvent.VK_R:
                            timer.stop();
                            showMenu();
                            return;
                }

                    if (newDir == Snake.Direction.NONE) return;

                    if (newDir == currentDirection.opposite() && nextDirection == Snake.Direction.NONE) return;

                    if (nextDirection == Snake.Direction.NONE) {
                        nextDirection = newDir;
                    } else {
                        if (newDir != nextDirection.opposite()) {
                            futureDirection = newDir;
                        }
                    }
            }
        });

            this.setFocusable(true);
            this.requestFocusInWindow();

}
//        iconImg = new ImageIcon(getClass().getResource("/sprites/snake_logo.png")).getImage();
//        MenuPanel.setIconImage(iconImg);
        // do frame, panel, timer
Timer timer;
        private boolean ateFood = false;

        private void gameLoop() {
            if (timer != null) {
                timer.stop();
                timer = null;
            }

            tickDuration = (snakeSpeed.get() + 50) * 1_000_000L;
            lastUpdateTime = System.nanoTime();

            final double logicStep = (snakeSpeed.get() + 50) / 1000.0;
            final double[] accumulator = { 0.0 };
            final long[] previous = { System.nanoTime()};

            timer = new Timer(16, e -> {
                long now = System.nanoTime();
                double delta = (now - previous[0]) / 1_000_000_000.0;
                previous[0] = now;
                accumulator[0] += delta;

                while (accumulator[0] >= logicStep) {
                    lastUpdateTime = System.nanoTime();

                    if (nextDirection != Snake.Direction.NONE) {
                        currentDirection = nextDirection;
                        nextDirection = Snake.Direction.NONE;
                    }

                    if (futureDirection != Snake.Direction.NONE) {
                        nextDirection = futureDirection;
                    }
                    futureDirection = Snake.Direction.NONE;

                    if (!isPaused) {
                        snake.move(currentDirection, ateFood, board);
                    }

                    if (snake.checkCollision(board.cols, board.rows)) {
                        timer.stop();
                        showMenu();
                    }

                    boolean justAte = snake.getBody().getFirst().equals(food.getPosition());

                    if (justAte) {
                        ateFood = true;
                        SwingUtilities.invokeLater(() -> {food.updateFood(board);});
                    } else {
                        ateFood = false;
                    }
                    accumulator[0] -= logicStep;
                }
                boardPanel.repaint();
            });
        timer.start();
        }

    public void showMenu() {
        cardLayout.show(mainPanel, "Menu");
    }

    public void showWin() {
        cardLayout.show(mainPanel, "Win");
    }


    public void startGame() {
        if (timer != null) {
            timer.stop();
            timer = null;
        }

        board = new GameBoard(boardSize.get());
        snake = new Snake(5, 5);
        food = new Food(board);
        ateFood = false;
        currentDirection = Snake.Direction.RIGHT;


        boardPanel.setSnake(snake);
        boardPanel.setBoard(board);

        cardLayout.show(mainPanel, "Game");
        gameLoop();
        this.requestFocusInWindow();
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        SnakeGame game = new SnakeGame();
        Food.setGame(game);

        game.setTitle("Snake Game");
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setResizable(false);
        game.pack();
        game.setVisible(true);
        game.setFocusable(true);
    }


}