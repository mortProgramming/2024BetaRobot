package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;
import frc.robot.util.Operator;

public class ShooterControl extends Command {
    private static Shooter shooter;

    private double motorValue;

    public ShooterControl() {
        shooter = Shooter.getInstance();

        motorValue = 0;

        addRequirements(shooter);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        // If the right bumper is pressed, set the shooter to max power
        // If the left bumper is pressed, set the shooter to half power
        // If the X button is pressed, stop the shooter
        if (Operator.getRightBumper()) {
            motorValue = -100;
        } else if (Operator.getLeftBumper()) {
            motorValue = -50;
        } else if (Operator.getXButton()) {
            motorValue = 0;
        }

        // Set the shooter motor based on the value above
        shooter.setMotor(motorValue);
    }

    @Override
    public void end(boolean interrupted) {
        // Once the command ends, set the shooter motor to 0 (just in case)
        shooter.setMotor(0);
    }
}
