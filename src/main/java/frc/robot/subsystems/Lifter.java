package frc.robot.subsystems;

import static frc.robot.config.constants.PIDConstants.Lifter.TO_POS_CONSTRAINTS;
import static frc.robot.config.constants.PIDConstants.Lifter.TO_POS_KD;
import static frc.robot.config.constants.PIDConstants.Lifter.TO_POS_KG;
import static frc.robot.config.constants.PIDConstants.Lifter.TO_POS_KI;
import static frc.robot.config.constants.PIDConstants.Lifter.TO_POS_KP;
import static frc.robot.config.constants.PhysicalConstants.Lifter.LIFTER_ARM_OFFSET_DEG;
import static frc.robot.config.constants.PhysicalConstants.Lifter.LIFTER_GEAR_RATIO;
import static frc.robot.config.constants.PhysicalConstants.Lifter.LIFTER_UP;
import static frc.robot.config.constants.PortConstants.Lifter.LIFTER_MOTOR;
import static frc.robot.mortlib.hardware.motor.MotorTypeEnum.NEO;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.mortlib.subsystems.arm.PIDArm;

public class Lifter extends SubsystemBase {
    private static Lifter lifter;

    private PIDArm liftArm;
    private double lifterPosition;
    private ShuffleboardTab tab;
    private CANSparkLowLevel.MotorType brushType;
    private CANSparkMax Liftmotor;
    private SparkPIDController control;
    private RelativeEncoder encoder;

    public int ID;
    private static final double MAX_VELOCITY = 5000;
    private static final double MAX_ACCELERATION = 3000;
    private static final double kSmartMotionCruiseVelocity = MAX_VELOCITY;
    private static final double kSmartMotionMaxAccel = MAX_ACCELERATION;

    private Lifter() {
        liftArm = new PIDArm(NEO, LIFTER_MOTOR);
        liftArm.motor.setDirectionFlip(true);

        Liftmotor = new CANSparkMax(ID, brushType);
        control = Liftmotor.getPIDController();
        encoder = ((CANSparkMax) encoder).getEncoder();

        control.setP(TO_POS_KP);
        control.setI(TO_POS_KI);
        control.setD(TO_POS_KD);

        control.setSmartMotionMaxVelocity(kSmartMotionCruiseVelocity, 0);
        control.setSmartMotionMaxAccel(kSmartMotionMaxAccel, 0);

        liftArm.setPIDConstants(TO_POS_KP, TO_POS_KI, TO_POS_KD, TO_POS_CONSTRAINTS);
        liftArm.setFeedforward(0, TO_POS_KG, 0, 0);
        liftArm.offset = Rotation2d.fromDegrees(LIFTER_ARM_OFFSET_DEG);

        lifterPosition = LIFTER_UP;

        tab = Shuffleboard.getTab("Lifter");
        ShuffleboardLayout layout = tab.getLayout("Overall", BuiltInLayouts.kList);
        layout.withSize(2, 4).withPosition(0, 5);
        layout.addNumber("LifterPosition", () -> lifterPosition);
        layout.addNumber("ActualSpeeds", () -> liftArm.motor.getOutputVoltage());
        layout.addNumber("LifterPosRot", () -> getPositionRot());
        layout.addNumber("LifterPosDeg", () -> getPositionDeg());
    }

    @Override
    public void periodic() {
        control.setReference(0.0, CANSparkMax.ControlType.kSmartMotion);
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

    public static Lifter getInstance() {
        if (lifter == null) {
            lifter = new Lifter();
        }
        return lifter;
    }
}
