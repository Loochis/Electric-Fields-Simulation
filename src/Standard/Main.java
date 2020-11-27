package Standard;

import Graphical.DrawablePanel;
import Graphical.MainFrame;
import Physics.Particle;

import java.util.ArrayList;

public class Main{
    //NOTE: TO MAKE CHANGES TO THE SCENE, GO TO MainFrame.java
    public static final double MAXIMUM_CHARGE = 10;
    public static final double MAXIMUM_FIELD_LENGTH = 100;
    public static final double PIXELS_PER_METER = 100;
    public static final double SAMPLING_DISTANCE = 20;

    public static final double K_CONSTANT = 9 * Math.pow(10, 9);
    public static final double MICRO_CONSTANT = 0.000001;
    public static final double NANO_CONSTANT = Math.pow(10, -10);

    private DrawablePanel drawablePanel;
    private ArrayList<Particle> particles;
    public double mouseX = 0, mouseY = 0;
    public boolean gravity = false;
    public boolean clicking = false;
    public int mouseButton = 0;

    private Particle movablePart = new Particle(0, 20, 0, 0, 0);

    private double timeMod = 0;

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
    }

    public Main() {
        (new Thread(this::Run)).start();
    }

    public void Run() {
        while (true) {
            if (particles != null) {
                if (!particles.contains(movablePart))
                    particles.add(movablePart);

                movablePart.setVelocity(new Point( (mouseX - movablePart.getX()) * 1000, (mouseY - movablePart.getY()) * 1000, 0));

                for (Particle particle : particles)
                    particle.PhysicsTick(particles, timeMod, gravity, drawablePanel);

                if (clicking) {
                    if (mouseButton == 1)
                        movablePart.setCharge(10);
                    if (mouseButton == 3)
                        movablePart.setCharge(-10);
                } else {
                    movablePart.setCharge(0);
                }
            }
            drawablePanel.repaint();
        }
    }

    public void setDrawablePanel(DrawablePanel drawablePanel) {
        this.drawablePanel = drawablePanel;
    }

    public void setParticles(ArrayList<Particle> particles) {
        this.particles = particles;
    }

    public void setTimeMod(double timeMod) {
        this.timeMod = timeMod;
    }

    /**
     * Is called by the mouse motion listener
     * @param mouseX the X component of the mouse pos
     * @param mouseY the Y component of the mouse pos
     */
    public void onMouseMove(double mouseX, double mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    /**
     * Is called by the mouse listener
     * @param mouse is a button being pressed, true or false
     * @param buttonPressed the button being pressed (1 = left click, 3 = right click)
     */
    public void onMouseListen(boolean mouse, int buttonPressed) {
        clicking = mouse;
        mouseButton = buttonPressed;
    }
}
