package am.david.inputs;

import am.david.game.Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMove implements MouseMotionListener {
    public static Game game = null;
    public static MouseEvent mouseEventCursor;

    public MouseMove(Game game) {
        MouseMove.game = game;
        game.getCanvas().addMouseMotionListener(this);

    }

    public static MouseEvent getMouseMoveEventTrap(Object o) {
        if(o instanceof Boolean){
            if(((Boolean)o)) {
                return mouseEventCursor;
            } else {
                mouseEventCursor = null; return null;
            }
        }
        return mouseEventCursor;
    }

    public static void setMouseMoveEventTrap(MouseEvent e) {
        mouseEventCursor = e;
    }
    /**
     * Invoked when a mouse button is pressed on a component and then
     * dragged.  {@code MOUSE_DRAGGED} events will continue to be
     * delivered to the component where the drag originated until the
     * mouse button is released (regardless of whether the mouse position
     * is within the bounds of the component).
     * <p>
     * Due to platform-dependent Drag&amp;Drop implementations,
     * {@code MOUSE_DRAGGED} events may not be delivered during a native
     * Drag&amp;Drop operation.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        MouseEvent me = new MouseEvent(e.getComponent(), e.getID(), e.getWhen(), e.getModifiersEx(), e.getX(), e.getY(), e.getClickCount(), e.isPopupTrigger());
        MouseEvents.setMouseEventTrap(me);
    }

    /**
     * Invoked when the mouse cursor has been moved onto a component
     * but no buttons have been pushed.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
