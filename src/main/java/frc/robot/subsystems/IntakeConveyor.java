package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import static frc.robot.util.Constants.DrivetrainMotors.CONVEYOR_MOTOR;

public class IntakeConveyor extends SubsystemBase {
    private static IntakeConveyor intakeConveyor;

    private static CANSparkMax conveyorMotor;

    private IntakeConveyor() {
        conveyorMotor = new CANSparkMax(CONVEYOR_MOTOR, MotorType.kBrushless);
    }

    public void setMotor(double setValue) {
        conveyorMotor.set(setValue);
    }

    public static IntakeConveyor getInstance() {
        if (intakeConveyor == null) {
            intakeConveyor = new IntakeConveyor();

        }
        return intakeConveyor;
    }

    //removed a static below here after 
    public IntakeConveyor getIntakeConveyor() {
        return intakeConveyor;
    }
}
