package frc.robot.mortlib.subsystems.flywheel;

import frc.robot.mortlib.hardware.motor.MotorGroup;
import frc.robot.mortlib.hardware.motor.MotorTypeEnum;
import frc.robot.mortlib.logger.LoggerGroup;

import static frc.robot.mortlib.logger.LoggerTypeEnum.*;


import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FlywheelBase extends SubsystemBase {
    private MotorGroup motors;
    private PIDFlywheel pidFlywheel;

    private LoggerGroup loggers;

    private double flywheelVel;

    public FlywheelBase(
            double kP, double kI, double kD, Constraints constraints, 
            MotorTypeEnum motorType, int... motorIDs
        ) {
        this(
            kP, kI, kD, constraints, 
            0, 0, 0, 
            motorType, motorIDs
        );
    }

    public FlywheelBase(
            double kP, double kI, double kD, Constraints constraints, 
            double kS, double kV, double kA, 
            MotorTypeEnum motorType, int... motorIDs
        ) {
        
        motors = new MotorGroup(motorType, motorIDs);

        pidFlywheel = new PIDFlywheel(motors);
        pidFlywheel.setPIDConstants(kP, kI, kD, constraints);
        pidFlywheel.setFeedforward(kS, kV, kA);

        loggers = new LoggerGroup(SMARTDASHBOARD, SHUFFLEBOARD);

        loggers.putDouble("Flywheel Set RPM", () -> flywheelVel);
        loggers.putDouble("Flywheel Actual RPM", () -> motors.getVelocityRPM(0));
    }

    @Override
    public void periodic() {
        pidFlywheel.setPIDPosition(flywheelVel);
    }

    public void setFlywheelVelocity(double rpm){
        this.flywheelVel = rpm;
    }



    public double getFlywheelVel() {
        return flywheelVel;
    }

    public MotorGroup getMotors() {
        return motors;
    }

    public PIDFlywheel getPIDFlywheel() {
        return pidFlywheel;
    }

    public LoggerGroup getLogger() {
        return loggers;
    }
}
