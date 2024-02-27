package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import java.util.function.DoubleSupplier;
import frc.robot.subsystems.Drivetrain;

public class DriveControl extends Command {
	private final Drivetrain drivetrain;

	private final DoubleSupplier translationXSupplier;
	private final DoubleSupplier translationYSupplier;
	private final DoubleSupplier rotationSupplier;

	private boolean fieldOriented;


	public DriveControl(DoubleSupplier translationXSupplier, DoubleSupplier translationYSupplier,
			DoubleSupplier rotationSupplier, boolean fieldOriented) {
		drivetrain = Drivetrain.getInstance();
		this.translationXSupplier = translationXSupplier;
		this.translationYSupplier = translationYSupplier;
		this.rotationSupplier = rotationSupplier;
		this.fieldOriented = fieldOriented;
		addRequirements(drivetrain);
	}


	

    




    @Override
	public void execute() {

		// // robot-oriented drive
		//drivetrain.drive(new ChassisSpeeds(translationXSupplier.getAsDouble(), translationYSupplier.getAsDouble(),
				//rotationSupplier.getAsDouble()));
		// drivetrain.drive(
		// ChassisSpeeds.fromFieldRelativeSpeeds(
		// translationXSupplier.getAsDouble(),
		// translationYSupplier.getAsDouble(),
		// rotationSupplier.getAsDouble(),
		// drivetrain.getGyroscopeRotation())
		// );
		if (fieldOriented) {
			drivetrain.drive(ChassisSpeeds.fromFieldRelativeSpeeds(translationXSupplier.getAsDouble(),
					translationYSupplier.getAsDouble(), rotationSupplier.getAsDouble(),
					drivetrain.getGyroscopeRotation()));
		} else {
			drivetrain.drive(new ChassisSpeeds(translationXSupplier.getAsDouble(), translationYSupplier.getAsDouble(),
					rotationSupplier.getAsDouble()));
		}

	}
	
@Override
	public boolean isFinished() {
		return false;
	}
	
	@Override
	public void end(boolean interrupted) {
		drivetrain.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
	}


}