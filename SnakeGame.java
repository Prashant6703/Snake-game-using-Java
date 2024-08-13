import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakeGame extends JPanel implements ActionListener 
{

    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private static final int DOT_SIZE = 20;
    private static final int TOTAL_DOTS = (WIDTH * HEIGHT) / (DOT_SIZE * DOT_SIZE);
    private static final int DELAY = 150;

    private final int[] x = new int[TOTAL_DOTS];
    private final int[] y = new int[TOTAL_DOTS];
    private int snakeSize;
    private int appleX;
    private int appleY;
    private boolean isRunning;
    private Direction direction;

    private Timer timer;

    public SnakeGame() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.black);
        setFocusable(true);
        setLayout(null);
        addKeyListener(new SnakeKeyListener());
        initGame();
    }

    private void initGame() {
        snakeSize = 3;
        direction = Direction.RIGHT;

        for (int i = 0; i < snakeSize; i++) {
            x[i] = 60 - i * DOT_SIZE;
            y[i] = 60;
        }

        generateApple();

        timer = new Timer(DELAY, this);
        timer.start();

        isRunning = true;
    }

    private void generateApple() {
        int maxX = WIDTH / DOT_SIZE;
        int maxY = HEIGHT / DOT_SIZE;

        appleX = (int) (Math.random() * maxX) * DOT_SIZE;
        appleY = (int) (Math.random() * maxY) * DOT_SIZE;
    }

    private void move() {
        for (int i = snakeSize; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
            case UP:
                y[0] -= DOT_SIZE;
                break;
            case DOWN:
                y[0] += DOT_SIZE;
                break;
            case LEFT:
                x[0] -= DOT_SIZE;
                break;
            case RIGHT:
                x[0] += DOT_SIZE;
                break;
        }
    }

    private void checkCollision() {
        for (int i = snakeSize; i > 0; i--) {
            if (i > 4 && x[0] == x[i] && y[0] == y[i]) {
                isRunning = false;
            }
        }

        if (x[0] >= WIDTH || x[0] < 0 || y[0] >= HEIGHT || y[0] < 0) {
            isRunning = false;
        }

        if (x[0] == appleX && y[0] == appleY) {
            snakeSize++;
            generateApple();
        }
    }

    private void draw(Graphics g) {
        if (isRunning) {
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, DOT_SIZE, DOT_SIZE);

            for (int i = 0; i < snakeSize; i++) {
                g.setColor(Color.green);
                g.fillRect(x[i], y[i], DOT_SIZE, DOT_SIZE);
            }

            Toolkit.getDefaultToolkit().sync();
        } else {
            gameOver(g);
        }
    }

    private void gameOver(Graphics g) {
        String message = "Game Over";
        Font font = new Font("Helvetica", Font.BOLD, 40);
        FontMetrics metrics = g.getFontMetrics(font);

        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(message, (WIDTH - metrics.stringWidth(message)) / 2, HEIGHT / 2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isRunning) {
            checkCollision();
            move();
        }

        repaint();
    }

    private class SnakeKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            switch (key) {
                case KeyEvent.VK_UP:
                    if (direction != Direction.DOWN)
                        direction = Direction.UP;
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != Direction.UP)
                        direction = Direction.DOWN;
                    break;
                case KeyEvent.VK_LEFT:
                    if (direction != Direction.RIGHT)
                        direction = Direction.LEFT;
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != Direction.LEFT)
                        direction = Direction.RIGHT;
                    break;
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");
        SnakeGame game = new SnakeGame();
        frame.add(game);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
}
