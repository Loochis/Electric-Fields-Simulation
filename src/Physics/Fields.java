package Physics;

import Standard.Main;
import Standard.Point;

import java.util.ArrayList;

public class Fields {
    public static Point FieldAt(Point p, ArrayList<Particle> particles) {
        Point totalField = new Point(0, 0, 0);
        for (Particle particle : particles) {
            Point difference = new Point(p.getX() - particle.getX(), p.getY() - particle.getY(), p.getZ() - particle.getZ());
            double distance2 = Math.pow(difference.getX() / Main.PIXELS_PER_METER, 2) + Math.pow(difference.getY() / Main.PIXELS_PER_METER, 2) + Math.pow(difference.getZ() / Main.PIXELS_PER_METER, 2);
            if (distance2 < 0.000001)
                continue;
            double strength = Main.K_CONSTANT * Main.MICRO_CONSTANT * particle.getCharge() / distance2;

            double distance = Math.sqrt(distance2);
            double multiplier = strength / distance;
            totalField.setX(totalField.getX() + difference.getX() * multiplier);
            totalField.setY(totalField.getY() + difference.getY() * multiplier);
            totalField.setZ(totalField.getZ() + difference.getZ() * multiplier);

        }

        return new Point(totalField.getX(), totalField.getY(), totalField.getZ());
    }
}
