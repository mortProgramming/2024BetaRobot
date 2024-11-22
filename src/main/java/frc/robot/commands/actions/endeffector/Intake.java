package frc.robot.commands.actions.endeffector;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intaker;

public class Intake extends Command {
    private Intaker intake;
    private double intakeSpeed;

    private Timer timer;
    private double time;

    public Intake(double intakeSpeed) {
        this(0, intakeSpeed);
    }

    public Intake(double time, double intakeSpeed) {
        intake = Intaker.getInstance();
        timer = new Timer();

        this.intakeSpeed = intakeSpeed;
        this.time = time;

        addRequirements(intake);
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }
    
    @Override
    public void execute() {
        intake.setSpeed(intakeSpeed);
    }

    @Override
	public boolean isFinished() {
		if(timer.get() >= time) {
            return true;
        }

        return false;
	}

    @Override
	public void end(boolean interrupted) {
		if(time != 0) {
            intake.setSpeed(0);
        }
	}
}
