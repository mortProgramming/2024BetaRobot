package frc.robot.commands.actions.endeffector;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Conveyor;

public class Convey extends Command {
    private Conveyor conveyor;
    private double conveyorSpeed;

    private Timer timer;
    private double time;

    public Convey(double conveyorSpeed) {
        this(0, conveyorSpeed);
    }

    public Convey(double time, double conveyorSpeed) {
        conveyor = Conveyor.getInstance();
        timer = new Timer();

        this.conveyorSpeed = conveyorSpeed;
        this.time = time;

        addRequirements(conveyor);
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }
    
    @Override
    public void execute() {
        conveyor.setSpeed(conveyorSpeed);
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
            conveyor.setSpeed(0);
        }
	}
}
