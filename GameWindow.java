import javax.swing.*;

public class GameWindow extends JFrame {
    private JPanel currentScreen;

    public GameWindow() {
        setTitle("Hurdle Queens: The Olympic Challenge");
        setSize(1097, 609);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        init();
        setVisible(true);
    }

    public void init() {
        setStartScreen();
    }

    public void setStartScreen() {
        StartScreen startScreen = new StartScreen(this);
        setScreen(startScreen);
    }

    public void setRunnerSelectionScreen() {
        RunnerSelection runnerSelectionScreen = new RunnerSelection(this);
        setScreen(runnerSelectionScreen);
    }

    public void setGameplayScreen(String selectedCountry) {
        GameplayScreen gameplayScreen = new GameplayScreen(this, selectedCountry);
        setScreen(gameplayScreen);
    }

    public void setGameOverScreen(int score) {
        GameOverScreen gameOverScreen = new GameOverScreen(this, score);
        setScreen(gameOverScreen);
    }

    private void setScreen(JPanel newScreen) {
        if (currentScreen != null) {
            remove(currentScreen);
        }
        currentScreen = newScreen;
        add(currentScreen);
        revalidate();
        repaint();
    }
}
