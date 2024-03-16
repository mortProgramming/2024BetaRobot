package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climber;
import frc.robot.util.Operator;

public class ClimberControl extends Command {
    private static Climber climber;

    private double motorValue;

    public ClimberControl() {
        climber = Climber.getInstance();

        addRequirements(climber);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        motorValue = 0;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (Operator.aButton() == true) {
            motorValue = -100;
        }

        if (Operator.xButton() == true) {
            motorValue = 0;
        }

        Climber.setMotor(motorValue);
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
