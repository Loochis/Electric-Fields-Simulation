package Physics;

import Graphical.DrawablePanel;
import Standard.Main;
import Standard.Point;

import java.awt.*;
import java.util.ArrayList;

public class Particle {
    private double charge, radius;
    private double x;
    private double y;
    private double z;

    private Point velocity = new Point(0,0,0);
    private Point acceleration = new Point(0,0,0);

    private long cachedTime = System.nanoTime();

    private Color color;



    public Point getVelocity() {
        return velocity;
    }

    public void setVelocity(Point velocity) {
        this.velocity = velocity;
    }

    public Point getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Point acceleration) {
        this.acceleration = acceleration;
    }

    public Particle(double charge, double radius, double x, double y, double z) {
        this.charge = charge;
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        if (charge > Main.MAXIMUM_CHARGE)
            charge = Main.MAXIMUM_CHARGE;
        if (charge < -Main.MAXIMUM_CHARGE)
            charge = -Main.MAXIMUM_CHARGE;

        this.charge = charge;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getMass() {
        return (4.0/3.0) * Math.PI * Math.pow(radius / Main.PIXELS_PER_METER, 3);
    }

    public void PhysicsTick(ArrayList<Particle> particles, double timeMod, boolean gravity, DrawablePanel panel) {
        double deltaT = (System.nanoTime() - cachedTime) * timeMod * Main.NANO_CONSTANT;
        Point field = Fields.FieldAt(new Point(x, y, z), particles);

        x += velocity.getX() * deltaT;
        y += velocity.getY() * deltaT;
        z += velocity.getZ() * deltaT;

        velocity.setX(velocity.getX() + acceleration.getX() * deltaT);
        velocity.setY(velocity.getY() + acceleration.getY() * deltaT);
        velocity.setZ(velocity.getZ() + acceleration.getZ() * deltaT);

        double mass = getMass();
        acceleration.setX(charge * Main.MICRO_CONSTANT * field.getX() / mass);
        acceleration.setY(charge * Main.MICRO_CONSTANT * field.getY() / mass);
        acceleration.setZ(charge * Main.MICRO_CONSTANT * field.getZ() / mass);
        if (gravity)
            acceleration.setY(acceleration.getY() + 98000);

        for (Particle particle : particles) {
            if (particle == this)
                continue;

            Point particleDiff = new Point(x - particle.getX(), y - particle.getY(), z - particle.getZ());
            double diffLength = particleDiff.getLength();
            if (diffLength < radius + particle.getRadius()) {
                particleDiff = new Point(particleDiff.getX() / diffLength, particleDiff.getY() / diffLength, particleDiff.getZ() / diffLength);

                //Point initVel = velocity;

                acceleration = new Point(0,0,0);
                particle.setAcceleration(new Point(0,0,0));

                velocity.setX((-velocity.getX() + particle.getVelocity().getX()) / 2);
                velocity.setY((-velocity.getY() + particle.getVelocity().getY()) / 2);
                velocity.setZ((-velocity.getZ() + particle.getVelocity().getZ()) / 2);

                particle.setVelocity(new Point(-velocity.getX(), -velocity.getY(), -velocity.getZ()));

                x += particleDiff.getX() * (Math.sqrt(particle.getRadius()));
                y += particleDiff.getY() * (Math.sqrt(particle.getRadius()));
                z += particleDiff.getZ() * (Math.sqrt(particle.getRadius()));

                particle.setX(particle.getX() - particleDiff.getX() * (Math.sqrt(radius)));
                particle.setY(particle.getY() - particleDiff.getY() * (Math.sqrt(radius)));
                particle.setZ(particle.getZ() - particleDiff.getZ() * (Math.sqrt(radius)));

                double deltaC = charge - particle.getCharge();
                charge -= deltaC/2;
                particle.setCharge(particle.getCharge() + deltaC/2);
            }
        }

        if (x - radius < 0) {
            velocity.setX(-velocity.getX());
            x = radius + 1;
        }
        if (x + radius > panel.getWidth()) {
            velocity.setX(-velocity.getX());
            x = panel.getWidth() - radius - 1;
        }
        if (y - radius < 0) {
            velocity.setY(-velocity.getY());
            y = radius + 1;
        }
        if (y + radius > panel.getHeight()) {
            velocity.setY(-velocity.getY());
            y = panel.getHeight() - radius - 1;
        }
        if (z - radius < -500) {
            velocity.setZ(-velocity.getZ());
            z = radius + -499;
        }
        if (z + radius > 500) {
            velocity.setZ(-velocity.getZ());
            z = 499 - radius;
        }

        cachedTime = System.nanoTime();
    }

    public void Draw(Graphics g) {
        double positivity = charge / Main.MAXIMUM_CHARGE;
        positivity += 1;
        positivity /= 2;

        g.setColor(new Color((float)positivity, 0, (float) (1 - positivity)));
        g.fillOval((int)Math.round(x - (radius)), (int)Math.round(y - (radius)), (int)Math.round(radius * 2), (int)Math.round(radius * 2));
    }
}
