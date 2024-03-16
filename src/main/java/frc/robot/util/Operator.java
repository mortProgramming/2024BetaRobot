// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.util;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.IntakeLifter;
import frc.robot.subsystems.IntakeRoller;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.IntakeConveyor;
import frc.robot.subsystems.Climber;

/** Add your docs here. */
public class Operator {
    private static final int JOYSTICK_PORT = 0;
    private static Joystick joystick = new Joystick(JOYSTICK_PORT);
    private static XboxController controller = new XboxController(2);
    private static final int XboxController = 0;

    private static IntakeLifter intakeLifter;
    private static IntakeRoller intakeRoller;
    private static Shooter shooter;
    private static IntakeConveyor intakeConveyor;
    private static Climber climber;

    public static boolean aButton() {
        return controller.getAButton();
    }

    public static boolean xButton() {
        return controller.getXButton();
    }

    public static double leftJoyStick() {
        return controller.getLeftY();
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

}
