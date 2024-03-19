package frc.robot.util;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

import static frc.robot.util.Constants.Control.CONTROLLER_PORT;
import static frc.robot.util.Constants.Control.JOYSTICK_PORT;

public class Operator {
    private static Joystick joystick = new Joystick(JOYSTICK_PORT);
    private static XboxController controller = new XboxController(CONTROLLER_PORT);

    public static boolean getAButton() {
        return controller.getAButton();
    }

    public static boolean getXButton() {
        return controller.getXButton();
    }

    public static double getLeftJoystickY() {
        return controller.getLeftY();
    }

    public static double getRightJoystickY() {
        return controller.getRightY();
    }

    public static boolean getBButton() {
        return controller.getBButton();
    }

    public static boolean getYButton() {
        return controller.getYButton();
    }

    public static boolean getRightBumper() {
        return controller.getRightBumper();
    }

    public static boolean getLeftBumper() {
        return controller.getLeftBumper();
    }

    public static boolean getStartButton() {
        return controller.getStartButton();
    }

    public static boolean getBackButton() {
        return controller.getBackButton();
    }
}
