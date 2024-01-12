package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RotateToAngle extends CommandBase {
	private Drivetrain drivetrain;
	private double angle;
	private boolean relative;

	public RotateToAngle(double angle, boolean relative) {
		drivetrain = Drivetrain.getInstance();
		this.angle = angle;
		this.relative = relative;

		addRequirements(drivetrain);
	}

	@Override
	public void initialize() {
		if (relative) {
			angle += drivetrain.getGyroscopeRotation().getDegrees();
		}
	}

	@Override
	public void execute() {
		drivetrain.drive(new ChassisSpeeds(0, 0, drivetrain.getRotateToAngleController()
				.calculate(drivetrain.getGyroscopeRotation().getDegrees(), angle)));
	}

	@Override
	public boolean isFinished() {
		return false;
	}

	@Override
	public void end(boolean interrupted) {
		drivetrain.drive(new ChassisSpeeds(0, 0, 0));
	}
}
