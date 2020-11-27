package Listeners;

import Standard.Main;

import java.awt.event.MouseEvent;

public class MouseListener implements java.awt.event.MouseListener {

    private Main main;

    public MouseListener(Main main) {
        this.main = main;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        main.onMouseListen(true, e.getButton());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        main.onMouseListen(false, e.getButton());
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
