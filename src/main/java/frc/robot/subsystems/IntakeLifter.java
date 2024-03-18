package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static frc.robot.util.Constants.DrivetrainMotors.INTAKE_LIFTER_MOTOR;
import static frc.robot.util.Constants.DrivetrainMotors.INTAKE_ROLLER_MOTOR;
import static frc.robot.util.Constants.Intake.*;

public class IntakeLifter extends SubsystemBase {
    private static CANSparkMax liftMotor;

    private static IntakeLifter intakeLifter;

    // Intake lifter constructor
    private IntakeLifter() {
        // Initialize the intake lifter motor
        liftMotor = new CANSparkMax(INTAKE_LIFTER_MOTOR, MotorType.kBrushless);
    }

    // Set the intake lifter motor to a specific value
    public void setMotor(double setValue) {
        liftMotor.set(setValue);
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
