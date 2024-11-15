package frc.robot.commands.actions.drivetrain;

import frc.robot.mortlib.swerve.autons.BasicTimedDrive;
import frc.robot.subsystems.Drivetrain;

public class TimedDrive extends BasicTimedDrive {

  public TimedDrive(double time, double x, double y, double omega) {
    super(Drivetrain.getInstance(), Drivetrain.getInstance().getSwerveDrive(), time, x, y, omega);
  }

  public TimedDrive(double time, double x, double y, double omega, boolean fieldOriented) {
    super(Drivetrain.getInstance(), Drivetrain.getInstance().getSwerveDrive(), time, x, y, omega, fieldOriented);
  }
}
