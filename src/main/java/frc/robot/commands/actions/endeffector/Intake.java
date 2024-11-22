package frc.robot.commands.actions.endeffector;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intaker;

public class Intake extends Command {
    private Intaker intake;
    private double shooterSpeed;

    private Timer timer;
    private double time;

    public Intake(double shooterSpeed) {
        this(0, shooterSpeed);
    }

    public Intake(double time, double shooterSpeed) {
        intake = Intaker.getInstance();
        timer = new Timer();

        this.shooterSpeed = shooterSpeed;
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
        intake.setSpeed(shooterSpeed);
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
