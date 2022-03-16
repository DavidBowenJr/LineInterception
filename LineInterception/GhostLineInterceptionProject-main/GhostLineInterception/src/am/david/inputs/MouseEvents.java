package am.david.inputs;

import am.david.game.Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseEvents implements MouseListener {
    public static Game game = null;
    public static MouseEvent mouseEventTrap;
    public static MouseEvent getMouseEventTrap(Object o) {
        if(o instanceof  Boolean)
        {
            if((Boolean) o)
            {
                return mouseEventTrap;
            } else
            {
                mouseEventTrap = null;
                return null;
            }
        }
        return mouseEventTrap;
    }
    public static void setMouseEventTrap(MouseEvent e) { mouseEventTrap = e; }
    public MouseEvents(Game game) {
        MouseEvents.game = game;
        game.getCanvas().addMouseListener(this);
    }
    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        MouseEvent me = new MouseEvent(e.getComponent(),e.getID(),e.getWhen(),e.getModifiersEx(),e.getX(),e.getY(),e.getClickCount(),e.isPopupTrigger());
        MouseEvents.setMouseEventTrap(me);
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
