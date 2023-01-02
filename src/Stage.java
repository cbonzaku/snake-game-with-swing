import com.sun.org.apache.xpath.internal.operations.Div;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Stage extends JPanel {

    Random r = new Random();
    Snake snake = new Snake();
    Food food = new Food(new Position(r.nextInt(550), r.nextInt(550)));
    int a = 0;
    BufferedImage im;
    boolean exit = false;


    void init() {
        try {
            im = ImageIO.read(new File("src/Asset 2.png"));
        } catch (IOException e) {

            System.out.println(e.getMessage().toString());
        }
        this.setPreferredSize(new Dimension(600, 600));
        this.setMaximumSize(new Dimension(600, 600));
        this.setBackground(Color.black);
        snake.initS();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!exit) {

                    moving();
                    checkDeath();
                    try {
                        Thread.sleep(80);
                    } catch (Exception e) {
                        e.getMessage();
                    }


                }
            }
        });
        thread1.start();
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                int s = e.getKeyCode();
                if (((s == KeyEvent.VK_UP) && a != 2) || ((s == KeyEvent.VK_LEFT) && a != 0)
                        || ((s == KeyEvent.VK_RIGHT) && a != 0) || ((s == KeyEvent.VK_DOWN) && a != 3))
                    Snake.direction = s;

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        this.setFocusable(true);

    }

    void checkDeath() {
        for (int i = 1; i < snake.L.size() - 1; i++) {
            if (snake.L.get(0).x == snake.L.get(i).x && snake.L.get(0).y == snake.L.get(i).y) {
                System.out.println("game over");

                JLabel jLabel = new JLabel("Game Over", SwingConstants.CENTER);
                jLabel.setForeground(Color.red);
                //jLabel.setHorizontalAlignment(JLabel.CENTER);
                jLabel.setFont(new Font("Serif", Font.PLAIN, 40));
                jLabel.setVisible(true);


                this.add(jLabel);
                repaint();
                revalidate();
                exit = true;

            }
        }
    }

    void overlaping() {
        //if (!(snake.L.get(0).x + 20 < food.p.x || snake.L.get(0).y < food.p.y + 10 || snake.L.get(0).x > food.p.x + 10 || snake.L.get(0).y + 20 > food.p.y)) {
        if (snake.L.get(0).x <= food.p.x && snake.L.get(0).x + 20 >= food.p.x && snake.L.get(0).y <= food.p.y && snake.L.get(0).y + 20 >= food.p.y) {
            System.out.println("eeeeee");
            snake.eat();
            food = new Food(new Position(r.nextInt(600), r.nextInt(600)));
        }


    }


    void start() {
        switch (Snake.direction) {
            case KeyEvent.VK_DOWN:
                a = 2;
                snake.L.get(0).y = (snake.L.get(0).y + Snake.unit_size) % 600;


                break;
            case KeyEvent.VK_UP:
                a = 3;
                snake.L.get(0).y = (snake.L.get(0).y - Snake.unit_size + 600) % 600;


                break;
            case KeyEvent.VK_LEFT:

                snake.L.get(0).x = (snake.L.get(0).x - Snake.unit_size + 600) % 600;
                a = 1;


                break;
            case KeyEvent.VK_RIGHT:

                snake.L.get(0).x = (snake.L.get(0).x + Snake.unit_size) % 600;
                a = 0;
                break;

        }

    }


    void moving() {
        for (int i = snake.eating_counter - 1; i > 0; i--) {
            snake.L.get(i).x = snake.L.get(i - 1).x;
            snake.L.get(i).y = snake.L.get(i - 1).y;

            overlaping();


        }

        repaint();
        start();
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gui = (Graphics2D) g;

        g.setColor(Color.red);
        for (int i = 0; i < snake.L.size(); i++) {

            gui.fill(new Rectangle2D.Double(
                    snake.L.get(i).x, snake.L.get(i).y,
                    snake.unit_size, snake.unit_size));

            gui.drawImage(im, food.p.x, food.p.y, null);
        }

    }
}
