package frc.robot.commands.Auton;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;
import frc.robot.util.Constants;
import frc.robot.util.HelperFunctions;

import static frc.robot.util.Constants.Drivetrain.DRIVETRAIN_WHEEL_CIRCUMFERENCE;

// THIS IS NOT DONE YET, DO NOT USE IT

public class DistanceDrive extends Command {
    private Drivetrain drivetrain;

    private double rotations;
    private double rotationsCompleted;

    private double xMetersPerSecond;
    private double yMetersPerSecond;

    private double lastEncoderAngle;
    private double currentEncoderAngle;

    public DistanceDrive(double time, double distanceInches, double directionDegrees) {
        // Get a reference to the drivetrain
        drivetrain = Drivetrain.getInstance();

        // Calculate the rotations of the wheels based on the distance we want to drive
        rotations = distanceInches / DRIVETRAIN_WHEEL_CIRCUMFERENCE;
        rotationsCompleted = 0;

        // Calculate the meters per second to drive the drivetrain
        xMetersPerSecond = (Units.inchesToMeters(distanceInches) * Math.sin(Math.toRadians(directionDegrees))) / time;
        yMetersPerSecond = (Units.inchesToMeters(distanceInches) * Math.cos(Math.toRadians(directionDegrees))) / time;

        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        // Get the initial encoder value of the front left wheel
        // We will use this value to track how many times the wheel rotates
        currentEncoderAngle = drivetrain.getFrontLeftSwerveModule().getDriveDistance();
    }

    @Override
    public void execute() {
        // Get the new encoder angle of the drive train wheel
        lastEncoderAngle = currentEncoderAngle;
        currentEncoderAngle = drivetrain.getFrontLeftSwerveModule().getDriveDistance();

        // The total rotations completed is the change in encoder angle divided by 360 degrees
        rotationsCompleted += HelperFunctions.getSignedAngleBetween(lastEncoderAngle, currentEncoderAngle) / 360.0;

        // Drive the drivetrain based on the set values
        drivetrain.drive(new ChassisSpeeds(xMetersPerSecond, yMetersPerSecond, 0));
    }

    @Override
    public void end(boolean interrupted) {
        // The command will end once the specified time has elapsed
        // Stop the drivetrain motors
        drivetrain.drive(new ChassisSpeeds(0, 0, 0));
    }

    @Override
    public boolean isFinished() {
        // If the number of rotations completed is equal to the rotations that we want to get to, the command should finish
        return rotationsCompleted >= rotations;
    }
}