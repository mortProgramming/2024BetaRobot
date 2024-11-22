package frc.robot.subsystems;

import static frc.robot.mortlib.hardware.motor.MotorTypeEnum.*;

import static frc.robot.config.constants.PhysicalConstants.VOLTAGE;
import static frc.robot.config.constants.PortConstants.Conveyor.*;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.mortlib.hardware.motor.Motor;

public class Conveyor extends SubsystemBase {
    private static Conveyor conveyor;

    private Motor motor;

    private double conveyorSpeed;

    private ShuffleboardTab tab;

    private Conveyor() {
        motor = new Motor(VORTEX, CONVEYOR_MOTOR);
        motor.setDirectionFlip(true);

        conveyorSpeed = 0;

        tab = Shuffleboard.getTab("Conveyor");
        ShuffleboardLayout layout = tab.getLayout("Overall", BuiltInLayouts.kList);
        layout.withSize(2, 4).withPosition(0, 5);
        layout.addNumber("conveyorSpeed", () -> conveyorSpeed);
    }

    @Override
    public void periodic() {
        motor.setVoltage(conveyorSpeed * VOLTAGE);
    }

    public void setSpeed(double conveyorSpeed) {
        this.conveyorSpeed = conveyorSpeed;
    }

    public static Conveyor getInstance() {
        if (conveyor == null) {
            conveyor = new Conveyor();
        }
        return conveyor;
    }
}
