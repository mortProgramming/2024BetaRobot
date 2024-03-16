package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.util.Constants.DrivetrainMotors.RIGHT_CLIMBER;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase;

public class RightClimber extends SubsystemBase {
    private static RightClimber rightClimber;
    private static CANSparkMax rightClimberMotor;
    private RightClimber() {
        rightClimberMotor = new CANSparkMax(RIGHT_CLIMBER, MotorType.kBrushless);
    }

    public static void setMotor(double setValue) {
        rightClimberMotor.set(setValue);
    }

    public static RightClimber getInstance() {
        if (rightClimber == null) {
            rightClimber = new RightClimber();
        }
        
        return rightClimber;
    }
}
