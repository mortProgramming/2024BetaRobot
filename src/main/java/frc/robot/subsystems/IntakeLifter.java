package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkLowLevel.MotorType;

import static frc.robot.util.Constants.DrivetrainMotors.INTAKE_LIFTER_MOTOR;
import static frc.robot.util.Constants.DrivetrainMotors.INTAKE_ROLLER_MOTOR;
import static frc.robot.util.Constants.Intake.*;

public class IntakeLifter extends SubsystemBase{


	
private PIDController xController;
	private PIDController yController;
	private PIDController thetaController;
	private PIDController rotateToAngleController;
    private static CANSparkMax liftMotor;

	private static IntakeLifter intakeLifter;
	
	private IntakeLifter() {
		
        liftMotor = new CANSparkMax(INTAKE_LIFTER_MOTOR, MotorType.kBrushless); 
	}

	public static void setMotor(double setValue) {
		liftMotor.set(setValue);
	}

	public static IntakeLifter getInstance() {
		if (intakeLifter == null) {
			intakeLifter = new IntakeLifter();
			
		}
		return intakeLifter;
	}

	
}

	





