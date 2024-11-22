package frc.robot.subsystems;

import static frc.robot.mortlib.hardware.motor.MotorTypeEnum.*;

import static frc.robot.config.constants.PhysicalConstants.VOLTAGE;
import static frc.robot.config.constants.PortConstants.Intake.*;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.mortlib.hardware.motor.Motor;

public class Intaker extends SubsystemBase {
    private static Intaker intaker;

    private Motor motor;

    private double intakerSpeed;

    private ShuffleboardTab tab;

    private Intaker() {
        motor = new Motor(NEO, INTAKE_MOTOR);
        motor.setDirectionFlip(true);

        intakerSpeed = 0;

        tab = Shuffleboard.getTab("Intake");
        ShuffleboardLayout layout = tab.getLayout("Overall", BuiltInLayouts.kList);
        layout.withSize(2, 4).withPosition(0, 5);
        layout.addNumber("IntakerSpeed", () -> intakerSpeed);
    }

    @Override
    public void periodic() {
        motor.setVoltage(intakerSpeed * VOLTAGE);
    }

    public void setSpeed(double intakerSpeed) {
        this.intakerSpeed = intakerSpeed;
    }

    public static Intaker getInstance() {
        if (intaker == null) {
            intaker = new Intaker();
        }
        return intaker;
    }
}
