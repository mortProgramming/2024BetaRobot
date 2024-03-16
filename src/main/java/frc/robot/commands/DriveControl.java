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

    private boolean isFieldOriented;

    public DriveControl(DoubleSupplier translationXSupplier, DoubleSupplier translationYSupplier,
            DoubleSupplier rotationSupplier, boolean isFieldOriented) {
        drivetrain = Drivetrain.getInstance();

        this.translationXSupplier = translationXSupplier;
        this.translationYSupplier = translationYSupplier;
        this.rotationSupplier = rotationSupplier;
        this.isFieldOriented = isFieldOriented;

        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        if (isFieldOriented) {
            drivetrain.drive(ChassisSpeeds.fromFieldRelativeSpeeds(translationXSupplier.getAsDouble(),
                    translationYSupplier.getAsDouble(), rotationSupplier.getAsDouble(),
                    drivetrain.getGyroscopeRotation()));
        } else {
            drivetrain.drive(new ChassisSpeeds(translationXSupplier.getAsDouble(), translationYSupplier.getAsDouble(),
                    rotationSupplier.getAsDouble()));
        }
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}