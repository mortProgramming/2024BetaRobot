package frc.robot.util;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

import static frc.robot.util.Constants.Control.CONTROLLER_PORT;
import static frc.robot.util.Constants.Control.JOYSTICK_PORT;

public class Operator {
    private static Joystick joystick = new Joystick(JOYSTICK_PORT);
    private static XboxController controller = new XboxController(CONTROLLER_PORT);

    public static boolean aButton() {
        return controller.getAButton();
    }

    public static boolean xButton() {
        return controller.getXButton();
    }

    public static double leftJoyStick() {
        return controller.getLeftY();
    }

    public static double rightJoyStick() {
        return controller.getRightY();
    }

    public static boolean bButton() {
        return controller.getBButton();
    }

    public static boolean yButton() {
        return controller.getYButton();
    }

    public static boolean rightBumper() {
        return controller.getRightBumper();
    }

    public static boolean leftBumper() {
        return controller.getLeftBumper();
    }

    public static boolean startButton() {
        return controller.getStartButton();
    }

    public static boolean backButton() {
        return controller.getBackButton();
    }
}
