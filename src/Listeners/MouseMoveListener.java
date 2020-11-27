package Listeners;

import Standard.Main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMoveListener implements MouseMotionListener {

    private Main main;

    public MouseMoveListener(Main main) {
        this.main = main;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        main.onMouseMove(e.getX(), e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        main.onMouseMove(e.getX(), e.getY());
    }
}
