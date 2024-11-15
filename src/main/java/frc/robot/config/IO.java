package frc.robot.config;

import frc.robot.commands.actions.drivetrain.Drive;
import frc.robot.subsystems.Drivetrain;
import static frc.robot.config.Inputs.*;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.InstantCommand;

import static frc.robot.config.constants.PhysicalConstants.Drivetrain.*;

public class IO {

	private static Drivetrain drivetrain;

    public static void init() {
		drivetrain = Drivetrain.getInstance();
    }

    public static void configure() {
      init();
      Inputs.init();

		  drivetrain.setDefaultCommand(
			  new Drive(Inputs::getJoystickX, Inputs::getJoystickY, Inputs::getJoystickTwist)
      );
        // drivetrain.setDefaultCommand(
        //     new Drive(Inputs::getLeftControllerXSwerve, Inputs::getLeftControllerYSwerve, Inputs::getRightControllerXSwerve)
        // );

      joystick.button(0).whileTrue(drivetrain.setGyroscopeZero(IMU_TO_ROBOT_FRONT_ANGLE));

      joystick.button(1).whileTrue(new InstantCommand(() -> drivetrain.getSwerveDrive().resetPosition(
        new Pose2d(0, 0, Rotation2d.fromDegrees(0))
      )));
    }

    public static Boolean getIsBlue () {
		return DriverStation.getAlliance().isPresent() ? DriverStation.getAlliance().get() == Alliance.Blue : true;
	}
}
