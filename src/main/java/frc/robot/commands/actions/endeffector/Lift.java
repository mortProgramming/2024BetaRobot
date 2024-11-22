package frc.robot.commands.actions.endeffector;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Lifter;

public class Lift extends Command {
    private Lifter lifter;

    private double armPosition;

    public Lift(DoubleSupplier armPosition) {
        this(armPosition.getAsDouble());
    }

    public Lift(double armPosition) {
        lifter = Lifter.getInstance();

        this.armPosition = armPosition;

        addRequirements(lifter);
    }
    
    @Override
    public void execute() {
        lifter.setPIDPosition(armPosition);
    }

    @Override
	public boolean isFinished() {
		return false;
	}
}
