package frc.robot.config;

import frc.robot.commands.actions.Lift;
import frc.robot.commands.actions.drivetrain.Drive;
import frc.robot.commands.actions.drivetrain.Orient;
import frc.robot.mortlib.commands.FlipOrFlop;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Lifter;

import static frc.robot.config.Inputs.*;
import static frc.robot.config.constants.PhysicalConstants.Lifter.*;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class IO {
	private static Drivetrain drivetrain;
  private static Lifter lifter;

  public static FlipOrFlop lifterFlip;

    public static void init() {
		  drivetrain = Drivetrain.getInstance();
      lifter = Lifter.getInstance();

      lifterFlip = new FlipOrFlop();
    }

    public static void configure() {
      init();
      Inputs.init();

		  drivetrain.setDefaultCommand(
			  new Drive(Inputs::getJoystickX, Inputs::getJoystickY, Inputs::getJoystickTwist)
      );

      joystick.button(1).whileTrue(new Orient());

      joystick.button(2).whileTrue(new InstantCommand(() -> drivetrain.getSwerveDrive().resetPosition(
        new Pose2d(0, 0, Rotation2d.fromDegrees(0))
      )));

      // xboxController.a().onTrue(lifterFlip.FlipFlop(new Lift(LIFTER_DOWN), new Lift(LIFTER_UP), xboxController.a()));
      xboxController.a().onTrue(new Lift(LIFTER_DOWN));
      xboxController.b().onTrue(new Lift(LIFTER_UP));
    }

    public static Boolean getIsBlue () {
		return DriverStation.getAlliance().isPresent() ? DriverStation.getAlliance().get() == Alliance.Blue : true;
	}
}
