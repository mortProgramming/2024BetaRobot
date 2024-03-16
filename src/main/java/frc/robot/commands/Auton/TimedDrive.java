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
        drivetrain = Drivetrain.getInstance();
        timer = new Timer();

        this.time = time;
        this.x = x;
        this.y = -y;
        this.omega = omega;

        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        drivetrain.drive(new ChassisSpeeds(x, y, omega));
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.drive(new ChassisSpeeds(0, 0, 0));
    }

    @Override
    public boolean isFinished() {
        return timer.get() > time;
    }
}