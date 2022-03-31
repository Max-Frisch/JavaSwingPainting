package main;

import javax.swing.*;

public class App {

    public static void main(String[] args) {
        // create a window frame and set the title bar in the toolbar
        JFrame window = new JFrame("Art");
        // the program stops/terminates when the window is closed
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create a canvas to draw on
        MyCanvas canvas = new MyCanvas();
        // add the canvas to the window
        window.add(canvas);

        // fit the window size around components (the canvas)
        window.pack();
        // don't allow the window to be resized
        window.setResizable(false);
        // open the window in the center of the screen
        window.setLocationRelativeTo(null);
        // display the window
        window.setVisible(true);
    }
}
