package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.IntakeRoller;
import frc.robot.util.Operator;

public class IntakeRollerControl extends Command {
    private IntakeRoller intakeRoller;

    public IntakeRollerControl() {
        intakeRoller = IntakeRoller.getInstance();

        addRequirements(intakeRoller);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        // Control the direction of the roller motors with the B and Y button
        // If neither button is pressed, then set the motor to 0
        if (Operator.getBButton()) {
            intakeRoller.setMotor(-0.50);
        } else if (Operator.getYButton()) {
            intakeRoller.setMotor(0.50);
        } else {
            intakeRoller.setMotor(0);
        }
    }

    @Override
    public void end(boolean interrupted) {
        // Once the command ends, set the intake roller motor to 0 (just in case)
        intakeRoller.setMotor(0);
    }
}
