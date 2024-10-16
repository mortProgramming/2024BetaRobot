package frc.robot.util;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

import static frc.robot.util.Constants.Control.CONTROLLER_PORT;
import static frc.robot.util.Constants.Control.JOYSTICK_PORT;
import static frc.robot.util.Constants.Control.OTHER_CONTROLLER_PORT;

import edu.wpi.first.wpilibj.PS5Controller;

public class Operator {
    private static Joystick joystick = new Joystick(JOYSTICK_PORT);
    private static XboxController controller = new XboxController(CONTROLLER_PORT);
    // private static PS5Controller psController = new PS5Controller(OTHER_CONTROLLER_PORT);

    private static XboxController otherController = new XboxController(OTHER_CONTROLLER_PORT);

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


    //ps5 stuff below here btw cuz michaels weird
    // public static boolean getCrossButton(){
    //     return psController.getCrossButton();
    // }
    // public static boolean getSquareButton(){
    //     return psController.getSquareButton();
    // }
    // public static boolean getCircleButton(){
    //     return psController.getCircleButton();
    // }
    // public static boolean getTriangleButton(){
    //     return psController.getTriangleButton();
    // }
    // public static double getLeftX(){
    //     return psController.getLeftX();
    // }
    
    // public static double getRightX() {
    //     return psController.getRightX();
    // }

    // public static double getLeftY() {
    //     return psController.getLeftY();
    // }

    // public static double getRightY() {
    //     return psController.getRightY();
    // }

    public static double getOtherLeftX() {
        return otherController.getLeftX();
    }

    public static double getOtherLeftY() {
        return otherController.getLeftY();
    }

    public static double getOtherRightX() {
        return otherController.getRightX();
    }

    public static double getOtherLeftTrigger() {
        return otherController.getLeftTriggerAxis();
    }

    public static double getOtherRightTrigger() {
        return otherController.getRightTriggerAxis();
    }

}
