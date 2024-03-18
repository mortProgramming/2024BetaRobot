package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;
import frc.robot.util.Operator;

public class ShooterControl extends Command {
    private static Shooter shooter;

    private double motorValue;

    public ShooterControl() {
        shooter = Shooter.getInstance();

        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        motorValue = 0;
    }

    @Override
    public void execute() {
        if (Operator.rightBumper()) {
            motorValue = -100;
        }

        if (Operator.leftBumper()) {
            motorValue = -40;
        }

        if (Operator.xButton()) {
            motorValue = 0;
        }

        shooter.setMotor(motorValue);
    }

    @Override
    public void end(boolean interrupted) {
        shooter.setMotor(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
