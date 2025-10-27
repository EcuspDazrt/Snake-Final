import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeGame extends JFrame {
        private Snake.Direction currentDirection = Snake.Direction.RIGHT;
        private boolean inputGiven = false;
        private Snake.Direction nextDirection = Snake.Direction.NONE;
        private Snake snake;
        private GameBoard board;
        private Food food;
        private GameBoardPanel boardPanel;
        private CardLayout cardLayout;
        private JPanel mainPanel;
        private MenuPanel menuPanel;
        public static SnakeSpeed snakeSpeed;
        public static BoardSize boardSize;
        private boolean isPaused = false;

        public SnakeGame() {
            cardLayout = new CardLayout();
            mainPanel = new JPanel(cardLayout);
            snakeSpeed = new SnakeSpeed(50);
            boardSize = new BoardSize(20);

            menuPanel = new MenuPanel(this);
            mainPanel.add(menuPanel, "Menu");

            snake = new Snake(boardSize.get() / 2, boardSize.get() / 2);
            board = new GameBoard(boardSize.get());
            food = new Food(board);

            boardPanel = new GameBoardPanel(board);
            mainPanel.add(boardPanel, "Game");

            this.add(mainPanel);
            showMenu();

            gameLoop();

            this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (inputGiven) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP:
                            nextDirection = Snake.Direction.UP;
                            break;
                        case KeyEvent.VK_DOWN:
                            nextDirection = Snake.Direction.DOWN;
                            break;
                        case KeyEvent.VK_LEFT:
                            nextDirection = Snake.Direction.LEFT;
                            break;
                        case KeyEvent.VK_RIGHT:
                            nextDirection = Snake.Direction.RIGHT;
                            break;
                        case KeyEvent.VK_ESCAPE:
                            isPaused = !isPaused;
                            break;
                        case KeyEvent.VK_ENTER:
                            if (menuPanel.isVisible()) {startGame();}
                            break;
                        case KeyEvent.VK_R:
                            timer.stop();
                            showMenu();
                            break;
                    }
                } else {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP:
                            if (currentDirection != Snake.Direction.DOWN) currentDirection = Snake.Direction.UP;
                            break;
                        case KeyEvent.VK_DOWN:
                            if (currentDirection != Snake.Direction.UP) currentDirection = Snake.Direction.DOWN;
                            break;
                        case KeyEvent.VK_LEFT:
                            if (currentDirection != Snake.Direction.RIGHT) currentDirection = Snake.Direction.LEFT;
                            break;
                        case KeyEvent.VK_RIGHT:
                            if (currentDirection != Snake.Direction.LEFT) currentDirection = Snake.Direction.RIGHT;
                            break;
                        case KeyEvent.VK_ESCAPE:
                            isPaused = !isPaused;
                            break;
                        case KeyEvent.VK_ENTER:
                            if (menuPanel.isVisible()) {startGame();}
                            break;
                        case KeyEvent.VK_R:
                            timer.stop();
                            showMenu();
                            break;
                    }
                    inputGiven = true;
                }

            }
        });

            this.setFocusable(true);
            this.requestFocusInWindow();

        }

        // do frame, panel, timer
        private Timer timer;
        private boolean ateFood = false;

        private void gameLoop() {
            if (timer != null) {
                timer.stop();
                timer = null;
            }

            timer = new Timer(snakeSpeed.get() + 50, e -> {
                if (!inputGiven && nextDirection != Snake.Direction.NONE) {
                    currentDirection = nextDirection;
                    nextDirection = Snake.Direction.NONE;
                    System.out.println(currentDirection);
                }

                if (!isPaused) {
                    snake.move(currentDirection, ateFood, snake.getBody(), board);
                }
                inputGiven = false;

                if (snake.checkCollision(board.cols, board.rows)) {
                    timer.stop();
                    showMenu();
                }

                if (snake.getBody().getFirst().equals(food.getPosition())) {
                    ateFood = true;
                    food.updateFood(board);
                } else {
                    ateFood = false;
                }
                boardPanel.repaint();
            });
        timer.start();
        }

    public void showMenu() {
        cardLayout.show(mainPanel, "Menu");
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


        boardPanel.board = board;

        cardLayout.show(mainPanel, "Game");
        gameLoop();
        this.requestFocusInWindow();
    }

    public static void main(String[] args) {
        SnakeGame game = new SnakeGame();

        game.setTitle("Snake Game");
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setResizable(false);
        game.pack();
        game.setVisible(true);
        game.setFocusable(true);
    }
}