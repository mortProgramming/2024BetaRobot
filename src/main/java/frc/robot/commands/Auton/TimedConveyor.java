package frc.robot.commands.Auton;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.IntakeConveyor;

public class TimedConveyor extends Command {
    private IntakeConveyor intakeConveyor;

    private Timer timer;

    private double motorSpeed;
    private double time;

    public TimedConveyor(double time, double motorSpeed) {
        // Get a reference to the intake conveyor
        intakeConveyor = IntakeConveyor.getInstance();

        // Create a new timer
        timer = new Timer();

        // Set the time for the command and the value of the motor
        this.time = time;
        this.motorSpeed = motorSpeed;

        addRequirements(intakeConveyor);
    }

    @Override
    public void initialize() {
        // Reset and start the timer
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        // Set the intake conveyor motor to the specified value
        intakeConveyor.setMotor(motorSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        // The command will end once the specified time has elapsed
        // Stop the intake conveyor motor
        intakeConveyor.setMotor(0);
    }

    @Override
    public boolean isFinished() {
        // Once the timer's counted time is greater than the specified time, the command should finish
        return timer.get() > time;
    }

}