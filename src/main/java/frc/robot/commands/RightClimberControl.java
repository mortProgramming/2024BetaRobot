package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.RightClimber;
import frc.robot.util.Operator;

public class RightClimberControl extends Command {
    private static RightClimber rightClimber;

    private double motorValue;

    public RightClimberControl() {
        rightClimber = RightClimber.getInstance();

        addRequirements(rightClimber);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        motorValue = 0;
    }

    @Override
    public void execute() {
      

       rightClimber.setMotor(0.35 * -Operator.rightJoyStick());
    }

    // Called when the command ends
    @Override
    public void end(boolean interrupted) {
    }

    // Whether or not the command is finished
    @Override
    public boolean isFinished() {
        return false;
    }
}
