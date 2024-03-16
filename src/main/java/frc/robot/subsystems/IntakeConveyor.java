// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.


package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import static frc.robot.util.Constants.DrivetrainMotors.CONVEYOR_MOTOR;

public class IntakeConveyor extends SubsystemBase {
    //make second I lowercase
    private static CANSparkMax conveyorMotor;
    private static IntakeConveyor intakeConveyor;

    private IntakeConveyor() {
        conveyorMotor = new CANSparkMax(CONVEYOR_MOTOR, MotorType.kBrushless);
    }
//comment out static if there is an issue
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
