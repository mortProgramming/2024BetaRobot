
// Copyright (c) FIRST and other WPILib contributors.
//  Open Source Software; you can modify and/or share it under the terms of
//  the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import static frc.robot.util.Constants.DrivetrainMotors.INTAKE_ROLLER_MOTOR;
import static frc.robot.util.Constants.DrivetrainMotors.CONVEYOR_MOTOR;

public class IntakeRoller extends SubsystemBase{
	private static CANSparkMax IntakeRoller;
	private static CANSparkMax rollMotor;
	private static IntakeRoller intakeRoller;
	
	
	private IntakeRoller() {
		rollMotor = new CANSparkMax(INTAKE_ROLLER_MOTOR, MotorType.kBrushless);
		//Conveyor = new CANSparkMax(CONVEYOR_MOTOR, MotorType.kBrushless);
	}

public static void setMotor(double setValue) {
	rollMotor.set(setValue);
}

public static IntakeRoller getInstance() {
		if (intakeRoller == null) {
			intakeRoller = new IntakeRoller();
			
		}
		return intakeRoller;
	}

public static IntakeRoller getIntakeRoller() {
	return intakeRoller;
}
}

