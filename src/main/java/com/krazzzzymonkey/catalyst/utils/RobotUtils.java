/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.utils;

import java.awt.AWTException;
import java.awt.Robot;

public class RobotUtils
{
    public static void clickMouse(final int n) {
        AWTException ex;
        try {
            while (true) {
                switch (-1618887494 + 1169451830 + 1) {
                    case -822929414: {
                        continue;
                    }
                    default: {
                        final Robot robot = new Robot();
                        if (n == 0) {
                            robot.mousePress(16);
                            robot.mouseRelease(16);
                        }
                        else {
                            if (n != 1) {
                                return;
                            }
                            robot.mousePress(4096);
                            robot.mouseRelease(4096);
                        }
                        return;
                    }
                    case 600751360: {
                        throw null;
                    }
                }
            }
        }
        catch (AWTException ex2) {
            ex = ex2;
        }
        ex.printStackTrace();
    }
}
