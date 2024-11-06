import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Runner {
    private int x, y;
    private Image runnerImage;
    private BufferedImage jumpImage;
    @SuppressWarnings("unused")
    private BufferedImage collisionImage;
    private boolean jumping;
    private final int jumpHeight = 160; // ความสูงการกระโดด
    private final int groundLevel = 400;
    private int currentJumpHeight = 0;
    private final int width = 150;
    private final int height = 150;
    private int score;  

    public Runner(String country) {
        this.x = 60;  // ตำแหน่งเริ่มต้นในแนวนอน
        this.y = groundLevel;  // เริ่มต้นที่ระดับพื้น
        this.jumping = false;  
        this.score = 0; 
        try {
            switch (country) {
                case "Thailand":
                    runnerImage = new ImageIcon(getClass().getResource("/img/RUNTHAI.gif")).getImage()
                            .getScaledInstance(width, height, Image.SCALE_DEFAULT);
                    jumpImage = ImageIO.read(getClass().getResource("/img/jumpthai.png"));
                    collisionImage = ImageIO.read(getClass().getResource("/img/coll1.png"));
                    break;
                case "USA":
                    runnerImage = new ImageIcon(getClass().getResource("/img/RUNUSA.gif")).getImage()
                            .getScaledInstance(width, height, Image.SCALE_DEFAULT);
                    jumpImage = ImageIO.read(getClass().getResource("/img/jumpUSA.png"));
                    collisionImage = ImageIO.read(getClass().getResource("/img/coll2.png"));
                    break;
                case "Jamaica":
                    runnerImage = new ImageIcon(getClass().getResource("/img/RunnerJamaica.gif")).getImage()
                            .getScaledInstance(width, height, Image.SCALE_DEFAULT);
                    jumpImage = ImageIO.read(getClass().getResource("/img/jumpJAM.png"));
                    collisionImage = ImageIO.read(getClass().getResource("/img/coll3.png"));
                    break;
                case "Far":
                    runnerImage = new ImageIcon(getClass().getResource("/img/RUNFAR.gif")).getImage()
                            .getScaledInstance(width, height, Image.SCALE_DEFAULT);
                    jumpImage = ImageIO.read(getClass().getResource("/img/jumpfar.png"));
                    collisionImage = ImageIO.read(getClass().getResource("/img/coll4.png"));
                    break;
                default:
                    runnerImage = new ImageIcon(getClass().getResource("/img/RUNTHAI.gif")).getImage()
                            .getScaledInstance(width, height, Image.SCALE_DEFAULT);
                    jumpImage = ImageIO.read(getClass().getResource("/img/jumpthai.png"));
                    collisionImage = ImageIO.read(getClass().getResource("/img/coll1.png"));
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void move() {
        if (jumping) {
            currentJumpHeight += 20;  // ความสูงการกระโดด
            y = groundLevel - currentJumpHeight;

            if (currentJumpHeight >= jumpHeight) {
                jumping = false;  // หยุดกระโดด
            }
        } else if (y < groundLevel) {
            currentJumpHeight -= 14;  // ลดความสูงเมื่อวิ่งลง
            y = groundLevel - currentJumpHeight;
            if (y >= groundLevel) {
                y = groundLevel;  
                currentJumpHeight = 0;
            }
        }
    }

    public void jump() {
        if (!jumping && y == groundLevel) {  // กระโดดเมื่ออยู่ที่พื้นเท่านั้น
            jumping = true;
            currentJumpHeight = 0;
        }
    }
    

    public void draw(Graphics g) {
        if (jumping) {
            g.drawImage(jumpImage, x, y, width, height, null);  // วาดภาพกระโดด
        } else {
            g.drawImage(runnerImage, x, y, width, height, null);  // วาดภาพวิ่ง
        }
    }
    
    

    public Ellipse2D getBounds() {
        return new Ellipse2D.Double(x + 10, y + 10, width - 30, height - 30);  // ขอบเขตการชนแบบวงรี
    }

    public boolean checkCollision(Hurdle hurdle) {
        Ellipse2D runnerBounds = this.getBounds();  // ขอบเขตของนักวิ่ง
        Rectangle hurdleBounds = hurdle.getBounds();  // ขอบเขตของ Hurdle
        return runnerBounds.intersects(hurdleBounds);  // ตรวจสอบการชน
    }
    
    // เมธอดเพิ่มคะแนน
    public void addScore(int points) {
        score += points;
    }

    public int getScore() {
        return score;  // Return the current score of the runner
    }
    
    public int getX() {
        return x;  // Return the current X position of the runner
    }
    
    public int getY() {
        return y;  // Return the current Y position of the runner (if needed)
    }
}
