package frc.robot.config;

import frc.robot.commands.actions.drivetrain.Drive;
import frc.robot.commands.actions.drivetrain.Orient;
import frc.robot.commands.actions.endeffector.Convey;
import frc.robot.commands.actions.endeffector.Intake;
import frc.robot.commands.actions.endeffector.Lift;
import frc.robot.commands.actions.endeffector.MoveNote;
import frc.robot.commands.actions.endeffector.Shoot;
import frc.robot.mortlib.commands.FlipOrFlop;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intaker;
import frc.robot.subsystems.Lifter;
import frc.robot.subsystems.Shooter;

import static frc.robot.config.Inputs.*;
import static frc.robot.config.constants.PhysicalConstants.Lifter.*;
import static frc.robot.config.constants.PhysicalConstants.Shooter.*;
import static frc.robot.config.constants.PhysicalConstants.Convey.*;
import static frc.robot.config.constants.PhysicalConstants.Intake.*;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class IO {
	private static Drivetrain drivetrain;
  private static Lifter lifter;
  private static Intaker intaker;
  private static Shooter shooter;
  private static Conveyor conveyor;

  public static FlipOrFlop lifterFlip;

    public static void init() {
		  drivetrain = Drivetrain.getInstance();
      lifter = Lifter.getInstance();
      intaker = Intaker.getInstance();
      shooter = Shooter.getInstance();
      conveyor = Conveyor.getInstance();

      lifterFlip = new FlipOrFlop();
    }

    public static void configure() {
      init();
      Inputs.init();

		  drivetrain.setDefaultCommand(
			  new Drive(Inputs::getJoystickX, Inputs::getJoystickY, Inputs::getJoystickTwist)
        //  new Drive(Inputs::getLeftControllerXSwerve, Inputs::getLeftControllerYSwerve, Inputs::getRightControllerXSwerve)
      );

      joystick.button(1).whileTrue(new Orient());

      joystick.button(2).whileTrue(new InstantCommand(() -> drivetrain.getSwerveDrive().resetPosition(
        new Pose2d(0, 0, Rotation2d.fromDegrees(0))
      )));

      // xboxController.a().onTrue(lifterFlip.FlipFlop(new Lift(LIFTER_DOWN), new Lift(LIFTER_UP), xboxController.a()));
      xboxController.a().onTrue(new Lift(LIFTER_DOWN));
      xboxController.b().onTrue(new Lift(LIFTER_UP));

      // xboxController.y().onTrue(new Convey(CONVEY_SPEED));
      // xboxController.x().onTrue(new Convey(0));

      // xboxController.y().onTrue(new Intake(INTAKE_SPEED));
      // xboxController.x().onTrue(new Intake(0));

      xboxController.y().onTrue(new MoveNote(INTAKE_SPEED, CONVEY_SPEED));
      xboxController.x().onTrue(new MoveNote(0, 0));

      xboxController.rightBumper().onTrue(new Shoot(SLOW_SHOOT));
      xboxController.leftBumper().onTrue(new Shoot(0));

      xboxController.start().onTrue(new MoveNote(OUTAKE_SPEED, BACKWARD_SPEED));
    }

    public static Boolean getIsBlue() {
		  return DriverStation.getAlliance().isPresent() ? DriverStation.getAlliance().get() == Alliance.Blue : true;
	  }
}
