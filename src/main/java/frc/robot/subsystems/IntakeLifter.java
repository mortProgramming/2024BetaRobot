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
import static frc.robot.util.Constants.DrivetrainMotors.LIFTER_PID_P;
import static frc.robot.util.Constants.DrivetrainMotors.LIFTER_PID_I;
import static frc.robot.util.Constants.DrivetrainMotors.LIFTER_PID_D;


public class IntakeLifter extends SubsystemBase {

    private PIDController lifterPid;
    private static CANSparkMax liftMotor;

    private static IntakeLifter intakeLifter;

    private IntakeLifter() {
        lifterPid = new PIDController(LIFTER_PID_P, LIFTER_PID_I, LIFTER_PID_D); 

        liftMotor = new CANSparkMax(INTAKE_LIFTER_MOTOR, MotorType.kBrushless);
    }

    
    public void setMotor(double setValue) {
        liftMotor.set(setValue);
    }

    public PIDController getLifterPid(){

        return lifterPid;

    }

    public static IntakeLifter getInstance() {
        if (intakeLifter == null) {
            intakeLifter = new IntakeLifter();

        }
        return intakeLifter;
    }

    public CANSparkMax getMotor(){
        return liftMotor;
    }


}
