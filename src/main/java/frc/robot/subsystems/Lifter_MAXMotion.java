package frc.robot.subsystems;

import static frc.robot.config.constants.PIDConstants.Lifter.TO_POS_KD;
import static frc.robot.config.constants.PIDConstants.Lifter.TO_POS_KI;
import static frc.robot.config.constants.PIDConstants.Lifter.TO_POS_KP;
import static frc.robot.config.constants.PhysicalConstants.Lifter.LIFTER_ARM_OFFSET_DEG;
import static frc.robot.config.constants.PhysicalConstants.Lifter.LIFTER_GEAR_RATIO;
import static frc.robot.config.constants.PhysicalConstants.Lifter.LIFTER_UP;
import static frc.robot.config.constants.PortConstants.Lifter.LIFTER_MOTOR;
import static frc.robot.mortlib.hardware.motor.MotorTypeEnum.NEO;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
// import com.revrobotics.spark.config.MAXMotionConfig;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.mortlib.subsystems.arm.PIDArm;

public class Lifter_MAXMotion extends SubsystemBase {
    private static Lifter_MAXMotion lifter;

    private PIDArm liftArm;
    private double lifterPosition;
    private ShuffleboardTab tab;
    private CANSparkLowLevel.MotorType brushType;
    private CANSparkMax iftmotor;
    private SparkPIDController control;
    private RelativeEncoder encoder;

    // Constants
    private static final double MAX_VELOCITY = 2000;
    private static final double MAX_ACCELERATION = 1500;
    private static final double kSmartMotionCruiseVelocity = MAX_VELOCITY;
    private static final double kSmartMotionMaxAccel = MAX_ACCELERATION;

    // Create MAXMotionConfig object
    // private MAXMotionConfig maxMotionConfig;

    private Lifter_MAXMotion() {
        liftArm = new PIDArm(NEO, LIFTER_MOTOR);
        liftArm.motor.setDirectionFlip(true);

        iftmotor = new CANSparkMax(LIFTER_MOTOR, MotorType.kBrushless);
        control = iftmotor.getPIDController();

        // PID coefficients
        control.setP(TO_POS_KP);
        control.setI(TO_POS_KI);
        control.setD(TO_POS_KD);
        control.setIZone(0);
        control.setFF(0.0);
        control.setOutputRange(-1, 1);

        // Initialize MAXMotionConfig
        // maxMotionConfig = new MAXMotionConfig()
        //     .maxVelocity(kSmartMotionCruiseVelocity)
        //     .maxAcceleration(kSmartMotionMaxAccel)
        //     .allowedClosedLoopError(0.01);  // Example of allowed error (tune this as needed)

        // Apply the MAXMotionConfig to the motor
        applyMAXMotionConfig();

        // Shuffleboard setup
        tab = Shuffleboard.getTab("Lifter");
        ShuffleboardLayout layout = tab.getLayout("Overall", BuiltInLayouts.kList);
        layout.withSize(2, 4).withPosition(0, 5);
        layout.addNumber("LifterPosition", () -> lifterPosition);
        layout.addNumber("ActualSpeeds", () -> liftArm.motor.getOutputVoltage());
        layout.addNumber("LifterPosRot", () -> getPositionRot());
        layout.addNumber("LifterPosDeg", () -> getPositionDeg());
    }

    private void applyMAXMotionConfig() {
        // Apply MAXMotionConfig to the SparkPIDController
        // control.configMotion(maxMotionConfig);
    }

    @Override
    public void periodic() {
        // Set the reference for MAX Motion control
        control.setReference(1000, CANSparkMax.ControlType.kPosition);  // Example reference (position)
    }

    public void setSpeeds(double lifterSpeed) {
        this.lifterPosition = lifterPosition + lifterSpeed * 0.02;
    }

    public void setPIDPosition(double lifterPosition) {
        this.lifterPosition = lifterPosition;
    }
    
    public double getPositionRot() {
        return liftArm.motor.getPositionRotations() / LIFTER_GEAR_RATIO;
    }

    public double getPositionDeg() {
        return getPositionRot() * 360 + LIFTER_ARM_OFFSET_DEG;
    }

    public static Lifter_MAXMotion getInstance() {
        if (lifter == null) {
            lifter = new Lifter_MAXMotion();
        }
        return lifter;
    }
}
