
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.util.Constants.DrivetrainMotors.RIGHT_CLIMBER;
import static frc.robot.util.Constants.DrivetrainMotors.LEFT_CLIMBER;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.controller.PIDController;

import static frc.robot.util.Constants.DrivetrainMotors.RIGHT_CLIMBER_PID_P;
import static frc.robot.util.Constants.DrivetrainMotors.LEFT_CLIMBER_PID_P;
import static frc.robot.util.Constants.DrivetrainMotors.RIGHT_CLIMBER_PID_I;
import static frc.robot.util.Constants.DrivetrainMotors.LEFT_CLIMBER_PID_I;
import static frc.robot.util.Constants.DrivetrainMotors.RIGHT_CLIMBER_PID_D;
import static frc.robot.util.Constants.DrivetrainMotors.LEFT_CLIMBER_PID_D;

public class Climber extends SubsystemBase {
    private PIDController leftClimberPid;
    private PIDController rightClimberPid;

    public static Climber climber;
    private TalonFX LeftClimber;
    private TalonFX RightClimber;

    private Climber(){

        rightClimberPid = new PIDController(RIGHT_CLIMBER_PID_P, RIGHT_CLIMBER_PID_I, RIGHT_CLIMBER_PID_D); 
        leftClimberPid = new PIDController(LEFT_CLIMBER_PID_P, LEFT_CLIMBER_PID_I, LEFT_CLIMBER_PID_D); 



        RightClimber = new TalonFX(RIGHT_CLIMBER);
        LeftClimber = new TalonFX(LEFT_CLIMBER);
    }
     
    public void setMotor(double setLeftValue, double setRightValue){
            LeftClimber.set(setLeftValue);
            RightClimber.set(setRightValue); 
    }
    public PIDController getRightClimberPid(){

        return rightClimberPid;

    }
    public PIDController getLeftClimberPid(){

        return leftClimberPid;
    }
     public static Climber getInstance(){
             if (climber == null){
             climber = new Climber();
                }
     return climber;
            }
public static Climber getClimber(){
    return climber;
    }
    public TalonFX getRightClimberMotor(){
        return RightClimber;
    }
    public TalonFX getLeftClimberMotor(){
        return LeftClimber;
    }
    
    
}

  

