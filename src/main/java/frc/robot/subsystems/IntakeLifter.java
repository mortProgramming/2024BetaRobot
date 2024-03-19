package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static frc.robot.util.Constants.Intake.LIFTER_MOTOR_ID;

public class IntakeLifter extends SubsystemBase {
    private static CANSparkMax liftMotor;

    private static IntakeLifter intakeLifter;

    // Intake lifter constructor
    private IntakeLifter() {
        // Initialize the intake lifter motor
        liftMotor = new CANSparkMax(LIFTER_MOTOR_ID, MotorType.kBrushless);
    }

    // Set the intake lifter motor to a specific value
    public void setMotor(double value) {
        liftMotor.set(value);
    }

    // Get the current instance of the intake lifter
    public static IntakeLifter getInstance() {
        // If the intake lifter is equal to null, then an instance has not been created yet
        // If this is the case, then create an instance
        if (intakeLifter == null) {
            intakeLifter = new IntakeLifter();
        }

        return intakeLifter;
    }

    // Get a reference to the intake lifter motor
    public CANSparkMax getLifterMotor() {
        return liftMotor;
    }
}
