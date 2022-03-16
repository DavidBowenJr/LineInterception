package am.david.game;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class GameLauncher {
    private static Game game = new Game();

    public void init(){
        initialize();
    }
    public static void main(String[] args){
        GameLauncher gameLauncher = new GameLauncher();
        gameLauncher.init();
    }
    public void start(){game.start();}
    public void stop(){game.start();}
    public void destroy(){game.destroy();}
    private void initialize(){
        Date date = new Date();
        System.out.println(date);

        game.canvas = new Canvas();
        game.canvas.setMinimumSize(Game.DIMENSION);
        game.canvas.setMaximumSize(Game.DIMENSION);
        game.canvas.setPreferredSize(Game.DIMENSION);


        game.frame = new JFrame(Game.NAME);

        game.getFrame().setUndecorated(true);
        //  game.getFrame().setOpacity( 0.70f);

        System.out.println(game.getFrame().getTitle());

        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLayout(new BorderLayout());

        game.frame.add(game.canvas,BorderLayout.CENTER);

        game.canvas.setBackground(Color.GREEN);
        game.canvas.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2, Toolkit.getDefaultToolkit().getScreenSize().height/2);

        game.frame.pack();

        game.frame.setResizable(true);
        game.frame.setLocationRelativeTo(null);

        game.frame.setVisible(true);
        game.start();
        if(game.thread.isAlive()){
            System.out.println("game.thread.isAlive and running... ");
        }

    }
}
