
// Copyright (c) FIRST and other WPILib contributors.
//  Open Source Software; you can modify and/or share it under the terms of
//  the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import static frc.robot.util.Constants.Intake.ROLLER_MOTOR_ID;

public class IntakeRoller extends SubsystemBase {
    private static IntakeRoller intakeRoller;

    private CANSparkMax rollMotor;

    // Intake roller constructor
    private IntakeRoller() {
        // Initialize the intake roller motor
        rollMotor = new CANSparkMax(ROLLER_MOTOR_ID, MotorType.kBrushless);
    }

    // Set the intake roller motor to a specific value
    public void setMotor(double setValue) {
        rollMotor.set(setValue);
    }

    // Get an instance to the current intake roller
    public static IntakeRoller getInstance() {
        // If the intake roller is equal to null, then an instance has not been created yet
        // If this is the case, then create an instance
        if (intakeRoller == null) {
            intakeRoller = new IntakeRoller();
        }

        return intakeRoller;
    }

    // Get a reference to the intake roller motor
    public IntakeRoller getRollerMotor() {
        return intakeRoller;
    }
}
