import javax.swing.*;
import java.awt.*;

public class StartScreen extends JPanel {
    private Image backgroundImage;

    public StartScreen(GameWindow window) {
        backgroundImage = new ImageIcon("img/bgpage.png").getImage();
        
        setLayout(new BorderLayout());
        
        // Load the button image
        ImageIcon buttonIcon = new ImageIcon("img/Buttonplay.png");
        
        // Create a button with an image instead of text    
        JButton playButton = new JButton(buttonIcon);
        playButton.setBorderPainted(false); // Remove button border
        playButton.setContentAreaFilled(false); // Make button transparent
        playButton.setFocusPainted(false); // Remove focus border
        playButton.setOpaque(false); // Make sure background is not filled
        
        playButton.addActionListener(e -> window.setRunnerSelectionScreen());
        
        add(playButton, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
