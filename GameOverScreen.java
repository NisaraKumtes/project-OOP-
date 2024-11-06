import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameOverScreen extends JPanel {
    private Image backgroundImage; // ตัวแปรสำหรับพื้นหลัง

    public GameOverScreen(GameWindow window, int score) {
        setLayout(new BorderLayout());

        // โหลดรูปภาพพื้นหลัง
        try {
            backgroundImage = ImageIO.read(getClass().getResource("/img/gameove.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //  JLabel แสดงคะแนน
        JLabel scoreLabel = new JLabel("Score: " + score, SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Monospaced", Font.BOLD, 30));
        scoreLabel.setForeground(Color.BLACK);
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER); // จัดให้อยู่ตรงกลางแนวนอน
        scoreLabel.setVerticalAlignment(SwingConstants.CENTER); // จัดให้อยู่ตรงกลางแนวตั้ง
        add(scoreLabel, BorderLayout.CENTER); // วางไว้ตรงกลางของหน้าจอ

        // โหลดปุ่ม Restart
        ImageIcon restartIcon = new ImageIcon(getClass().getResource("/img/restart_icon.png"));

        // ปุ่ม Restart ด้วยรูปภาพ
        JButton restartButton = new JButton();
        restartButton.setIcon(restartIcon);
        restartButton.addActionListener(e -> window.setStartScreen());

        // คุณสมบัติของปุ่ม
        restartButton.setBorderPainted(false);  // ไม่แสดงกรอบปุ่ม
        restartButton.setContentAreaFilled(false); // ทำให้ปุ่มโปร่งใส
        restartButton.setFocusPainted(false);  // ไม่แสดงการเลือกปุ่ม
        restartButton.setPreferredSize(new Dimension(restartIcon.getIconWidth(), restartIcon.getIconHeight())); // ตั้งขนาดให้พอดีกับขนาดของรูปภาพปุ่ม

        //  JPanel ตำแหน่งปุ่ม Restart
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // จัดปุ่มให้อยู่ตรงกลางแนวนอน
        buttonPanel.add(restartButton);
        buttonPanel.setOpaque(false); //  โปร่งใส
        add(buttonPanel, BorderLayout.SOUTH); 

        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // วาดพื้นหลัง
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
        }
    }
}
