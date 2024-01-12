package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.DoubleSupplier;
import frc.robot.subsystems.Drivetrain;

public class DriveControl extends CommandBase {
	private final Drivetrain drivetrain;

	private final DoubleSupplier translationXSupplier;
	private final DoubleSupplier translationYSupplier;
	private final DoubleSupplier rotationSupplier;

	public DriveControl(DoubleSupplier translationXSupplier, DoubleSupplier translationYSupplier,
			DoubleSupplier rotationSupplier) {
		drivetrain = Drivetrain.getInstance();
		this.translationXSupplier = translationXSupplier;
		this.translationYSupplier = translationYSupplier;
		this.rotationSupplier = rotationSupplier;

		addRequirements(drivetrain);
	}

	@Override
	public void execute() {

		// // robot-oriented drive
		drivetrain.drive(new ChassisSpeeds(translationXSupplier.getAsDouble(), translationYSupplier.getAsDouble(),
				rotationSupplier.getAsDouble()));
		// drivetrain.drive(
		// ChassisSpeeds.fromFieldRelativeSpeeds(
		// translationXSupplier.getAsDouble(),
		// translationYSupplier.getAsDouble(),
		// rotationSupplier.getAsDouble(),
		// drivetrain.getGyroscopeRotation())
		// );
	}

	@Override
	public void end(boolean interrupted) {
		drivetrain.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
	}
}
