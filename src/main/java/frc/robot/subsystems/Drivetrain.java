package frc.robot.subsystems;

import static frc.robot.util.Constants.DrivetrainMotors.BACK_LEFT_MODULE_DRIVE_MOTOR;
import static frc.robot.util.Constants.DrivetrainMotors.BACK_LEFT_MODULE_STEER_ENCODER;
import static frc.robot.util.Constants.DrivetrainMotors.BACK_LEFT_MODULE_STEER_MOTOR;
import static frc.robot.util.Constants.DrivetrainMotors.BACK_LEFT_MODULE_STEER_OFFSET;
import static frc.robot.util.Constants.DrivetrainMotors.BACK_RIGHT_MODULE_DRIVE_MOTOR;
import static frc.robot.util.Constants.DrivetrainMotors.BACK_RIGHT_MODULE_STEER_ENCODER;
import static frc.robot.util.Constants.DrivetrainMotors.BACK_RIGHT_MODULE_STEER_MOTOR;
import static frc.robot.util.Constants.DrivetrainMotors.BACK_RIGHT_MODULE_STEER_OFFSET;
import static frc.robot.util.Constants.DrivetrainMotors.FRONT_LEFT_MODULE_DRIVE_MOTOR;
import static frc.robot.util.Constants.DrivetrainMotors.FRONT_LEFT_MODULE_STEER_ENCODER;
import static frc.robot.util.Constants.DrivetrainMotors.FRONT_LEFT_MODULE_STEER_MOTOR;
import static frc.robot.util.Constants.DrivetrainMotors.FRONT_LEFT_MODULE_STEER_OFFSET;
import static frc.robot.util.Constants.DrivetrainMotors.FRONT_RIGHT_MODULE_DRIVE_MOTOR;
import static frc.robot.util.Constants.DrivetrainMotors.FRONT_RIGHT_MODULE_STEER_ENCODER;
import static frc.robot.util.Constants.DrivetrainMotors.FRONT_RIGHT_MODULE_STEER_MOTOR;
import static frc.robot.util.Constants.DrivetrainMotors.FRONT_RIGHT_MODULE_STEER_OFFSET;
import static frc.robot.util.Constants.DrivetrainSpecs.DRIVETRAIN_TRACKWIDTH_METERS;
import static frc.robot.util.Constants.DrivetrainSpecs.DRIVETRAIN_WHEELBASE_METERS;
import static frc.robot.util.Constants.DrivetrainSpecs.MAX_VELOCITY_METERS_PER_SECOND;
import static frc.robot.util.Constants.DrivetrainSpecs.MAX_VOLTAGE;

import com.kauailabs.navx.frc.AHRS;
// import com.swervedrivespecialties.swervelib.Mk4iSwerveModuleHelper;
// import com.swervedrivespecialties.swervelib.Mk4iSwerveModuleHelper;
import com.swervedrivespecialties.swervelib.MkSwerveModuleBuilder;
import com.swervedrivespecialties.swervelib.MotorType;
import com.swervedrivespecialties.swervelib.SdsModuleConfigurations;
import com.swervedrivespecialties.swervelib.SwerveModule;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
	private final AHRS navX;

	public final SwerveDriveKinematics driveKinematics;
	private SwerveDriveOdometry driveOdometry;

	private final SwerveModule frontLeftModule;
	private final SwerveModule frontRightModule;
	private final SwerveModule backLeftModule;
	private final SwerveModule backRightModule;

	private ChassisSpeeds chassisSpeeds;

	private PIDController xController;
	private PIDController yController;
	private PIDController thetaController;

	private PIDController rotateToAngleController;

	private PIDController chargeStationController;

	private static Drivetrain drivetrain;

	public Drivetrain() {
		navX = new AHRS(SerialPort.Port.kMXP);
		driveKinematics = new SwerveDriveKinematics(
				// Front left
				new Translation2d(DRIVETRAIN_TRACKWIDTH_METERS / 2.0, DRIVETRAIN_WHEELBASE_METERS / 2.0),
				// Front right
				new Translation2d(DRIVETRAIN_TRACKWIDTH_METERS / 2.0, -DRIVETRAIN_WHEELBASE_METERS / 2.0),
				// Back left
				new Translation2d(-DRIVETRAIN_TRACKWIDTH_METERS / 2.0, DRIVETRAIN_WHEELBASE_METERS / 2.0),
				// Back right
				new Translation2d(-DRIVETRAIN_TRACKWIDTH_METERS / 2.0, -DRIVETRAIN_WHEELBASE_METERS / 2.0));

		chassisSpeeds = new ChassisSpeeds(0.0, 0.0, 0.0);

		ShuffleboardTab tab = Shuffleboard.getTab("Drivetrain");

		frontLeftModule = new MkSwerveModuleBuilder()
				.withLayout(tab.getLayout("Front Left Module", BuiltInLayouts.kList).withSize(2, 4).withPosition(0, 0))
				.withGearRatio(SdsModuleConfigurations.MK4I_L2)
				.withDriveMotor(MotorType.FALCON, FRONT_LEFT_MODULE_DRIVE_MOTOR)
				.withSteerMotor(MotorType.FALCON, FRONT_LEFT_MODULE_STEER_MOTOR)
				.withSteerEncoderPort(FRONT_LEFT_MODULE_STEER_ENCODER).withSteerOffset(FRONT_LEFT_MODULE_STEER_OFFSET)
				.build();
		frontRightModule = new MkSwerveModuleBuilder()
				.withLayout(tab.getLayout("Front Right Module", BuiltInLayouts.kList).withSize(2, 4).withPosition(2, 0))
				.withGearRatio(SdsModuleConfigurations.MK4I_L2)
				.withDriveMotor(MotorType.FALCON, FRONT_RIGHT_MODULE_DRIVE_MOTOR)
				.withSteerMotor(MotorType.FALCON, FRONT_RIGHT_MODULE_STEER_MOTOR)
				.withSteerEncoderPort(FRONT_RIGHT_MODULE_STEER_ENCODER).withSteerOffset(FRONT_RIGHT_MODULE_STEER_OFFSET)
				.build();
		backLeftModule = new MkSwerveModuleBuilder()
				.withLayout(tab.getLayout("Back Left Module", BuiltInLayouts.kList).withSize(2, 4).withPosition(4, 0))
				.withGearRatio(SdsModuleConfigurations.MK4I_L2)
				.withDriveMotor(MotorType.FALCON, BACK_LEFT_MODULE_DRIVE_MOTOR)
				.withSteerMotor(MotorType.FALCON, BACK_LEFT_MODULE_STEER_MOTOR)
				.withSteerEncoderPort(BACK_LEFT_MODULE_STEER_ENCODER).withSteerOffset(BACK_LEFT_MODULE_STEER_OFFSET)
				.build();
		backRightModule = new MkSwerveModuleBuilder()
				.withLayout(tab.getLayout("Back Right Module", BuiltInLayouts.kList).withSize(2, 4).withPosition(6, 0))
				.withGearRatio(SdsModuleConfigurations.MK4I_L2)
				.withDriveMotor(MotorType.FALCON, BACK_RIGHT_MODULE_DRIVE_MOTOR)
				.withSteerMotor(MotorType.FALCON, BACK_RIGHT_MODULE_STEER_MOTOR)
				.withSteerEncoderPort(BACK_RIGHT_MODULE_STEER_ENCODER).withSteerOffset(BACK_RIGHT_MODULE_STEER_OFFSET)
				.build();

		// frontLeftModule = Mk4iSwerveModuleHelper.createFalcon500(
		// tab.getLayout("Front Left Module", BuiltInLayouts.kList).withSize(2,
		// 4).withPosition(0, 0),
		// Mk4iSwerveModuleHelper.GearRatio.L2, FRONT_LEFT_MODULE_DRIVE_MOTOR,
		// FRONT_LEFT_MODULE_STEER_MOTOR,
		// FRONT_LEFT_MODULE_STEER_ENCODER, FRONT_LEFT_MODULE_STEER_OFFSET);

		// frontRightModule = Mk4iSwerveModuleHelper.createFalcon500(
		// tab.getLayout("Front Right Module", BuiltInLayouts.kList).withSize(2,
		// 4).withPosition(2, 0),
		// Mk4iSwerveModuleHelper.GearRatio.L2, FRONT_RIGHT_MODULE_DRIVE_MOTOR,
		// FRONT_RIGHT_MODULE_STEER_MOTOR,
		// FRONT_RIGHT_MODULE_STEER_ENCODER, FRONT_RIGHT_MODULE_STEER_OFFSET);

		// backLeftModule = Mk4iSwerveModuleHelper.createFalcon500(
		// tab.getLayout("Back Left Module", BuiltInLayouts.kList).withSize(2,
		// 4).withPosition(4, 0),
		// Mk4iSwerveModuleHelper.GearRatio.L2, BACK_LEFT_MODULE_DRIVE_MOTOR,
		// BACK_LEFT_MODULE_STEER_MOTOR,
		// BACK_LEFT_MODULE_STEER_ENCODER, BACK_LEFT_MODULE_STEER_OFFSET);

		// backRightModule = Mk4iSwerveModuleHelper.createFalcon500(
		// tab.getLayout("Back Right Module", BuiltInLayouts.kList).withSize(2,
		// 4).withPosition(6, 0),
		// Mk4iSwerveModuleHelper.GearRatio.L2, BACK_RIGHT_MODULE_DRIVE_MOTOR,
		// BACK_RIGHT_MODULE_STEER_MOTOR,
		// BACK_RIGHT_MODULE_STEER_ENCODER, BACK_RIGHT_MODULE_STEER_OFFSET);

		// todo: wait, try the other
		// driveOdometry = new SwerveDriveOdometry(driveKinematics,
		// Rotation2d.fromDegrees(navX.getFusedHeading()),
		// getModulePositions());

		driveOdometry = new SwerveDriveOdometry(driveKinematics, getGyroscopeRotation(), getModulePositions());

		xController = new PIDController(0.8, 0, 0);
		xController.setSetpoint(-3);
		xController.setTolerance(0.1);

		yController = new PIDController(.41, 0, 0);
		yController.setSetpoint(0);
		yController.setTolerance(0.1);

		thetaController = new PIDController(0.04, 0, 0);
		thetaController.setSetpoint(0);
		thetaController.setTolerance(0.5);

		rotateToAngleController = new PIDController(0.07, 0, 0.001);
		rotateToAngleController.setTolerance(0.5);
		rotateToAngleController.enableContinuousInput(-180, 180);

		chargeStationController = new PIDController(0.03, 0, 0);
		chargeStationController.setTolerance(0.5);
		chargeStationController.setSetpoint(0);
	}

	public PIDController getChargeStationController() {
		return chargeStationController;
	}

	/** Sets the gyroscope angle to zero. */
	public void zeroGyroscope() {
		navX.zeroYaw();
	}

	/**
	 * @return Rotation2d
	 */
	public Rotation2d getGyroscopeRotation() {
		if (navX.isMagnetometerCalibrated()) {
			// We will only get valid fused headings if the magnetometer is calibrated
			return Rotation2d.fromDegrees(navX.getFusedHeading());
		}

		// We have to invert the angle of the NavX so that rotating the robot
		// counter-clockwise
		// makes the angle increase.
		return Rotation2d.fromDegrees(360.0 - navX.getYaw());
	}

	public double getAngle() {
		return navX.getAngle();
	}

	/**
	 * @return SwerveDriveKinematics
	 */
	public SwerveDriveKinematics getDriveKinematics() {
		return driveKinematics;
	}

	/**
	 * @return Pose2d
	 */
	public Pose2d getPose() {
		return driveOdometry.getPoseMeters();
	}

	/**
	 * @param pose
	 */
	public void resetPose(Pose2d pose) {
		// driveOdometry.resetPosition(Rotation2d.fromDegrees(navX.getFusedHeading()),
		// getModulePositions(),
		// pose);

		// //todo: try this
		// /*
		driveOdometry.resetPosition(getGyroscopeRotation(), getModulePositions(), pose);

		// */
	}

	public SwerveModulePosition[] getModulePositions() {
		return new SwerveModulePosition[]{frontLeftModule.getPosition(), frontRightModule.getPosition(),
				backLeftModule.getPosition(), backRightModule.getPosition()};
	}

	public AHRS getNavX() {
		return navX;
	}

	/**
	 * @param states
	 */
	public void setModuleStates(SwerveModuleState[] states) {
		SwerveDriveKinematics.desaturateWheelSpeeds(states, MAX_VELOCITY_METERS_PER_SECOND);

		frontLeftModule.set(states[0].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE,
				states[0].angle.getRadians());
		frontRightModule.set(states[1].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE,
				states[1].angle.getRadians());
		backLeftModule.set(states[2].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE,
				states[2].angle.getRadians());
		backRightModule.set(states[3].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE,
				states[3].angle.getRadians());
	}

	public double getCompass() {
		return navX.getCompassHeading();
	}

	/**
	 * @param chassisSpeeds
	 */
	public void drive(ChassisSpeeds chassisSpeeds) {
		this.chassisSpeeds = chassisSpeeds;
	}

	public PIDController getXController() {
		return xController;
	}

	public PIDController getYController() {
		return yController;
	}

	public PIDController getThetaController() {
		return thetaController;
	}

	public PIDController getRotateToAngleController() {
		return rotateToAngleController;
	}

	@Override
	public void periodic() {
		driveOdometry.update(Rotation2d.fromDegrees(navX.getFusedHeading()), getModulePositions());

		SwerveModuleState[] states = driveKinematics.toSwerveModuleStates(chassisSpeeds);
		setModuleStates(states);

		Shuffleboard.getTab("dt").add(drivetrain);
	}

	public static Drivetrain getInstance() {
		if (drivetrain == null) {
			drivetrain = new Drivetrain();
		}
		return drivetrain;
	}
}
