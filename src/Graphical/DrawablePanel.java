package Graphical;

import Physics.Fields;
import Physics.Particle;
import Standard.Main;
import Standard.Point;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DrawablePanel extends JPanel {

    private ArrayList<Particle> particles;
    private double sampleDistance = 10;

    public void setParticles(ArrayList<Particle> newParts) {
        particles = newParts;
    }

    public void setSampleDistance(double newDepth) {
        sampleDistance = newDepth;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(29, 29, 29));
        g.fillRect(0,0,getWidth(),getHeight());
        if (particles != null) {
            DrawField(g);
            DrawParticles(g);
        }
    }

    private void DrawParticles(Graphics g) {
        for (Particle particle : particles) {
            particle.Draw(g);
        }
    }

    private void DrawField(Graphics g) {
        for (int x = 0; x < getWidth() + Main.SAMPLING_DISTANCE; x += Main.SAMPLING_DISTANCE) {
            for (int y = 0; y < getHeight() + Main.SAMPLING_DISTANCE; y += Main.SAMPLING_DISTANCE) {
                Point field = Fields.FieldAt(new Standard.Point(x, y, sampleDistance), particles);
                float length = (float) field.getLength();
                float colLength = length / 50000000f;
                if (colLength > 1)
                    colLength = 1;
                g.setColor(new Color(1.0f, 1.0f, 1.0f, colLength));
                if (length > Main.MAXIMUM_FIELD_LENGTH) {
                    field.setX(field.getX() / length * Main.MAXIMUM_FIELD_LENGTH);
                    field.setY(field.getY() / length * Main.MAXIMUM_FIELD_LENGTH);
                    field.setZ(field.getZ() / length * Main.MAXIMUM_FIELD_LENGTH);
                }
                g.drawLine(x - (int)Math.round(field.getX() / 10f), y - (int)Math.round(field.getY() / 10f), x + (int)Math.round(field.getX() / 10f), y + (int)Math.round(field.getY() / 10f));
            }
        }
    }
}
