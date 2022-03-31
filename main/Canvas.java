package main;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {

    public Canvas() {
        setPreferredSize(new Dimension(900, 600));
        setBackground(Color.white);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        PaintBrush brush = new PaintBrush(g);

        brush.drawSky();
        brush.drawGrass();
        brush.drawMountains();
        // drawing trees with x and y coordinates
        brush.drawTree(300, -10);
        brush.drawTree(260, 10);
        brush.drawTree(600, 20);
        brush.drawTree(720, 0);
        brush.drawTree(790, 45);
    }
}
