package frc.robot;

import com.pathplanner.lib.*;
import com.pathplanner.lib.path.PathConstraints;
import com.pathplanner.lib.path.PathPlannerTrajectory;
import com.pathplanner.lib.util.PIDConstants;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import static frc.robot.util.Constants.DrivetrainSpecs.*;
import static frc.robot.util.Constants.OperatorConstants.*;

import java.util.ArrayList;
import java.util.HashMap;
import com.swervedrivespecialties.swervelib.*;

import frc.robot.commands.BalanceStation;
import frc.robot.commands.DriveControl;
import frc.robot.commands.DriveToAprilTag;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;

public class RobotContainer {
	private final Drivetrain drivetrain = Drivetrain.getInstance();
	// private final Auto auto = Auto.getInstance();
	private final Limelight limelight = Limelight.getInstance();

	// private final XboxController xboxController = new
	// XboxController(CONTROLLER_PORT);
	private final Joystick joystick = new Joystick(JOYSTICK_PORT);

	private DigitalInput sensor;

	public RobotContainer() {
		drivetrain.setDefaultCommand(new DriveControl(
				() -> -modifyAxis(joystick.getX(), joystick.getThrottle()) * MAX_VELOCITY_METERS_PER_SECOND,
				() -> -modifyAxis(joystick.getY(), joystick.getThrottle()) * MAX_VELOCITY_METERS_PER_SECOND,
				() -> -modifyAxis(joystick.getTwist(), joystick.getThrottle())
						* MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND));

		configureButtonBindings();
		drivetrain.resetPose(new Pose2d(0, 0, new Rotation2d(0, 0)));

		sensor = new DigitalInput(0);
		// drivetrain.zeroGyroscope();
	}

	public void displaySmartDashboard() {
		SmartDashboard.putNumber("compass", drivetrain.getCompass());
		SmartDashboard.putNumber("angle", drivetrain.getGyroscopeRotation().getDegrees());
		SmartDashboard.putNumber("yaw", drivetrain.getNavX().getYaw());
		SmartDashboard.putNumber("pitch", drivetrain.getNavX().getPitch());
		SmartDashboard.putNumber("roll", drivetrain.getNavX().getRoll());

		SmartDashboard.putBoolean("ir sensor", sensor.get());

	}

	/**
	 *
	 */
	private void configureButtonBindings() {
		// new Button(joystick::getTrigger).whenPressed(new
		// InstantCommand(drivetrain::zeroGyroscope));
		new Trigger(joystick::getTrigger).whileTrue(new InstantCommand(drivetrain::zeroGyroscope));
		new Trigger(() -> joystick.getRawButton(2)).whileTrue(new BalanceStation());
		new Trigger(() -> joystick.getRawButton(3)).whileTrue(
				new InstantCommand(() -> drivetrain.drive(new ChassisSpeeds(SmartDashboard.getNumber("vx", 0),
						SmartDashboard.getNumber("vy", 0), SmartDashboard.getNumber("omega", 0)))));

		// new Trigger(joystick::getTrigger).whileTrue(new DriveToAprilTag(1));
		// new Trigger(joystick::getTrigger).whileTrue(new RotateToAngle(90, false));

	}

	public Command getAutonomousCommand() {
		// ArrayList<PathPlannerTrajectory> pathGroup = (ArrayList<PathPlannerTrajectory>) PathPlanner
		// 		.loadPathGroup("Test7", new PathConstraints(2, 1,0,0));

		// // This is just an example event map. It would be better to have a constant,
		// // global event map
		// // in your code that will be used by all path following commands.
		HashMap<String, Command> eventMap = new HashMap<>();
		eventMap.put("tag", new DriveToAprilTag(0));
		eventMap.put("balance", new BalanceStation());

		// // Create the AutoBuilder. This only needs to be created once when robot code
		// // starts, not every time you want to create an auto command. A good place to
		// // put this is in RobotContainer along with your subsystems.
		// SwerveAutoBuilder autoBuilder = new SwerveAutoBuilder(drivetrain::getPose, // Pose2d supplier
		// 		drivetrain::resetPose, // Pose2d consumer, used to reset odometry at the beginning of auto
		// 		drivetrain.driveKinematics, // SwerveDriveKinematics
		// 		new PIDConstants(5.0, 0.0, 0.0), // PID constants to correct for translation error (used to create the X
		// 											// and Y PID controllers)
		// 		new PIDConstants(0.5, 0.0, 0.0), // PID constants to correct for rotation error (used to create the
		// 											// rotation controller)
		// 		drivetrain::setModuleStates, // Module states consumer used to output to the drive subsystem
		// 		eventMap, false, // Should the path be automatically mirrored depending on alliance color.
		// 							// Optional, defaults to true
		// 		drivetrain // The drive subsystem. Used to properly set the requirements of path following
		// 					// commands
		// );

		return null;

		// todo: zero gyro??

		// return autoBuilder.fullAuto(pathGroup);
	}

	private static double deadband(double value, double deadband) {
		if (Math.abs(value) > deadband) {
			if (value > 0.0) {
				return (value - deadband) / (1.0 - deadband);
			} else {
				return (value + deadband) / (1.0 - deadband);
			}
		} else {
			return 0.0;
		}
	}

	private static double modifyAxis(double value, double throttleValue) {
		// Deadband
		value = deadband(value, 0.1);

		// Square the axis
		value = Math.copySign(value * value, value);

		// takes the throttle value and takes it from [-1, 1] to [0.2, 1], and
		// multiplies it by the
		// value
		return value * (throttleValue * -0.4 + 0.6);
	}

}
