package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.util.Constants.Shooter.RIGHT_SHOOTER_MOTOR_ID;
import static frc.robot.util.Constants.Shooter.LEFT_SHOOTER_MOTOR_ID;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

public class Shooter extends SubsystemBase {
    private static Shooter shooter;

    private CANSparkMax leftShooter;
    private CANSparkMax rightShooter;

    // Shooter constructor
    private Shooter() {
        // Initialize the shooter motors
        rightShooter = new CANSparkMax(RIGHT_SHOOTER_MOTOR_ID, MotorType.kBrushless);
        leftShooter = new CANSparkMax(LEFT_SHOOTER_MOTOR_ID, MotorType.kBrushless);

        // Set the right shooter motor to follow the left shooter motor
       rightShooter.follow(leftShooter, true);
    }           

    // Set the shooter motors to a specific value
    // Since we are using the follow method, we only need to set the left motor to set both of them
    public void setMotor(double value) {
        leftShooter.set(value);
    }

    // Get an instance to the current shooter
    public static Shooter getInstance() {
        // If the shooter is equal to null, then an instance has not been created yet
        // If this is the case, then create an instance
        if (shooter == null) {
            shooter = new Shooter();
        }

        return shooter;
    }

    // Get a reference to the shooter motor
    public Shooter getShooterMotor() {
        return shooter;
    }
}