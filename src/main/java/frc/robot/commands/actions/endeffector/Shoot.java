package frc.robot.commands.actions.endeffector;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

public class Shoot extends Command {
    private Shooter shooter;
    private double shooterSpeed;

    private Timer timer;
    private double time;

    public Shoot(double shooterSpeed) {
        this(0, shooterSpeed);
    }

    public Shoot(double time, double shooterSpeed) {
        shooter = Shooter.getInstance();
        timer = new Timer();

        this.shooterSpeed = shooterSpeed;
        this.time = time;

        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }
    
    @Override
    public void execute() {
        shooter.setSpeed(shooterSpeed);
    }

    @Override
	public boolean isFinished() {
		if(time == 0) {
            return true;
        }

        if(timer.get() >= time) {
            return true;
        }

        return false;
	}

    @Override
	public void end(boolean interrupted) {
		if(time != 0) {
            shooter.setSpeed(0);
        }
	}
}
