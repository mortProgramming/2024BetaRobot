// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;
import frc.robot.util.Operator;

public class ShooterControl extends Command {

private static Shooter shooter;

private double motorValue;

public ShooterControl(){
 shooter = Shooter.getInstance();
 addRequirements(shooter);
 motorValue = 0;
}


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  if (Operator.aButton() == true){
      motorValue = -100;
    }

    if (Operator.xButton() == true) {
      motorValue = 0;
    }

      Shooter.setMotor(motorValue);
  }

  
  // private void setMotor(int i) {
  //   // TODO Auto-generated method stub
  //   throw new UnsupportedOperationException("Unimplemented method 'setMotor'");
  // }
  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
