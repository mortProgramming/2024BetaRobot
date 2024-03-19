package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeLifter;
import frc.robot.util.Operator;

import static frc.robot.util.Constants.Intake.LIFTER_UP;
import static frc.robot.util.Constants.Intake.LIFTER_DOWN;

public class IntakeLifterControl extends Command {
    private IntakeLifter intakeLifter;

    private boolean oldButtonValue;
    private boolean newButtonValue;
    private double targetEncoderValue;

    public IntakeLifterControl() {
        intakeLifter = IntakeLifter.getInstance();

        oldButtonValue = false;
        newButtonValue = false;
        targetEncoderValue = LIFTER_UP;

        addRequirements(intakeLifter);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        // Set the old button value to what the new button value is, then get a new button value from the A button
        oldButtonValue = newButtonValue;
        newButtonValue = Operator.getAButton();

        // If the A button was not pressed last loop AND is currently pressed this loop, then swap the target encoder value
        if (oldButtonValue == false && newButtonValue == true) {
            // If the current target encoder value is the lifter's up position, then switch the target encoder value to be the lifter's down position
            // If the current target encoder value is the lifter's down position, then switch the target encoder value to be the lifter's up position
            if (targetEncoderValue == LIFTER_UP) {
                targetEncoderValue = LIFTER_DOWN;
            } else if (targetEncoderValue == LIFTER_DOWN) {
                targetEncoderValue = LIFTER_UP;
            }
        }

        // Get the current encoder value of the intake motor
        double motorEncoderValue = intakeLifter.getLifterMotor().getEncoder().getPosition();

        // If the target encoder value is the lifter's down position AND the encoder value is not equal to the target encoder value, move the intake position
        // If the target encoder value is the lifter's up position AND the encoder value is not equal to the target encoder value, move the intake position
        // If the current encoder value is at the target encoder value, then stop moving the motor
        if (targetEncoderValue == LIFTER_DOWN && motorEncoderValue < targetEncoderValue) {
            intakeLifter.setMotor(0.5);
        } else if (targetEncoderValue == LIFTER_UP && motorEncoderValue > targetEncoderValue) {
            intakeLifter.setMotor(-0.5);
        } else {
            intakeLifter.setMotor(0);
        }
    }

    @Override
    public void end(boolean interrupted) {
        // Once the command ends, set the intake lifter motor to 0 (just in case)
        intakeLifter.setMotor(0);
    }
}