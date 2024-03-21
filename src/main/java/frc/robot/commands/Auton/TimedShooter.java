package frc.robot.commands.Auton;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.Shooter;

public class TimedShooter extends Command {
    private Shooter shooter;

    private Timer timer;

    private double motorValue;
    private double time;

    public TimedShooter(double time, double motorValue) {
        // Get a reference to the shooter
        shooter = Shooter.getInstance();

        // Create a new timer
        timer = new Timer();

        // Set the time for the command and the value of the motor
        this.time = time;
        this.motorValue = motorValue;

        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        // Reset and start the timer
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        // Set the shooter motor to the specified value
        shooter.setMotor(motorValue);
    }

    @Override
    public void end(boolean interrupted) {
        // The command will end once the specified time has elapsed
        // Stop the shooter motor
        // shooter.setMotor(0);
    }

    @Override
    public boolean isFinished() {
        // Once the timer's counted time is greater than the specified time, the command should finish
        return timer.get() > time;
    }
}
