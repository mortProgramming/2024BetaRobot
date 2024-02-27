// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import static frc.robot.util.Constants.DrivetrainMotors.CONVEYOR_MOTOR;

public class IntakeConveyor extends SubsystemBase{
	private static IntakeConveyor IntakeConveyor;
	private static CANSparkMax conveyorMotor;
	private static CANSparkMax Conveyor;
	
	
	
	private IntakeConveyor() {
		conveyorMotor = new CANSparkMax(CONVEYOR_MOTOR, MotorType.kBrushless);
	}

public static void setMotor(double setValue) {
	conveyorMotor.set(setValue);
}

public static IntakeConveyor getInstance() {
		if (IntakeConveyor == null) {
			IntakeConveyor = new IntakeConveyor();
			
		}
		return IntakeConveyor;
	}

public static IntakeConveyor getIntakeConveyor() {
	return IntakeConveyor;
}
}
