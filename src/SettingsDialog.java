import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Hashtable;


public class SettingsDialog extends JDialog {
    public SettingsDialog(JFrame parent) {
        super(parent, "Settings", true); // true = modal
        setLayout(new GridBagLayout());
        setSize(500, 430);
        setResizable(false);
        setLocationRelativeTo(parent); // center over game window
        getContentPane().setBackground(new Color(214, 217, 223));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(Box.createHorizontalGlue(), gbc); // placeholder

// --- your invisButton ---
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;

        JButton invisButton = new JButton("");
        invisButton.setBackground(Color.WHITE);
        invisButton.setPreferredSize(new Dimension(30, 30));
        invisButton.setBorderPainted(false);
        invisButton.setFocusPainted(false);
        invisButton.setHorizontalAlignment(SwingConstants.RIGHT);

        invisButton.addActionListener(e -> {
            SnakeColor.set(new Color(170, 215, 81));
        });

        add(invisButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // span across both columns
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel title = new JLabel("Settings", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridy++;
        add(title, gbc);

        gbc.gridy++;

        JPanel colorPanel = new JPanel();
        colorPanel.setOpaque(false);

        JButton whiteButton = new  JButton("");
        whiteButton.setBackground(Color.BLACK);
        whiteButton.setPreferredSize(new Dimension(30, 30));
        whiteButton.setBorderPainted(false);
        whiteButton.setFocusPainted(false);

        whiteButton.addActionListener(e -> {
            SnakeColor.set(Color.BLACK);
        });

        colorPanel.add(whiteButton);

        JButton grayButton = new JButton("");
        grayButton.setBackground(Color.DARK_GRAY);
        grayButton.setPreferredSize(new Dimension(30, 30));
        grayButton.setBorderPainted(false);
        grayButton.setFocusPainted(false);

        grayButton.addActionListener(e -> {
            SnakeColor.set(Color.GRAY);
        });

        colorPanel.add(grayButton);

        JButton redButton = new JButton("");
        redButton.setBackground(new Color(246, 10, 58));
        redButton.setPreferredSize(new Dimension(30, 30));
        redButton.setBorderPainted(false);
        redButton.setFocusPainted(false);

        redButton.addActionListener(e -> {
            SnakeColor.set(new Color (246, 10, 58));
        });

        colorPanel.add(redButton);

        JButton blueButton = new JButton("");
        blueButton.setBackground(new Color(71, 117, 235));
        blueButton.setPreferredSize(new Dimension(30, 30));
        blueButton.setBorderPainted(false);
        blueButton.setFocusPainted(false);

        blueButton.addActionListener(e -> {
            SnakeColor.set(new Color(71, 117, 235));
        });

        colorPanel.add(blueButton);

        JButton purpleButton = new JButton("");
        purpleButton.setBackground(new Color (137, 43, 191));
        purpleButton.setPreferredSize(new Dimension(30, 30));
        purpleButton.setBorderPainted(false);
        purpleButton.setFocusPainted(false);

        purpleButton.addActionListener(e -> {
            SnakeColor.set(new Color (137, 43, 191));
        });

        colorPanel.add(purpleButton);

        JButton pinkButton = new JButton("");
        pinkButton.setBackground(new Color (224, 0, 255));
        pinkButton.setPreferredSize(new Dimension(30, 30));
        pinkButton.setBorderPainted(false);
        pinkButton.setFocusPainted(false);

        pinkButton.addActionListener(e -> {
            SnakeColor.set(new Color (224, 0, 255));
        });

        colorPanel.add(pinkButton);

        add(colorPanel, gbc);

        gbc.gridy++;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);

        slider.setPaintTicks(false);
        slider.setMajorTickSpacing(50);
        slider.setMinorTickSpacing(10);
        slider.setInverted(true);
        slider.setPaintLabels(true);
        slider.setPreferredSize(new Dimension(400, 50));
        slider.setOpaque(false);
        slider.setBackground(new Color(0, 0, 0));
        slider.setAlignmentX(SwingConstants.CENTER);

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                int value = source.getValue();
                SnakeSpeed.set(value);
            }
        });

        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();

        JLabel speedLabel = new JLabel("Snake Speed");
        speedLabel.setFont(new Font("Arial", Font.BOLD, 17));
        speedLabel.setForeground(Color.BLACK);
        labelTable.put(50, speedLabel);

        slider.setLabelTable(labelTable);

        JPanel sliderPanel = new JPanel();
        sliderPanel.add(slider);
        add(sliderPanel, gbc);

        gbc.gridy++;

        JSlider slider2 = new JSlider(JSlider.HORIZONTAL, 10, 20, 15);

        slider2.setPaintTicks(false);
        slider2.setMajorTickSpacing(5);
        slider2.setMinorTickSpacing(1);
        slider2.setPaintLabels(true);
        slider2.setPreferredSize(new Dimension(400, 50));
        slider2.setOpaque(false);
        slider2.putClientProperty("JSlider.trackColor", Color.RED);
        slider2.setAlignmentX(SwingConstants.CENTER);

        slider2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                int value = source.getValue();
                BoardSize.set(value);
            }
        });

        Hashtable<Integer, JLabel> labelTable2 = new Hashtable<>();

        JLabel sizeLabel = new JLabel("Board Size");
        sizeLabel.setFont(new Font("Arial", Font.BOLD, 17));
        sizeLabel.setForeground(Color.BLACK);
        labelTable2.put(15, sizeLabel);

        slider2.setLabelTable(labelTable2);

        JPanel sliderPanel2 = new JPanel();
        sliderPanel2.add(slider2);
        add(sliderPanel2, gbc);

        gbc.gridy++;
        JButton closeButton = new JButton("Close");
        closeButton.setPreferredSize(new Dimension(400, 40));
        closeButton.addActionListener(e -> dispose());
        add(closeButton, gbc);
    }
}
