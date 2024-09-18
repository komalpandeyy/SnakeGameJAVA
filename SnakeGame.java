import javax.swing.*;

public class SnakeGame extends JFrame{

    SnakeGame(){
        super("Snake Game");
        add(new Board());
        pack();
        setSize(300, 300);
        setLocationRelativeTo(null);
        // setLocation(700,300);
        setVisible(true);

    }

    public static void main(String[] args) {
        SnakeGame snGame = new SnakeGame();

    }
}