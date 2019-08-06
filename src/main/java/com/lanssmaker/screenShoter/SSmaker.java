package com.lanssmaker.screenShoter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SSmaker {
    private Robot robot;

    public SSmaker() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage screenCapture() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle rectangle = new Rectangle(dimension);
        return robot.createScreenCapture(rectangle);
    }
}
