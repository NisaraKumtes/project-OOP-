import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Hurdle {
    private int x, y;
    private BufferedImage hurdleImage;
    private static int hurdleType = 1; // วนลูป Hurdle
    private Random random;
    private int speed; // ความเร็วของ Hurdle

    public Hurdle(int x, int y) {
        this.x = 950; // กำหนดตำแหน่งเริ่มต้นทางด้านขวา
        this.y = y;
        this.random = new Random();
        this.speed = 10 + random.nextInt(5); 

        loadHurdleImage();
    }

    private void loadHurdleImage() {
        try {
            switch (hurdleType) {
                case 1:
                    hurdleImage = ImageIO.read(getClass().getResource("/img/h1.png"));
                    break;
                case 2:
                    hurdleImage = ImageIO.read(getClass().getResource("/img/h2.png"));
                    break;
                case 3:
                    hurdleImage = ImageIO.read(getClass().getResource("/img/h3.png"));
                    break;
            }
            hurdleType = (hurdleType % 3) + 1; // เปลี่ยนประเภทของ Hurdle แบบวนลูป
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void move() {
        x -= speed; 

        // ถ้า Hurdle วิ่งออกจากจอ จะถูกรีเซ็ตตำแหน่งใหม่ทางด้านขวา
        if (x < -hurdleImage.getWidth()) {
            speed = 4+ random.nextInt(6); // กำหนดความเร็วใหม่แบบสุ่ม
            x = 650 + random.nextInt(200);  
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return hurdleImage.getWidth();
    }

    public int getHeight() {
        return hurdleImage.getHeight();
    }

    public void draw(Graphics g) {
        g.drawImage(hurdleImage, x, y, 100, 100, null); 
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 40, 40);  // ตรวจสอบการชน
    }
}
