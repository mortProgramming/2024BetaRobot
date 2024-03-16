package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.util.Constants.DrivetrainMotors.LEFT_CLIMBER;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase;

public class LeftClimber extends SubsystemBase {
    private static LeftClimber leftClimber;
    private static CANSparkMax leftClimberMotor;

    public LeftClimber() {
        leftClimberMotor = new CANSparkMax(LEFT_CLIMBER, MotorType.kBrushless);
    }

    public static void setMotor(double setValue) {
        leftClimberMotor.set(setValue);
    }

    public static LeftClimber getInstance() {
        if (leftClimber == null) {
            leftClimber = new LeftClimber();
        }
        
        return leftClimber;
    }
}
