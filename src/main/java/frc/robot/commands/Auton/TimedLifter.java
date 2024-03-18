package frc.robot.commands.Auton;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.IntakeLifter;

public class TimedLifter extends Command {
    private IntakeLifter intakeLifter;

    private double lifterMotorSpeed;
    private double time;
    private Timer timer;

    public TimedLifter(double time, double lifterMotorSpeed) {
        intakeLifter = IntakeLifter.getInstance();
        timer = new Timer();
        this.time = time;
        this.lifterMotorSpeed = lifterMotorSpeed;
        addRequirements(intakeLifter);
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        intakeLifter.setMotor(lifterMotorSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        intakeLifter.setMotor(0);
    }

    @Override
    public boolean isFinished() {
        return timer.get() > time;
    }
}
