package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;

import edu.wpi.first.math.controller.HolonomicDriveController;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveAtPath extends CommandBase {
	private Drivetrain drivetrain;
	private Trajectory trajectory;
	private HolonomicDriveController holonomicController;
	private Timer timer;
	private Rotation2d endRotation;

	ProfiledPIDController pp;

	/**
	 * @param traj
	 * @param rotation
	 */
	public DriveAtPath(Trajectory traj, Rotation2d rotation) {
		drivetrain = Drivetrain.getInstance();

		trajectory = traj;

		pp = new ProfiledPIDController(0.45, 0, 0.05, new TrapezoidProfile.Constraints(2, 1));
		pp.setTolerance(0.1);

		holonomicController = new HolonomicDriveController(new PIDController(-0.01, 0, 0),
				new PIDController(-0.01, 0, 0), pp);

		timer = new Timer();

		endRotation = rotation;
		addRequirements(drivetrain);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		timer.start();
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		ChassisSpeeds cs = holonomicController.calculate(drivetrain.getPose(), trajectory.sample(timer.get()),
				endRotation);

		System.out.println("setpoint: " + pp.getSetpoint().position);
		System.out.println("error: " + pp.getPositionError());

		// cs.omegaRadiansPerSecond = drivetrain.getRotateToAngleController()
		// .calculate(drivetrain.getGyroscopeRotation().getDegrees(),
		// endRotation.getDegrees() + drivetrain.getAngle());

		drivetrain.drive(cs);
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		timer.stop();
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return pp.atSetpoint();
	}
}
