import javax.swing.*;
import java.awt.*;

public class RunnerSelection extends JPanel {
    
    private String selectedCountry;
    private Image backgroundImage;
    
    public RunnerSelection(GameWindow window) {
        backgroundImage = new ImageIcon("img/Bgthai01.png").getImage();
    
        setLayout(new BorderLayout());
    
        JPanel countryPanel = new JPanel(new GridBagLayout());
        countryPanel.setOpaque(false);
    
        GridBagConstraints gbc = new GridBagConstraints();
    
        // สร้างปุ่มสำหรับแต่ละประเทศ
        JButton country1 = createTransparentButton("img/THAI.png", "Thailand", "img/Bgthai01.png");
        JButton country2 = createTransparentButton("img/USA.png", "USA", "img/BgUSA02.png");
        JButton country3 = createTransparentButton("img/JAM.png", "Jamaica", "img/Bgjamaica.png");
        JButton country4 = createTransparentButton("img/FR.png", "Far", "img/Bgfrance.png");
    
        // จัดปุ่มในตาราง 2x2
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(90, 240, 5, 0); 
        gbc.anchor = GridBagConstraints.CENTER;
        countryPanel.add(country1, gbc);
    
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(90, 0, 5, 120); 
        gbc.anchor = GridBagConstraints.CENTER;
        countryPanel.add(country2, gbc);
    
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(35, 240, 5, 0); 
        countryPanel.add(country3, gbc);
    
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(35, 0, 5, 120); 
        gbc.anchor = GridBagConstraints.CENTER;
        countryPanel.add(country4, gbc);
    
        add(countryPanel, BorderLayout.WEST);
        
        ImageIcon startIcon = new ImageIcon("img/BTstart.png");
        JButton startButton = new JButton(startIcon);
        
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        startButton.setOpaque(false);
    
        startButton.addActionListener(e -> {
            // ตรวจสอบว่ามีการเลือกประเทศก่อนหรือไม่
            if (selectedCountry != null) {
                System.out.println("Selected country: " + selectedCountry); // ตรวจสอบการเลือก
                window.setGameplayScreen(selectedCountry);  // ส่งประเทศไปยัง GameplayScreen
            } else {
                JOptionPane.showMessageDialog(this, "Please select a country before starting the game.");
            }
        });
    
        add(startButton, BorderLayout.SOUTH);
    }
    
    private JButton createTransparentButton(String imagePath, String countryName, String bgImagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        JButton button = new JButton(icon);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false); 
    
        // เมื่อกดปุ่ม เลือกประเทศและเปลี่ยนพื้นหลัง
        button.addActionListener(e -> {
            selectedCountry = countryName;  // กำหนดประเทศที่เลือก
            backgroundImage = new ImageIcon(bgImagePath).getImage();  // เปลี่ยนพื้นหลังตามประเทศที่เลือก
            repaint();  // วาดใหม่พร้อมพื้นหลังใหม่
        });
    
        return button;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);  // วาดพื้นหลัง
    }
}
