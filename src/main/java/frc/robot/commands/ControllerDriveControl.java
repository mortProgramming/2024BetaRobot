package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import java.util.function.DoubleSupplier;
import frc.robot.subsystems.Drivetrain;
import frc.robot.util.Operator;

import static frc.robot.util.Constants.Drivetrain.*;

public class ControllerDriveControl extends Command {
    private Drivetrain drivetrain;

    private double throttle;

    private static double minThrottle = 0.2;
    private static double throttleChangeRate = 0.03;

    public ControllerDriveControl() {
        drivetrain = Drivetrain.getInstance();
        throttle = 1;
        
        addRequirements(drivetrain);
    }

    @Override
    public void execute() {

        throttle += ((-Operator.getOtherLeftTrigger() + 1 ) / 2) * -throttleChangeRate + 
        ((-Operator.getOtherRightTrigger() + 1) / 2) * throttleChangeRate;

        drivetrain.drive(ChassisSpeeds.fromFieldRelativeSpeeds(
            modifyAxis(Operator.getOtherLeftX() * MAX_VELOCITY_METERS_PER_SECOND, throttle),
            modifyAxis(Operator.getOtherLeftY() * MAX_VELOCITY_METERS_PER_SECOND, throttle), 
            modifyAxis(Operator.getOtherRightX() * MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND, throttle),
            drivetrain.getGyroscopeRotation()));

        SmartDashboard.putNumber("Drive Encoder Value:", drivetrain.getFrontLeftSwerveModule().getDriveDistance());
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        // Once the command ends, stop running all the drivetrain motors (just in case)
        drivetrain.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
    }

    public static double deadband(double value, double deadband) {

        if (Math.abs(value) > deadband) {
            //deadband is the region defined as +- deviation equal to 0

            if (value > 0.0) {
                return (value - deadband) / (1.0 - deadband);
            } else {
                return (value + deadband) / (1.0 - deadband);
            }
        }

        else {
            return 0.0;
        }
    }

    public static double modifyAxis(double value, double throttleValue) {
        value = deadband(value, 0.05);

        return value * (throttleValue * (1 - minThrottle) + minThrottle);
    }
}
