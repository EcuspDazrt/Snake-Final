import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Hashtable;

public class MenuPanel extends JPanel {
    public final Image backgroundImage;

    public MenuPanel(SnakeGame game) {
        setLayout(new GridBagLayout());
        backgroundImage = new ImageIcon(getClass().getResource("/sprites/background.png")).getImage();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;  // column
        gbc.gridy = 2;  // row
        gbc.insets = new Insets(0, 10, 10, 10);  // padding around component
        // Creating buttons
        JButton startButton = new JButton("Start Game");
        JButton exitButton = new JButton("Exit");
        // Adding button functions
        startButton.addActionListener(e -> game.startGame());
        exitButton.addActionListener(e -> System.exit(0));
        // Setting visual button parameters
        startButton.setPreferredSize(new Dimension(600, 50));
        Color customGreen =  new Color(170, 218, 72);
        startButton.setBackground(customGreen);
        startButton.setForeground(Color.DARK_GRAY);
        startButton.setHorizontalAlignment(SwingConstants.CENTER);
        startButton.setBorderPainted(false);
        // add the button to the panel with constraints
        add(startButton, gbc);
        // new row for button
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 10, 10, 10);
        exitButton.setPreferredSize(new Dimension(600, 50));
        exitButton.setBackground(customGreen);
        exitButton.setForeground(Color.DARK_GRAY);
        exitButton.setHorizontalAlignment(SwingConstants.CENTER);
        exitButton.setBorderPainted(false);
        add(exitButton, gbc);
        // creating sliders
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        slider.setPaintTicks(false);
        slider.setMajorTickSpacing(50);
        slider.setMinorTickSpacing(10);
        slider.setInverted(true);
        slider.setPaintLabels(true);
        slider.setPreferredSize(new Dimension(400, 50));
        slider.setOpaque(false);
        slider.setBackground(new Color(0, 0, 0, 0));
        slider.setAlignmentX(SwingConstants.CENTER);
        // creating the labels on the table
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(0, new JLabel("Fast"));
        labelTable.put(50, new JLabel("Snake Speed"));
        labelTable.put(100, new JLabel("Slow"));
        slider.setLabelTable(labelTable);
        // adding second slider
        JSlider slider2 = new JSlider(JSlider.HORIZONTAL, 10, 20, 15);
        slider2.setPaintTicks(false);
        slider2.setMajorTickSpacing(5);
        slider2.setMinorTickSpacing(1);
        slider2.setPaintLabels(true);
        slider2.setPreferredSize(new Dimension(200, 50));
        slider2.setOpaque(false);
        slider2.setBackground(new Color(0, 0, 0, 0));
        slider2.setAlignmentX(SwingConstants.CENTER);

        Hashtable<Integer, JLabel> labelTable2 = new Hashtable<>();
        JLabel smallLabel = new JLabel("Small");
        smallLabel.setFont(new Font("Arial", Font.BOLD, 14));
        smallLabel.setForeground(Color.BLACK);
        labelTable2.put(10, smallLabel);

        JLabel sizeLabel = new JLabel("Board Size");
        sizeLabel.setFont(new Font("Arial", Font.BOLD, 15));
        sizeLabel.setForeground(Color.BLACK);
        labelTable2.put(15, sizeLabel);

        JLabel bigLabel = new JLabel("Big");
        bigLabel.setFont(new Font("Arial", Font.BOLD, 14));
        bigLabel.setForeground(Color.BLACK);
        labelTable2.put(20, bigLabel);
        slider2.setLabelTable(labelTable2);
        //adding change listeners
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                int value = source.getValue();
                SnakeSpeed.set(value);
                System.out.println(SnakeSpeed.get());
            }
        });

        slider2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                int value = source.getValue();
                BoardSize.set(value);
                System.out.println(BoardSize.get());
            }
        });
        // adding sliders with constraints
        gbc.insets = new Insets(0, 10, 10, 10);
        gbc.gridy = 1;
        add(slider, gbc);

        gbc.gridy = 0;
        gbc.insets = new Insets(470, 10, 0, 10);
        add(slider2, gbc);
    }

    // adding the background image
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image scaled to the panel size
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}