package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.hardware.TalonFX;

import static frc.robot.util.Constants.Climber.RIGHT_CLIMBER_MOTOR_ID;
import static frc.robot.util.Constants.Climber.LEFT_CLIMBER_MOTOR_ID;

public class Climber extends SubsystemBase {
    private static Climber climber;

    private TalonFX leftClimber;
    private TalonFX rightClimber;

    // Climber constructor
    private Climber() {
        // Initialize the climber motors
        rightClimber = new TalonFX(RIGHT_CLIMBER_MOTOR_ID);
        leftClimber = new TalonFX(LEFT_CLIMBER_MOTOR_ID);
    }

    // Set both the left and the right climber motors to a specific value
    public void setMotors(double leftValue, double rightValue) {
        leftClimber.set(leftValue);
        rightClimber.set(rightValue);
    }

    // Get the current instance of the climber
    public static Climber getInstance() {
        // If the climber is equal to null, then an instance has not been created yet
        // If this is the case, then create an instance
        if (climber == null) {
            climber = new Climber();
        }

        return climber;
    }

    // Get a reference to the right climber motor
    public TalonFX getRightClimberMotor() {
        return rightClimber;
    }

    // Get a reference to the left climber motor
    public TalonFX getLeftClimberMotor() {
        return leftClimber;
    }
}
