package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeConveyor;
import frc.robot.util.Operator;

public class IntakeConveyorControl extends Command {
    private IntakeConveyor intakeConveyor;

    public IntakeConveyorControl() {
        intakeConveyor = IntakeConveyor.getInstance();

        addRequirements(intakeConveyor);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        // Control the direction of the conveyor motors with the B and Y button
        // If neither button is pressed, then set the motor to 0
        if (Operator.getBButton()) {
            intakeConveyor.setMotor(-0.50);
        } else if (Operator.getYButton()) {
            intakeConveyor.setMotor(0.5);
        } else {
            intakeConveyor.setMotor(0);
        }
    }

    @Override
    public void end(boolean interrupted) {
        // Once the command ends, set the intake conveyor motor to 0 (just in case)
        intakeConveyor.setMotor(0);
    }
}