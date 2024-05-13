package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climber;
import frc.robot.util.Operator;

import static frc.robot.util.Constants.Climber.LEFT_CLIMBER_MAX;
import static frc.robot.util.Constants.Climber.LEFT_CLIMBER_MIN;
import static frc.robot.util.Constants.Climber.RIGHT_CLIMBER_MAX;
import static frc.robot.util.Constants.Climber.RIGHT_CLIMBER_MIN;

public class ClimberControl extends Command {
    private Climber climber;

    // Constructor for the climber control command
    public ClimberControl() {
        // Get the current instance of the climber
        climber = Climber.getInstance();

        addRequirements(climber);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        // Get the initial left and right motor values based on the controller joystick values
    double leftMotorValue = 0;
    double rightMotorValue = 0;
       

        if (Operator.getLeftJoystickY() < 0.05 && Operator.getLeftJoystickY() > -0.05) {
            leftMotorValue = 0;
        }        else{
             leftMotorValue = 0.7 * -Operator.getLeftJoystickY();
        }

        if (Operator.getRightJoystickY() < 0.05 && Operator.getRightJoystickY() > -0.05) {
             rightMotorValue = 0;
        }        else{
             rightMotorValue = 0.7 * Operator.getRightJoystickY();
        }

        // Get the left and right climber motor encoder values
        // double leftMotorEncoderValue = climber.getLeftClimberMotor().getPosition().getValueAsDouble();
        // double rightMotorEncoderValue = climber.getRightClimberMotor().getPosition().getValueAsDouble();

        // If the left climber is at its lowest position AND we are trying to move it more down, prevent the motor from moving
        // If the left climber is at its maximum position AND we are trying to move it more up, prevent the motor from moving
        // You can prevent the motor from moving by setting the left motor value to 0
        // if (leftMotorEncoderValue <= LEFT_CLIMBER_MIN && leftMotorValue < 0) {
        //     leftMotorValue = 0;
        // } else if (leftMotorEncoderValue >= LEFT_CLIMBER_MAX && leftMotorValue > 0) {
        //     leftMotorValue = 0;
        // }

        // If the right climber is at its lowest position AND we are trying to move it more down, prevent the motor from moving
        // If the right climber is at its maximum position AND we are trying to move it more up, prevent the motor from moving
        // You can prevent the motor from moving by setting the right motor value to 0
        // if (rightMotorEncoderValue >= RIGHT_CLIMBER_MIN && rightMotorValue > 0) {
        //     rightMotorValue = 0;
        // } else if (rightMotorEncoderValue <= RIGHT_CLIMBER_MAX && rightMotorValue < 0) {
        //     rightMotorValue = 0;
        // }

        // Set the climber motor values
        climber.setMotors(leftMotorValue, rightMotorValue);
    }

    @Override
    public void end(boolean interrupted) {
        // Once the command ends, stop the climber motors (just in case)
        climber.setMotors(0, 0);
    }
}
