package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class BalanceStation extends CommandBase {
	private Drivetrain drivetrain;

	public BalanceStation() {
		drivetrain = Drivetrain.getInstance();

		addRequirements(drivetrain);
	}

	@Override
	public void initialize() {

	}

	@Override
	public void execute() {

		if (Math.abs(drivetrain.getNavX().getPitch()) > 0.5) {
			drivetrain.drive(new ChassisSpeeds(0,
					drivetrain.getChargeStationController().calculate(-drivetrain.getNavX().getPitch()), 0));
		} else if (Math.abs(drivetrain.getNavX().getRoll()) > 0.5) {
			drivetrain.drive(new ChassisSpeeds(
					drivetrain.getChargeStationController().calculate(-drivetrain.getNavX().getRoll()), 0, 0));
		}

	}

	@Override
	public boolean isFinished() {
		return drivetrain.getChargeStationController().atSetpoint();
	}

	@Override
	public void end(boolean interrupted) {
		drivetrain.drive(new ChassisSpeeds(0, 0, 0));
	}
}
