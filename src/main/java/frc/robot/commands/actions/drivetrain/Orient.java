// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.actions.drivetrain;

import frc.robot.subsystems.Drivetrain;

import static frc.robot.config.constants.PhysicalConstants.Drivetrain.*;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class Orient extends SequentialCommandGroup {
  private Drivetrain drivetrain;

  public Orient() {
    drivetrain = Drivetrain.getInstance();
    addCommands(
      drivetrain.setGyroscopeZero(IMU_TO_ROBOT_FRONT_ANGLE)
    );
  }

  public Orient(double angle) {
    drivetrain = Drivetrain.getInstance();
    addCommands(
      drivetrain.setGyroscopeZero(angle)
    );
  }
}
