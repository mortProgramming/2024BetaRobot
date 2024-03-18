package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climber;
import frc.robot.util.Operator;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static frc.robot.util.Constants.Climber.LEFT_CLIMBER_MAX;
import static frc.robot.util.Constants.Climber.LEFT_CLIMBER_MIN;
import static frc.robot.util.Constants.Climber.RIGHT_CLIMBER_MAX;
import static frc.robot.util.Constants.Climber.RIGHT_CLIMBER_MIN;

public class ClimberControl extends Command {
    private static Climber climber;

    private double motorValue;

    public ClimberControl() {
        climber = Climber.getInstance();

        addRequirements(climber);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        double leftMotorValue = 0.7 * -Operator.leftJoyStick();
        double rightMotorValue = 0.7 * Operator.rightJoyStick();

        // if (climber.getLeftClimberMotor().getPosition().getValueAsDouble() <=
        // LEFT_CLIMBER_MIN && leftMotorValue < 0){
        // leftMotorValue = 0;

        // } else if (climber.getLeftClimberMotor().getPosition().getValueAsDouble() >=
        // LEFT_CLIMBER_MAX && leftMotorValue > 0){
        // leftMotorValue = 0;
        // }

        // if (climber.getRightClimberMotor().getPosition().getValueAsDouble() >=
        // RIGHT_CLIMBER_MIN && rightMotorValue > 0){
        // rightMotorValue = 0;
        // } else if (climber.getRightClimberMotor().getPosition().getValueAsDouble() <=
        // RIGHT_CLIMBER_MAX && rightMotorValue < 0){
        // rightMotorValue = 0;
        // }

        SmartDashboard.putNumber("leftjoystick", leftMotorValue);
        SmartDashboard.putNumber("rightjoystick", rightMotorValue);

        climber.setMotors(leftMotorValue, rightMotorValue);

        SmartDashboard.putNumber("rightclimberpid", climber.getRightClimberMotor().getPosition().getValueAsDouble());

        SmartDashboard.putNumber("leftclimberpid", climber.getLeftClimberMotor().getPosition().getValueAsDouble());

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        climber.setMotors(0, 0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
