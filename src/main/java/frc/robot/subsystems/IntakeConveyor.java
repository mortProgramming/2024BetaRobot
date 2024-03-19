package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import static frc.robot.util.Constants.Intake.CONVEYOR_MOTOR_ID;

public class IntakeConveyor extends SubsystemBase {
    private static IntakeConveyor intakeConveyor;

    private CANSparkMax conveyorMotor;

    // Intake constructor 
    private IntakeConveyor() {
        // Initialize the intake motor
        conveyorMotor = new CANSparkMax(CONVEYOR_MOTOR_ID, MotorType.kBrushless);
    }

    // Set the intake motor to a specific value
    public void setMotor(double value) {
        conveyorMotor.set(value);
    }

    // Get the current instance of the intake
    public static IntakeConveyor getInstance() {
        // If the intake is equal to null, then an instance has not been created yet
        // If this is the case, then create an instance
        if (intakeConveyor == null) {
            intakeConveyor = new IntakeConveyor();
        }

        return intakeConveyor;
    }

    // Get a reference to the intake motor
    public CANSparkMax getIntakeMotor() {
        return conveyorMotor;
    }
}
