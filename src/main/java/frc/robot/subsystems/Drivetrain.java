// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.config.constants.PhysicalConstants.Drivetrain.*;
import static frc.robot.config.constants.PIDConstants.Drivetrain.*;
import static frc.robot.config.constants.PortConstants.Drivetrain.*;

import static frc.robot.mortlib.hardware.encoder.EncoderTypeEnum.*;
import static frc.robot.mortlib.hardware.imu.IMUTypeEnum.*;
import static frc.robot.mortlib.hardware.motor.MotorTypeEnum.*;
import static frc.robot.mortlib.subsystems.swerve.ModuleConfigEnum.*;

import frc.robot.config.IO;
import frc.robot.mortlib.hardware.imu.IMU;
import frc.robot.mortlib.subsystems.swerve.SwerveModule;
import frc.robot.mortlib.subsystems.swerve.swervedrives.OdometeredSwerveDrive;
import frc.robot.mortlib.logger.LoggerGroup;
import static frc.robot.mortlib.logger.LoggerTypeEnum.*;

public class Drivetrain extends SubsystemBase {
  private static Drivetrain drivetrain;

  private OdometeredSwerveDrive swerveDrive;

  private SwerveModule frontLeftModule;
  private SwerveModule frontRightModule;
  private SwerveModule backLeftModule;
  private SwerveModule backRightModule;

  private SwerveDriveKinematics kinematics;

  private ChassisSpeeds speeds;

  private IMU imu;

  private ProfiledPIDController xToPosController;
	private ProfiledPIDController yToPosController;
  private ProfiledPIDController rotateToAngleController;

  private SwerveDriveOdometry odometer;

  // private LoggerGroup loggers;

  private Drivetrain() {
    configureSwerve();
    
    speeds = new ChassisSpeeds(0, 0, 0);

    xToPosController = new ProfiledPIDController(
			TO_POS_KP, TO_POS_KI, TO_POS_KD, TO_POS_CONSTRAINTS
		);
		yToPosController = new ProfiledPIDController(
			TO_POS_KP, TO_POS_KI, TO_POS_KD, TO_POS_CONSTRAINTS
		);
    rotateToAngleController = new ProfiledPIDController(
			TO_ANGLE_KP, TO_ANGLE_KI, TO_ANGLE_KD, TO_ANGLE_CONSTRAINTS
		);

    xToPosController.setTolerance(TO_POS_POS_TOLERANCE);
		yToPosController.setTolerance(TO_POS_POS_TOLERANCE);
    rotateToAngleController.setTolerance(TO_ANGLE_POS_TOLERANCE, TO_ANGLE_VEL_TOLERANCE);

    rotateToAngleController.enableContinuousInput(-180, 180);

    // loggers = new LoggerGroup(SMARTDASHBOARD, SHUFFLEBOARD);

    odometer = new SwerveDriveOdometry(kinematics, Rotation2d.fromDegrees(0), swerveDrive.getModulePositions());
  }

  public void configureSwerve () {
    frontLeftModule = new SwerveModule(
      KRAKEN, FRONT_LEFT_DRIVE_MOTOR, 
      KRAKEN, FRONT_LEFT_STEER_MOTOR, 
      CANCODER, FRONT_LEFT_ENCODER, 
      MK4i_L3
    );

    frontRightModule = new SwerveModule(
      KRAKEN, FRONT_RIGHT_DRIVE_MOTOR, 
      KRAKEN, FRONT_RIGHT_STEER_MOTOR, 
      CANCODER, FRONT_RIGHT_ENCODER, 
      MK4i_L3
    );

    backLeftModule = new SwerveModule(
      KRAKEN, BACK_LEFT_DRIVE_MOTOR, 
      KRAKEN, BACK_LEFT_STEER_MOTOR, 
      CANCODER, BACK_LEFT_ENCODER, 
      MK4i_L3
    );

    backRightModule = new SwerveModule(
      KRAKEN, BACK_RIGHT_DRIVE_MOTOR, 
      KRAKEN, BACK_RIGHT_STEER_MOTOR, 
      CANCODER, BACK_RIGHT_ENCODER, 
      MK4i_L3
    );

    frontLeftModule.steerMotor.setDirectionFlip(true);
    frontRightModule.steerMotor.setDirectionFlip(true);
    backLeftModule.steerMotor.setDirectionFlip(true);
    backRightModule.steerMotor.setDirectionFlip(true);

    kinematics = new SwerveDriveKinematics(
      // Front left
			new Translation2d(DRIVETRAIN_TRACKWIDTH_METERS / 2.0, DRIVETRAIN_WHEELBASE_METERS / 2.0),
			// Front right
			new Translation2d(DRIVETRAIN_TRACKWIDTH_METERS / 2.0, -DRIVETRAIN_WHEELBASE_METERS / 2.0),
			// Back left
			new Translation2d(-DRIVETRAIN_TRACKWIDTH_METERS / 2.0, DRIVETRAIN_WHEELBASE_METERS / 2.0),
			// Back right
			new Translation2d(-DRIVETRAIN_TRACKWIDTH_METERS / 2.0, -DRIVETRAIN_WHEELBASE_METERS / 2.0)
    );

    imu = new IMU(NAVX, IMU_ID);

    swerveDrive = new OdometeredSwerveDrive(
      frontLeftModule, frontRightModule, 
      backLeftModule, backRightModule, 
      kinematics, imu
    );

    swerveDrive.setOffsets(FRONT_LEFT_OFFSET, FRONT_RIGHT_OFFSET, BACK_LEFT_OFFSET, BACK_RIGHT_OFFSET);
  }

  @Override
  public void periodic() {
    if(IO.getIsBlue()) {
			speeds = new ChassisSpeeds(
				-speeds.vyMetersPerSecond, -speeds.vxMetersPerSecond,
				speeds.omegaRadiansPerSecond
			);
		}
		else {
			speeds = new ChassisSpeeds(
				speeds.vyMetersPerSecond, speeds.vxMetersPerSecond,
				speeds.omegaRadiansPerSecond
			);
		}

		swerveDrive.setOrientedVelocity(speeds);

    swerveDrive.update();

    // loggers.putDouble("XPose", () -> swerveDrive.getPosition().getX());
    // loggers.putDouble("YPose", () -> swerveDrive.getPosition().getY());

    // loggers.putDouble("Yaw", () -> Math.toDegrees(swerveDrive.getRobotRotations().getZ()));
    // loggers.putDouble("Pitch", () -> Math.toDegrees(swerveDrive.getRobotRotations().getY()));
    // loggers.putDouble("Roll", () -> Math.toDegrees(swerveDrive.getRobotRotations().getX()));

    odometer.update(getIMURotation(), swerveDrive.getModulePositions());
  }

  public void setDrive(ChassisSpeeds speeds) {
    this.speeds = speeds;
  }

  public void setUnorientedDrive(ChassisSpeeds speeds) {
    this.speeds = ChassisSpeeds.fromFieldRelativeSpeeds(
      speeds, Rotation2d.fromDegrees(getIMURotation().getDegrees())
    );
  }

  public void setPosController(double poseX, double poseY, double wantedX, double wantedY) {
		speeds = new ChassisSpeeds(
			xToPosController.calculate(swerveDrive.getPosition().getX(), wantedX), 
        	yToPosController.calculate(swerveDrive.getPosition().getY(), wantedY), 
        	0
		);
	}

  public void setAngleController(double wantedAngle) {
		speeds = ChassisSpeeds.fromFieldRelativeSpeeds(
			speeds.vxMetersPerSecond, 
        	speeds.vyMetersPerSecond,
			rotateToAngleController.calculate(drivetrain.getIMURotation().getDegrees(), wantedAngle),
			drivetrain.getIMURotation()
		);
	}

  public Command setGyroscopeZero(double angle) {
		return new InstantCommand(() -> swerveDrive.zeroIMU(angle), drivetrain);
	}

  public void setRobotPosition(Pose2d pose) {
		odometer.resetPosition(Rotation2d.fromDegrees(0), swerveDrive.getModulePositions(), pose);
	}



  public boolean getXControllerAtSetpoint() {
		return xToPosController.atSetpoint();
	}

	public boolean getYControllerAtSetpoint() {
		return yToPosController.atSetpoint();
	}

	public boolean getRotateControllerAtSetpoint() {
		return rotateToAngleController.atSetpoint();
	}
  
	public ChassisSpeeds getChassisSpeeds() {
    return speeds;
  }

	public double getMaxSpeedMeters() {
		return frontLeftModule.maxSpeed;
	}

  public ProfiledPIDController getXController() {
		return xToPosController;
	}

	public ProfiledPIDController getYController() {
		return yToPosController;
	}

	public ProfiledPIDController getRotateController() {
		return rotateToAngleController;
	}
	
	public OdometeredSwerveDrive getSwerveDrive() {
		return swerveDrive;
	}

	public SwerveDriveKinematics getDriveKinematics() {
		return kinematics;
	}

	public Rotation2d getIMURotation() {
		return swerveDrive.getFieldRelativeAngle2d();
	}

  public Pose2d getPose() {
		return odometer.getPoseMeters();
	}

  public static Drivetrain getInstance() {
		if (drivetrain == null) {
			drivetrain = new Drivetrain();
		}
		return drivetrain;
	}
}
