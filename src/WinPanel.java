import javax.swing.*;
import java.awt.*;

public class WinPanel extends JPanel {
    private final Image win;

    public WinPanel(SnakeGame game) {
        win = new ImageIcon(getClass().getResource("/sprites/win.png")).getImage();

        setLayout(new BorderLayout());

        JButton button = new JButton("Go to Menu");
        button.setBackground(new Color(170, 218, 72));
        button.setPreferredSize(new Dimension(700, 90));
        button.addActionListener(e -> game.showMenu());

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 50));
        bottomPanel.setOpaque(false);
        bottomPanel.add(button,  BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image scaled to the panel size
        g.drawImage(win, 0, 0, getWidth(), getHeight(), this);
    }
}
