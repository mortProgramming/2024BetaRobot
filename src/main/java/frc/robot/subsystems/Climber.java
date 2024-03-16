package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.util.Constants.DrivetrainMotors.RIGHT_CLIMBER;
import static frc.robot.util.Constants.DrivetrainMotors.LEFT_CLIMBER;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase;

public class Climber extends SubsystemBase {
    private static CANSparkMax rightClimber;
    private static CANSparkMax leftClimber;
    private static Climber climber;

    public Climber() {
        rightClimber = new CANSparkMax(RIGHT_CLIMBER, MotorType.kBrushless);
        leftClimber = new CANSparkMax(LEFT_CLIMBER, MotorType.kBrushless);

        // We want the right climber to do the same thing as the left climber
        rightClimber.follow(leftClimber, true);
    }

    public static void setMotor(double setValue) {
        leftClimber.set(setValue);
    }

    public static Climber getInstance() {
        if (climber == null) {
            climber = new Climber();
        }
        
        return climber;
    }
}
