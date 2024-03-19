package frc.robot.commands.Auton;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.IntakeRoller;

public class TimedRoller extends Command {
    private IntakeRoller intakeRoller;

    private double motorValue;

    private double time;
    private Timer timer;

    public TimedRoller(double time, double motorValue) {
        // Get a reference to the intake roller
        intakeRoller = IntakeRoller.getInstance();

        // Create a new timer
        timer = new Timer();

        // Set the time for the command and the value of the motor
        this.time = time;
        this.motorValue = motorValue;

        addRequirements(intakeRoller);
    }

    @Override
    public void initialize() {
        // Reset and start the timer
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        // Set the intake roller motor to the specified value
        intakeRoller.setMotor(motorValue);
    }

    @Override
    public void end(boolean interrupted) {
        // The command will end once the specified time has elapsed
        // Stop the intake roller motor
        intakeRoller.setMotor(0);
    }

    @Override
    public boolean isFinished() {
        // Once the timer's counted time is greater than the specified time, the command should finish
        return timer.get() > time;
    }
}
