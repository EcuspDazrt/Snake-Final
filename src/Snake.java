import java.awt.*;
import java.util.LinkedList;

public class Snake {

    public enum Direction {
        UP, DOWN, LEFT, RIGHT, NONE;

        public Direction opposite() {
            switch(this) {
                case UP: return DOWN;
                case DOWN: return UP;
                case RIGHT: return LEFT;
                case LEFT: return RIGHT;
            }
            throw new IllegalStateException("Unknown direction: " + this);
        }
    } // defining directions

    private LinkedList<Point> body;

    public Snake(int startX, int startY) {
        body = new LinkedList<>(); // creating body
        body.add(new Point(startX, startY)); // initial head
    }

    public void move(Direction dir, boolean ateFood, GameBoard board) {
        if (prevBody.size() != body.size()) {
            prevBody = new LinkedList<>();
            for (Point p : body) prevBody.add(new Point(p.x, p.y));
        } else {
            for (int i = 0; i < body.size(); i++) {
                Point p = body.get(i);
                Point q = prevBody.get(i);
                q.x = p.x;
                q.y = p.y;
            }
        }

        Point head = body.getFirst();
        Point newHead = new Point(head);

        switch (dir) {
            case UP: newHead.y -= 1; break;
            case DOWN: newHead.y += 1; break;
            case RIGHT: newHead.x += 1; break;
            case LEFT: newHead.x -= 1; break;
        }

        body.addFirst(newHead);


        convertSnakeGridHead(body, board);

        if (!ateFood) {
            convertSnakeGridTail(body, board);
            body.removeLast();
        }
    }

    public boolean checkCollision(int boardWidth, int boardHeight) {
        Point head = body.getFirst();
        // checks wall-collision
        if (head.x < 0 || head.x >= boardWidth || head.y >= boardHeight || head.y < 0) {
            return true;
        }
        // checks self-collision
        for (int i = 1; i < body.size(); i++) {
            if (head.equals(body.get(i))) {
                return true;
            }
        }
        return false;
    }
    // getter:
    public LinkedList<Point> getBody() {
        return body;
    }

    public void convertSnakeGridHead(LinkedList<Point> body, GameBoard board) {

        int headX = body.getFirst().x;
        int headY = body.getFirst().y;
        if (headX < 0 || headX >= board.cols || headY >= board.rows || headY < 0) {
            return;
        }

        board.grid[headY][headX] = 1;
    }

    public void convertSnakeGridTail(LinkedList<Point> body, GameBoard board) {
        int lastX = body.getLast().x;
        int lastY = body.getLast().y;

        board.grid[lastY][lastX] = 0;
    }

    private LinkedList<Point> prevBody = new  LinkedList<>();

    public LinkedList<Point> getPrevBody() {
        return prevBody;
    }
}