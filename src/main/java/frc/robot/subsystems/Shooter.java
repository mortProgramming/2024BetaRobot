package frc.robot.subsystems;

import static frc.robot.mortlib.hardware.motor.MotorTypeEnum.*;

import static frc.robot.config.constants.PhysicalConstants.VOLTAGE;
import static frc.robot.config.constants.PortConstants.Shooter.*;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.mortlib.hardware.motor.Motor;
import frc.robot.mortlib.hardware.motor.MotorGroup;

public class Shooter extends SubsystemBase {
    private static Shooter shooter;

    private Motor topMotor;
    private Motor bottomMotor;
    private MotorGroup group;

    private double shooterSpeed;

    private ShuffleboardTab tab;

    private Shooter() {
        topMotor = new Motor(NEO, TOP_SHOOTER_MOTOR);

        bottomMotor = new Motor(NEO, BOTTOM_SHOOTER_MOTOR);

        group = new MotorGroup(topMotor, bottomMotor);
        group.setDirectionFlip(0, true);
        group.setDirectionFlip(1, false);

        shooterSpeed = 0;

        tab = Shuffleboard.getTab("Shooter");
        ShuffleboardLayout layout = tab.getLayout("Overall", BuiltInLayouts.kList);
        layout.withSize(2, 4).withPosition(0, 5);
        layout.addNumber("shooterSpeed", () -> shooterSpeed);
    }

    @Override
    public void periodic() {
        group.setVoltage(shooterSpeed * VOLTAGE);
        // group.getMotor(0).setVoltage(shooterSpeed * VOLTAGE);
    }

    public void setSpeed(double shooterSpeed) {
        this.shooterSpeed = shooterSpeed;
    }

    public static Shooter getInstance() {
        if (shooter == null) {
            shooter = new Shooter();
        }
        return shooter;
    }
}
