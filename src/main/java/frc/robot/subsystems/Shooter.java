// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.util.Constants.DrivetrainMotors.RIGHT_SHOOTER;
import static frc.robot.util.Constants.DrivetrainMotors.LEFT_SHOOTER;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase;

/** Add your docs here. */
public class Shooter extends SubsystemBase {

    private static Shooter shooter;
    private static CANSparkMax LeftShooter;
    private static CANSparkMax RightShooter;

    private Shooter() {
        RightShooter = new CANSparkMax(RIGHT_SHOOTER, MotorType.kBrushless);
        LeftShooter = new CANSparkMax(LEFT_SHOOTER, MotorType.kBrushless);
        RightShooter.follow(LeftShooter, true);
    }

    public void setMotor(double setValue) {
        LeftShooter.set(setValue);    }

    public static Shooter getInstance() {
        if (shooter == null) {
            shooter = new Shooter();

        }
        return shooter;
    }

    public static Shooter getShooter() {
        return shooter;

    }

}