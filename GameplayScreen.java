import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class GameplayScreen extends JPanel {
    private Runner runner;
    private ArrayList<Hurdle> hurdles;
    private Timer timer;
    private Image backgroundImage;

    public GameplayScreen(GameWindow window, String selectedCountry) {
        runner = new Runner(selectedCountry);
        hurdles = new ArrayList<>();
        backgroundImage = new ImageIcon(getClass().getResource("/img/BGPLAY.png")).getImage();

        hurdles.add(new Hurdle(950, 470));

        timer = new Timer(30, e -> {
            runner.move();

            for (Hurdle hurdle : hurdles) {
                hurdle.move();
            }

            for (Hurdle hurdle : hurdles) {
                if (runner.checkCollision(hurdle)) {
                    window.setGameOverScreen(runner.getScore());  // ส่งคะแนนไปยัง Game Over
                    timer.stop();
                    return;
                }
            }

            for (int i = hurdles.size() - 1; i >= 0; i--) {
                Hurdle hurdle = hurdles.get(i);
                if (runner.getX() > hurdle.getX() + hurdle.getWidth()) {
                    int points = 12 + new Random().nextInt(10);
                    runner.addScore(points);
                    hurdles.remove(hurdle);
                }
            }

            if (hurdles.size() < 2 && Math.random() > 0.95) {
                Hurdle lastHurdle = hurdles.get(hurdles.size() - 1);
                if (lastHurdle.getX() < 600) {
                    hurdles.add(new Hurdle(950, 470));
                }
            }

            repaint();
        });
        timer.start();

        getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "jump");
        getActionMap().put("jump", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runner.jump();
            }
        });

        setFocusable(true);
        requestFocusInWindow();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
        runner.draw(g);

        for (Hurdle hurdle : hurdles) {
            hurdle.draw(g);
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 45));
        String scoreString = String.format("%04d", runner.getScore());
        g.drawString(scoreString, getWidth() - 180, 80);

        setFocusable(true);
        requestFocusInWindow();
    }
}
