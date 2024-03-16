package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LeftClimber;
import frc.robot.util.Operator;

public class LeftClimberControl extends Command {
    private static LeftClimber leftClimber;

    private double motorValue;

    public LeftClimberControl() {
       leftClimber = LeftClimber.getInstance();

        addRequirements(leftClimber);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        motorValue = 0;
    }

    @Override
    public void execute() {
      

      leftClimber.setMotor(0.35 * -Operator.leftJoyStick());
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
