package frc.robot.subsystems;

import static frc.robot.mortlib.hardware.motor.MotorTypeEnum.*;

import static frc.robot.config.constants.PhysicalConstants.VOLTAGE;
import static frc.robot.config.constants.PhysicalConstants.Lifter.*;
import static frc.robot.config.constants.PIDConstants.Lifter.*;
import static frc.robot.config.constants.PortConstants.Lifter.*;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.mortlib.arm.PIDArm;

public class Lifter extends SubsystemBase {
    private static Lifter lifter;

    private PIDArm liftArm;

    private double lifterSpeed;
    private double lifterLastPosition;

    private ShuffleboardTab tab;

    private Lifter() {
        liftArm = new PIDArm(NEO, LIFTER_MOTOR);
        liftArm.motor.setDirectionFlip(true);

        liftArm.setPIDConstants(TO_POS_KP, TO_POS_KI, TO_POS_KD, TO_POS_CONSTRAINTS);
        liftArm.setG(TO_POS_KG);
        liftArm.offset = Rotation2d.fromDegrees(LIFTER_ARM_OFFSET_DEG);

        lifterSpeed = 0;

        tab = Shuffleboard.getTab("Lifter");
        ShuffleboardLayout layout = tab.getLayout("Overall", BuiltInLayouts.kList);
        layout.withSize(2, 4).withPosition(0, 5);
        layout.addNumber("lifterSpeeds", () -> lifterSpeed);
        layout.addNumber("ActualSpeeds", () -> liftArm.motor.getOutputVoltage());
        layout.addNumber("LifterPosRot", () -> getPositionRot());
        layout.addNumber("LifterPosDeg", () -> getPositionDeg());
        layout.addNumber("LifterWantedPosDeg", () -> lifterLastPosition);
    }

    @Override
    public void periodic() {
        liftArm.setHeldVoltage(lifterSpeed * VOLTAGE, getPositionRot());
    }

    public void setSpeeds(double lifterSpeed) {
        this.lifterSpeed = lifterSpeed;
    }

    public void setPIDPosition(double lifterPosition) {
        lifterLastPosition = lifterPosition;
        lifterSpeed = liftArm.getPIDCalculation(getPositionDeg(), lifterPosition);
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
