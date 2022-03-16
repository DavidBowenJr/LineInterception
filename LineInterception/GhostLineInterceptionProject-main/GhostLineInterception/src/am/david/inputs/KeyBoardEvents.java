package am.david.inputs;

import am.david.game.Game;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;


/** @noinspection SameParameterValue*/
public class KeyBoardEvents implements KeyListener {
    public static Game game = null;
    public List<Key> keys = new ArrayList<>();
    public static boolean canExit = false;

    public KeyBoardEvents(Game game) {
        KeyBoardEvents.game = game;
        game.getCanvas().addKeyListener(this);
    }

    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        toggleKey(e.getKeyCode(), false);
    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        toggleKey(e.getKeyCode(), false);
    }

    public class Key{
        private int numTimesPressed = 0;
        private boolean pressed = false;
        public int getNumTimesPressed(){return numTimesPressed;}
        public boolean isPressed(){ return pressed;}
        private void toggle(boolean isPressed){
            pressed = isPressed;
            if(isPressed) numTimesPressed++;
        }
    }

    public Key up = new Key();
    public Key down = new Key();
    public Key left = new Key();
    public Key right = new Key();

    private void toggleKey(int keyCode, boolean isPressed) {
        if(keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
            up.toggle(isPressed);
        }
        if(keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
            down.toggle(isPressed);
        }
        if(keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
            left.toggle(isPressed);
        }
        if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
            right.toggle(isPressed);
        }
        if(keyCode == KeyEvent.VK_ESCAPE || keyCode == KeyEvent.VK_Q) {
            canExit = true;
        }

    }

}
