package frc.robot.commands.Auton;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeLifter;

import static frc.robot.util.Constants.Intake.LIFTER_DOWN;
import static frc.robot.util.Constants.Intake.LIFTER_UP;

public class SetLifter extends Command {
    private IntakeLifter intakeLifter;

    private double targetEncoderValue;
    private double motorEncoderValue;
    private double motorValue;

    public SetLifter(double targetEncoderValue) {
        // Get a reference to the intake lifter
        intakeLifter = IntakeLifter.getInstance();

        // Set the target encoder value
        this.targetEncoderValue = targetEncoderValue;

        addRequirements(intakeLifter);
    }

    @Override
    public void initialize() {
        // *** This code is copied straight from the IntakeLifterControl class, it might be smart to put this code inside the subsystem class to prevent having to copy it :)

        // Get the current encoder value of the intake motor
        motorEncoderValue = intakeLifter.getLifterMotor().getEncoder().getPosition();

        // If the target encoder value is the lifter's down position AND the encoder value is not equal to the target encoder value, move the intake position
        // If the target encoder value is the lifter's up position AND the encoder value is not equal to the target encoder value, move the intake position
        if (targetEncoderValue == LIFTER_DOWN && motorEncoderValue < targetEncoderValue) {
            motorValue = 0.5;
        } else if (targetEncoderValue == LIFTER_UP && motorEncoderValue > targetEncoderValue) {
            motorValue = -0.5;
        }
    }

    @Override
    public void execute() {
        // Set the intake lifter motor to the specified value
        intakeLifter.setMotor(motorValue);
    }

    @Override
    public void end(boolean interrupted) {
        // Once the command finishes, set the intake lifter motor to 0 (just in case)
        intakeLifter.setMotor(0);
    }

    @Override
    public boolean isFinished() {
        // If the current motor encoder value has reached the target encoder value, then the command is finished
        // If it has not reached it yet, then it is not finished and the intake lifter motor should keep running
        if (targetEncoderValue == LIFTER_DOWN && motorEncoderValue >= targetEncoderValue) {
            return true;
        } else if (targetEncoderValue == LIFTER_UP && motorEncoderValue <= targetEncoderValue) {
            return true;
        } else {
            return false;
        }
    }
}
