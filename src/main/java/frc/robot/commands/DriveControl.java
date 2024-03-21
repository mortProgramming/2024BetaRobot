package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import java.util.function.DoubleSupplier;
import frc.robot.subsystems.Drivetrain;

public class DriveControl extends Command {
    private Drivetrain drivetrain;

    private DoubleSupplier translationXSupplier;
    private DoubleSupplier translationYSupplier;
    private DoubleSupplier rotationSupplier;

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
        // Drive the drivetrain based on the inputted values
        // The inputted values come from the joystick controls on the drive station
        drivetrain.drive(ChassisSpeeds.fromFieldRelativeSpeeds(translationXSupplier.getAsDouble(),
                translationYSupplier.getAsDouble(), rotationSupplier.getAsDouble(),
                drivetrain.getGyroscopeRotation()));

        SmartDashboard.putNumber("Drive Encoder Value:", drivetrain.getFrontLeftSwerveModule().getDriveDistance());
    }

    @Override
    public void end(boolean interrupted) {
        // Once the command ends, stop running all the drivetrain motors (just in case)
        drivetrain.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
    }
}