package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;
import frc.robot.util.Constants.LimelightPipeline;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveToAprilTag extends CommandBase {

	Drivetrain drivetrain;
	Limelight limelight;
	private double id;

	public DriveToAprilTag(double id) {
		drivetrain = Drivetrain.getInstance();
		limelight = Limelight.getInstance();

		this.id = id;

		addRequirements(drivetrain, limelight);
	}

	@Override
	public void initialize() {
		limelight.setPipeline(LimelightPipeline.APRILTAG);
	}

	@Override
	public void execute() {
		drivetrain.drive(
				// TODO: adjust
				new ChassisSpeeds(-drivetrain.getXController().calculate(limelight.getZ(), -3),
						drivetrain.getYController().calculate(limelight.getX(), 0),
						-drivetrain.getThetaController().calculate(limelight.getYaw(), 0)));

	}

	@Override
	public boolean isFinished() {
		// return drivetrain.getYController().atSetpoint();
		return !limelight.hasTarget() || (drivetrain.getYController().atSetpoint()
				&& drivetrain.getXController().atSetpoint() && drivetrain.getThetaController().atSetpoint());
	}

	@Override
	public void end(boolean interrupted) {
		drivetrain.drive(new ChassisSpeeds(0, 0, 0));
	}
}
