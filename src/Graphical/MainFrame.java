package Graphical;

import Listeners.MouseListener;
import Listeners.MouseMoveListener;
import Physics.Particle;
import Standard.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainFrame extends JFrame {


    private JPanel panel1;
    private JPanel drawPanel;
    private JSlider DepthSlider;
    private JButton addParticleButton;
    private JSlider TimeSlider;
    private JButton PAUSEButton;
    private JButton FWDButton;
    private JButton FFWDButton;
    private JButton toggleGravityButton;

    private DrawablePanel drawablePanel;

    public MainFrame() {
        $$$setupUI$$$();
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();

        Main main = new Main();
        main.setDrawablePanel(drawablePanel);

        drawablePanel.addMouseListener(new MouseListener(main));
        drawablePanel.addMouseMotionListener(new MouseMoveListener(main));
        PAUSEButton.addActionListener(e -> TimeSlider.setValue(0));
        FWDButton.addActionListener(e -> TimeSlider.setValue(100));
        FFWDButton.addActionListener(e -> TimeSlider.setValue(200));
        TimeSlider.addChangeListener(e -> {
            main.setTimeMod(TimeSlider.getValue() / 100.0);
        });
        DepthSlider.addChangeListener(e -> {
            drawablePanel.setSampleDistance(DepthSlider.getValue());
            drawablePanel.repaint();
        });

        // EDIT THIS TO CHANGE THE SCENE
        addParticleButton.addActionListener(e -> {
            ArrayList<Particle> particles = new ArrayList<Particle>();
            particles.add(new Particle(10, 10, 450, 350, 0));
            particles.add(new Particle(-10, 10, 550, 350, 0));
            drawablePanel.setParticles(particles);
            main.setParticles(particles);
        });

        toggleGravityButton.addActionListener(e -> main.gravity = !main.gravity);

        drawablePanel.repaint();
    }

    private void createUIComponents() {
        drawPanel = new DrawablePanel();
        drawablePanel = (DrawablePanel) drawPanel;
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(7, 13, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-13882324));
        panel1.add(drawPanel, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 11, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(1000, 700), null, 0, false));
        DepthSlider = new JSlider();
        DepthSlider.setBackground(new Color(-13882324));
        DepthSlider.setMaximum(500);
        DepthSlider.setMinimum(-500);
        DepthSlider.setMinorTickSpacing(100);
        DepthSlider.setPaintTicks(true);
        DepthSlider.setValue(0);
        panel1.add(DepthSlider, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 11, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        PAUSEButton = new JButton();
        PAUSEButton.setBackground(new Color(-9367));
        PAUSEButton.setText("| |");
        panel1.add(PAUSEButton, new com.intellij.uiDesigner.core.GridConstraints(5, 9, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(20, 20), null, 0, false));
        FWDButton = new JButton();
        FWDButton.setBackground(new Color(-24521));
        FWDButton.setText(">");
        panel1.add(FWDButton, new com.intellij.uiDesigner.core.GridConstraints(5, 10, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(20, 20), null, 0, false));
        FFWDButton = new JButton();
        FFWDButton.setBackground(new Color(-39680));
        FFWDButton.setText(">>");
        panel1.add(FFWDButton, new com.intellij.uiDesigner.core.GridConstraints(5, 11, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(20, 20), null, 0, false));
        TimeSlider = new JSlider();
        TimeSlider.setBackground(new Color(-13882324));
        TimeSlider.setForeground(new Color(-1));
        TimeSlider.setMajorTickSpacing(50);
        TimeSlider.setMaximum(200);
        TimeSlider.setPaintLabels(true);
        TimeSlider.setPaintTicks(true);
        TimeSlider.setValue(0);
        panel1.add(TimeSlider, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 8, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(6, 1, 1, 11, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 30), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 11, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 30), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(0, 12, 7, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, new Dimension(30, -1), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 7, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, new Dimension(30, -1), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer5 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer5, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 11, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 20), null, 0, false));
        addParticleButton = new JButton();
        addParticleButton.setBackground(new Color(-12929055));
        addParticleButton.setText("Reset Simulation");
        panel1.add(addParticleButton, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        toggleGravityButton = new JButton();
        toggleGravityButton.setBackground(new Color(-10362495));
        toggleGravityButton.setText("Toggle Gravity");
        panel1.add(toggleGravityButton, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer6 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer6, new com.intellij.uiDesigner.core.GridConstraints(3, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}
