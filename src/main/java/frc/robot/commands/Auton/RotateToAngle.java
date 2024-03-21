package frc.robot.commands.Auton;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;
import frc.robot.util.HelperFunctions;

// THIS IS NOT DONE YET, DO NOT USE IT

public class RotateToAngle extends Command {
    private Drivetrain drivetrain;

    private double angleDegrees;

    private double omegaRadiansPerSecond;

    public RotateToAngle(double time, double angleDegrees) {
        // Get a reference to the drivetrain
        drivetrain = Drivetrain.getInstance();

        // Set the target angle value
        this.angleDegrees = angleDegrees;

        // Calculate the radians per second to rotate the robot at
        omegaRadiansPerSecond = Math.toRadians(HelperFunctions.getSignedAngleBetween(
                drivetrain.getGyroscopeRotation().getDegrees(), angleDegrees)) / time;

        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        // Drive the drivetrain based on the set values
        drivetrain.drive(new ChassisSpeeds(0, 0, omegaRadiansPerSecond));
    }

    @Override
    public void end(boolean interrupted) {
        // Drive the drivetrain based on the set values
        drivetrain.drive(new ChassisSpeeds(0, 0, 0));
    }

    @Override
    public boolean isFinished() {
        // If the robot is rotating to the right AND the gyroscope angle is greater than the target angle degrees, the command should finish
        // If the robot is rotating to the left AND the gyroscope angle is less than the target angle degrees, the command should finish
        // If the gyroscope angle has not reached the target angle yet, keep running the command
        if (omegaRadiansPerSecond < 0 && drivetrain.getGyroscopeRotation().getDegrees() >= angleDegrees) {
            return true;
        } else if (omegaRadiansPerSecond > 0 && drivetrain.getGyroscopeRotation().getDegrees() <= angleDegrees) {
            return true;
        } else {
            return false;
        }
    }
}
