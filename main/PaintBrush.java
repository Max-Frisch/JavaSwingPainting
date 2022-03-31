package main;

import java.awt.*;

public class PaintBrush {

    private static final Color ALIZARIN_CRIMSON = new Color(78,21,0);
    private static final Color BRIGHT_RED = new Color(219,0,0);
    private static final Color CADMIUM_YELLOW = new Color(255, 236,0);
    private static final Color DARK_SIENNA = new Color(95,46,31);
    private static final Color INDIAN_YELLOW = new Color(255, 184, 0);
    private static final Color MIDNIGHT_BLACK = new Color(0,0,0);
    private static final Color PHTHALO_BLUE = new Color(12,0,64);
    private static final Color PHTHALO_GREEN = new Color(16,46,60);
    private static final Color PRUSSIAN_BLUE = new Color(2,30,68);
    private static final Color SAP_GREEN = new Color(10,52,16);
    private static final Color TITANIUM_WHITE = new Color(255,255,255);
    private static final Color VAN_DYKE_BROWN = new Color(34,27,21);
    private static final Color YELLOW_OCHRE = new Color(199,155,0);
    private static final Color LIGHT_SKY_BLUE = new Color(80,207,250);

    private Graphics g;

    public PaintBrush(Graphics graphics) {
        g = graphics;
    }

    public void drawSky() {

        // paint a light blue sky
        g.setColor(LIGHT_SKY_BLUE);
        g.fillRect(0, 0, 900, 220);
        // add a yellow sun in the upper right area
        g.setColor(Color.yellow);
        g.fillOval(680, 30, 60, 60);

    }

    public void drawMountains() {
        // draw a mountain
        g.setColor(VAN_DYKE_BROWN);
        Polygon triangle = new Polygon();
        triangle.addPoint(15, 450);
        triangle.addPoint(390, 60);
        triangle.addPoint(550, 450);
        g.fillPolygon(triangle);

        //draw a second jagged mountain in foreground to the first mountain
        Color mountainMix = blend(VAN_DYKE_BROWN, INDIAN_YELLOW, 0.18f);
        g.setColor(mountainMix);
        Polygon jagged = new Polygon();
        jagged.addPoint(200, 450);
        jagged.addPoint(390, 260);
        jagged.addPoint(450, 320);
        jagged.addPoint(520, 180);
        jagged.addPoint(575, 310);
        jagged.addPoint(610, 240);
        jagged.addPoint(720, 490);
        g.fillPolygon(jagged);
    }

    public void drawTree(int positionX, int positionY) {
        // draw the trunk
        g.setColor(VAN_DYKE_BROWN);
        g.fillRect(positionX - 13, 410 + positionY, 26, 140);
        // draw the leaves or crown
        Color leafMix = blend(SAP_GREEN, PHTHALO_GREEN, 0.11f);
        g.setColor(leafMix);
        //draw five levels of leaves
        for (int i = 0; i < 5; i++) {
            Polygon triangle = new Polygon();
            float height = 50;
            float width = 70;
            float spacing = 15;
            float rate = 0.26f;
            float growth = 1 + (rate * i);

            triangle.addPoint(positionX, Math.round((310 + positionY) + (spacing * i))); // top
            triangle.addPoint(Math.round(positionX - (width / 2) * growth), Math.round((310 + positionY) + height + (spacing * i) * growth)); // bottom-left
            triangle.addPoint(Math.round(positionX + (width / 2) * growth), Math.round((310 + positionY) + height + (spacing * i) * growth)); // bottom-right

            // add the leaf triangle
            g.fillPolygon(triangle);
        }

    }

    public void drawGrass() {
        // create a color gradient from top to bottom
        Color grassFarMix = blend(SAP_GREEN, PHTHALO_GREEN, 0.414f);
        Color grassNearMix = blend(SAP_GREEN, TITANIUM_WHITE, 0.414f);
        GradientPaint grassMix = new GradientPaint(0,0,grassFarMix,0,500,grassNearMix);

        // casting Graphics to Graphics2D to use gradient
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(grassMix);
        g2.fillRect(0,220,900,600);
    }

    Color blend( Color c1, Color c2, float ratio ) {
        if ( ratio > 1f ) ratio = 1f;
        else if ( ratio < 0f ) ratio = 0f;
        float iRatio = 1.0f - ratio;

        int i1 = c1.getRGB();
        int i2 = c2.getRGB();

        int a1 = (i1 >> 24 & 0xff);
        int r1 = ((i1 & 0xff0000) >> 16);
        int g1 = ((i1 & 0xff00) >> 8);
        int b1 = (i1 & 0xff);

        int a2 = (i2 >> 24 & 0xff);
        int r2 = ((i2 & 0xff0000) >> 16);
        int g2 = ((i2 & 0xff00) >> 8);
        int b2 = (i2 & 0xff);

        int a = (int)((a1 * iRatio) + (a2 * ratio));
        int r = (int)((r1 * iRatio) + (r2 * ratio));
        int g = (int)((g1 * iRatio) + (g2 * ratio));
        int b = (int)((b1 * iRatio) + (b2 * ratio));

        return new Color( a << 24 | r << 16 | g << 8 | b );
    }
}
