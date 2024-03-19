package frc.robot.commands.Auton;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Timer;

public class TimedDrive extends Command {
    private Drivetrain drivetrain;

    private Timer timer;

    private double time;
    private double x;
    private double y;
    private double omega;

    public TimedDrive(double time, double x, double y, double omega) {
        // Get a reference to the drivetrain
        drivetrain = Drivetrain.getInstance();

        // Create a new timer
        timer = new Timer();

        // Set the time and the horizontal, vertial, and rotational values
        this.time = time;
        this.x = x;
        this.y = -y;
        this.omega = omega;

        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        // Reset and start the timer
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        // Drive the drivetrain based on the set values
        drivetrain.drive(new ChassisSpeeds(x, y, omega));
    }

    @Override
    public void end(boolean interrupted) {
        // The command will end once the specified time has elapsed
        // Stop the drivetrain motors
        drivetrain.drive(new ChassisSpeeds(0, 0, 0));
    }

    @Override
    public boolean isFinished() {
        // Once the timer's counted time is greater than the specified time, the command should finish
        return timer.get() > time;
    }
}