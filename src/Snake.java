import com.sun.rmi.rmid.ExecPermission;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Snake {
    ArrayList<Position> L = new ArrayList<>();
    int eating_counter = 3;
    static final int unit_size = 20;
    static int direction = 39;
    static int pastDirection = 39;
    static int speed = 10;


    void initS() {

        L.add(new Position(50, 50));
        L.add(new Position(60, 50));
        L.add(new Position(70, 50));

    }

    /*void move(){
        switch (direction){
            case KeyEvent.VK_DOWN:
                for (Position p:L){
                    p.y=p.y+1*speed;}
                break;
            case KeyEvent.VK_UP:
                for (Position p:L){
                    p.y=p.y-1*speed;}
                break;
            case KeyEvent.VK_LEFT:
                for (Position p:L){
                    p.x=p.x-1*speed;}

                break;
            case KeyEvent.VK_RIGHT:
                for (Position p:L){
                    p.x=p.x+1*speed;
                }
                break;

        }

    }*/


    void eat() {
        eating_counter++;
        L.add(new Position(L.get(L.size() - 1).x, L.get(L.size() - 1).y));

    }

}
