import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import java.awt.Graphics;

import javax.swing.*;
public class Board extends JPanel implements ActionListener{
    private int dots;
    private Image apple;
    private Image dot;
    private Image head;
    private final int ALL_DOTS = 900;
    private final int DOT_SIZE = 10;
    private final int[] x = new int[ALL_DOTS];
    private final int[] y = new int[ALL_DOTS];
    private int randomPosition;
    private int apple_x;
    private int apple_y;
    private Timer timer;
    private boolean leftDirection = false;
    private boolean rightDirection = false;
    private boolean upDirection = false;
    private boolean downDirection = false;
    Board(){
        addKeyListener(new TAdapter());
        setBackground(Color.BLACK);
        setFocusable(true);
        loadImages();
        initGame();
    }

    public void loadImages(){
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("apple.png"));
        apple = i1.getImage();
        ImageIcon i2 = new ImageIcon(ClassLoader.getSystemResource("dot.png"));
        dot = i2.getImage();
        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("head.png"));
        head = i3.getImage();
    }
    public void initGame(){
        dots = 3;

        for(int i = 0;i<dots;i++){
            y[i] = 50;
            x[i] =
            50-i*DOT_SIZE;
        }
        locateApple();
        Timer timer = new Timer(140,this);
        timer.start();

    }

    public void locateApple(){
        int r =(int)Math.random()*randomPosition;
        apple_x = r*DOT_SIZE;
        r =(int)Math.random()*randomPosition;
        apple_y = r*DOT_SIZE;


    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        g.drawImage(apple, apple_x, apple_y, this);
        for(int i = 0;i<dots;i++){
            if(i == 0){
                g.drawImage(head,x[i],y[i],this);

            }
            else{
                g.drawImage(dot,x[i],y[i],this);
            }
        }

        Toolkit.getDefaultToolkit().sync();
    }
    public void move(){
        for(int i = dots;i>0;i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        if(leftDirection){
            x[0]-=DOT_SIZE;
        }
        if(rightDirection){
            x[0]+=DOT_SIZE;
        }
        if(upDirection){
            y[0]-=DOT_SIZE;
        }
        if(downDirection){
            y[0]+=DOT_SIZE;
        }

        x[0] +=DOT_SIZE;
        y[0] +=DOT_SIZE;
    }
    public void actionPerformed(ActionEvent ae){
        move();
        repaint();
    }

    public class TAdapter extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            int key = e.getKeyCode();

            if(key == KeyEvent.VK_LEFT && (!rightDirection)){
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if(key == KeyEvent.VK_RIGHT && (!leftDirection)){
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if(key == KeyEvent.VK_UP && (!downDirection)){
                upDirection = true;
                leftDirection = false;
                rightDirection = false;
            }

            if(key == KeyEvent.VK_DOWN && (!upDirection)){
                downDirection = true;
                leftDirection = false;
                rightDirection = false;
            }
        }
    }
}
