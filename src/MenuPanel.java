import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    public final Image backgroundImage;
    public final Image iconImg;

    public MenuPanel(SnakeGame game) {
        setLayout(new GridBagLayout());
        backgroundImage = new ImageIcon(getClass().getResource("/sprites/background.png")).getImage();
        iconImg = new ImageIcon(getClass().getResource("/sprites/snake_logo.png")).getImage();

        Color customRed = new Color(255, 33, 33);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;  // column
        gbc.gridy = 2;  // row
        gbc.insets = new Insets(650, 10, 10, 10);  // padding around component
        // Creating buttons
        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(e -> game.startGame());
        // Setting visual button parameters
        startButton.setPreferredSize(new Dimension(700, 65));
        Color customGreen =  new Color(170, 218, 72);
        startButton.setBackground(customGreen);
        startButton.setForeground(Color.DARK_GRAY);
        startButton.setHorizontalAlignment(SwingConstants.CENTER);
        startButton.setBorderPainted(false);
        // add the button to the panel with constraints
        add(startButton, gbc);

        JButton settingsButton = new JButton("Settings");
        settingsButton.setPreferredSize(new Dimension(700, 65));
        Color customGreen2 = new Color(170, 218, 72);
        settingsButton.setBackground(customGreen2);
        settingsButton.setForeground(Color.DARK_GRAY);
        startButton.setHorizontalAlignment(SwingConstants.CENTER);
        settingsButton.setBorderPainted(true);
        settingsButton.addActionListener(e -> {
            SettingsDialog dialog = new SettingsDialog((JFrame) SwingUtilities.getWindowAncestor(this));
            dialog.setVisible(true);
        });
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridy++;
        add(settingsButton, gbc);
    }


    // adding the background image
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image scaled to the panel size
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        int logoWidth = 512; // adjust size as needed
        int logoHeight = 225;
        int x = (getWidth() - logoWidth) / 2;
        int y = 20; // distance from top

        g.drawImage(iconImg, x, y, logoWidth, logoHeight, this);
    }
}