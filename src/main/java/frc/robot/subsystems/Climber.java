package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.controller.PIDController;

import static frc.robot.util.Constants.DrivetrainMotors.RIGHT_CLIMBER;
import static frc.robot.util.Constants.DrivetrainMotors.LEFT_CLIMBER;

public class Climber extends SubsystemBase {
    private static Climber climber;

    private TalonFX leftClimber;
    private TalonFX rightClimber;

    private Climber() {
        // Initialize the climber motors
        rightClimber = new TalonFX(RIGHT_CLIMBER);
        leftClimber = new TalonFX(LEFT_CLIMBER);
    }

    // Set both the left and the right climber motors to a specific value
    public void setMotors(double setLeftValue, double setRightValue) {
        leftClimber.set(setLeftValue);
        rightClimber.set(setRightValue);
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
