import javax.swing.*;
import java.awt.*;

public class GameWindow {


    public static void main(String[] args) {
        JFrame f = new JFrame();
        //creating instance of JButton
        //b.setBounds(130,100,100, 40);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.getContentPane().setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
        Stage s = new Stage();

        s.init();

        f.setSize(1050, 700);//400 width and 500 height
        f.getContentPane().add(s);//adding button in JFrame


        f.setVisible(true);

    }

}
