package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
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
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.util.Constants.Drivetrain.BACK_LEFT_MODULE_DRIVE_MOTOR;
import static frc.robot.util.Constants.Drivetrain.BACK_LEFT_MODULE_STEER_ENCODER;
import static frc.robot.util.Constants.Drivetrain.BACK_LEFT_MODULE_STEER_MOTOR;
import static frc.robot.util.Constants.Drivetrain.BACK_LEFT_MODULE_STEER_OFFSET;
import static frc.robot.util.Constants.Drivetrain.BACK_RIGHT_MODULE_DRIVE_MOTOR;
import static frc.robot.util.Constants.Drivetrain.BACK_RIGHT_MODULE_STEER_ENCODER;
import static frc.robot.util.Constants.Drivetrain.BACK_RIGHT_MODULE_STEER_MOTOR;
import static frc.robot.util.Constants.Drivetrain.BACK_RIGHT_MODULE_STEER_OFFSET;
import static frc.robot.util.Constants.Drivetrain.FRONT_LEFT_MODULE_DRIVE_MOTOR;
import static frc.robot.util.Constants.Drivetrain.FRONT_LEFT_MODULE_STEER_ENCODER;
import static frc.robot.util.Constants.Drivetrain.FRONT_LEFT_MODULE_STEER_MOTOR;
import static frc.robot.util.Constants.Drivetrain.FRONT_LEFT_MODULE_STEER_OFFSET;
import static frc.robot.util.Constants.Drivetrain.FRONT_RIGHT_MODULE_DRIVE_MOTOR;
import static frc.robot.util.Constants.Drivetrain.FRONT_RIGHT_MODULE_STEER_ENCODER;
import static frc.robot.util.Constants.Drivetrain.FRONT_RIGHT_MODULE_STEER_MOTOR;
import static frc.robot.util.Constants.Drivetrain.FRONT_RIGHT_MODULE_STEER_OFFSET;
import static frc.robot.util.Constants.Drivetrain.DRIVETRAIN_TRACKWIDTH_METERS;
import static frc.robot.util.Constants.Drivetrain.DRIVETRAIN_WHEELBASE_METERS;
import static frc.robot.util.Constants.Drivetrain.MAX_VELOCITY_METERS_PER_SECOND;
import static frc.robot.util.Constants.Drivetrain.MAX_VOLTAGE;

public class Drivetrain extends SubsystemBase {
    private static Drivetrain drivetrain;

    private final AHRS navX;
    public final SwerveDriveKinematics driveKinematics;
    private SwerveDriveOdometry driveOdometry;
    private ChassisSpeeds chassisSpeeds;

    private final SwerveModule frontLeftModule;
    private final SwerveModule frontRightModule;
    private final SwerveModule backLeftModule;
    private final SwerveModule backRightModule;

    private PIDController xController;
    private PIDController yController;
    private PIDController thetaController;
    private PIDController rotateToAngleController;
    private PIDController chargeStationController;

    private ShuffleboardTab drivetrainTab;

    public Drivetrain() {
        navX = new AHRS(SerialPort.Port.kMXP);

        driveKinematics = new SwerveDriveKinematics(
                new Translation2d(DRIVETRAIN_TRACKWIDTH_METERS / 2.0, DRIVETRAIN_WHEELBASE_METERS / 2.0), // Front left
                new Translation2d(DRIVETRAIN_TRACKWIDTH_METERS / 2.0, -DRIVETRAIN_WHEELBASE_METERS / 2.0), // Front right
                new Translation2d(-DRIVETRAIN_TRACKWIDTH_METERS / 2.0, DRIVETRAIN_WHEELBASE_METERS / 2.0), // Back left
                new Translation2d(-DRIVETRAIN_TRACKWIDTH_METERS / 2.0, -DRIVETRAIN_WHEELBASE_METERS / 2.0)); // Back right

        chassisSpeeds = new ChassisSpeeds(0.0, 0.0, 0.0);

        drivetrainTab = Shuffleboard.getTab("Drivetrain");

        frontLeftModule = new MkSwerveModuleBuilder()
                .withLayout(drivetrainTab.getLayout("Front Left Module", BuiltInLayouts.kList).withSize(2, 4)
                        .withPosition(0, 0))
                .withGearRatio(SdsModuleConfigurations.MK4I_L2)
                .withDriveMotor(MotorType.FALCON, FRONT_LEFT_MODULE_DRIVE_MOTOR)
                .withSteerMotor(MotorType.FALCON, FRONT_LEFT_MODULE_STEER_MOTOR)
                .withSteerEncoderPort(FRONT_LEFT_MODULE_STEER_ENCODER).withSteerOffset(FRONT_LEFT_MODULE_STEER_OFFSET)
                .build();
        frontRightModule = new MkSwerveModuleBuilder()
                .withLayout(drivetrainTab.getLayout("Front Right Module", BuiltInLayouts.kList).withSize(2, 4)
                        .withPosition(2, 0))
                .withGearRatio(SdsModuleConfigurations.MK4I_L2)
                .withDriveMotor(MotorType.FALCON, FRONT_RIGHT_MODULE_DRIVE_MOTOR)
                .withSteerMotor(MotorType.FALCON, FRONT_RIGHT_MODULE_STEER_MOTOR)
                .withSteerEncoderPort(FRONT_RIGHT_MODULE_STEER_ENCODER).withSteerOffset(FRONT_RIGHT_MODULE_STEER_OFFSET)
                .build();
        backLeftModule = new MkSwerveModuleBuilder()
                .withLayout(drivetrainTab.getLayout("Back Left Module", BuiltInLayouts.kList).withSize(2, 4)
                        .withPosition(4, 0))
                .withGearRatio(SdsModuleConfigurations.MK4I_L2)
                .withDriveMotor(MotorType.FALCON, BACK_LEFT_MODULE_DRIVE_MOTOR)
                .withSteerMotor(MotorType.FALCON, BACK_LEFT_MODULE_STEER_MOTOR)
                .withSteerEncoderPort(BACK_LEFT_MODULE_STEER_ENCODER).withSteerOffset(BACK_LEFT_MODULE_STEER_OFFSET)
                .build();
        backRightModule = new MkSwerveModuleBuilder()
                .withLayout(drivetrainTab.getLayout("Back Right Module", BuiltInLayouts.kList).withSize(2, 4)
                        .withPosition(6, 0))
                .withGearRatio(SdsModuleConfigurations.MK4I_L2)
                .withDriveMotor(MotorType.FALCON, BACK_RIGHT_MODULE_DRIVE_MOTOR)
                .withSteerMotor(MotorType.FALCON, BACK_RIGHT_MODULE_STEER_MOTOR)
                .withSteerEncoderPort(BACK_RIGHT_MODULE_STEER_ENCODER).withSteerOffset(BACK_RIGHT_MODULE_STEER_OFFSET)
                .build();

        driveOdometry = new SwerveDriveOdometry(driveKinematics, getGyroscopeRotation(), getModulePositions());

        // Set up x axis controller
        xController = new PIDController(0.8, 0, 0);
        xController.setSetpoint(-3);
        xController.setTolerance(0.1);

        // Set up y axis controller
        yController = new PIDController(.41, 0, 0);
        yController.setSetpoint(0);
        yController.setTolerance(0.1);

        // Set up theta axis controller
        thetaController = new PIDController(0.04, 0, 0);
        thetaController.setSetpoint(0);
        thetaController.setTolerance(0.5);

        // Set up rotate to angle controller
        rotateToAngleController = new PIDController(0.07, 0, 0.001);
        rotateToAngleController.setTolerance(0.5);
        rotateToAngleController.enableContinuousInput(-180, 180);

        // Set up charge station controller
        chargeStationController = new PIDController(0.03, 0, 0);
        chargeStationController.setTolerance(0.5);
        chargeStationController.setSetpoint(0);
    }

    // Sets the gyroscope angle to zero
    public void zeroGyroscope() {
        navX.zeroYaw();
    }

    // Get the current gyroscope rotation
    public Rotation2d getGyroscopeRotation() {
        return Rotation2d.fromDegrees(-navX.getYaw());
    }

    // Get a reference to the drive kinematics object
    public SwerveDriveKinematics getDriveKinematics() {
        return driveKinematics;
    }

    // Get a reference to the drive odometry pose meters
    public Pose2d getPose() {
        return driveOdometry.getPoseMeters();
    }

    // Reset the drive odometry pose
    public void resetPose(Pose2d pose) {
        driveOdometry.resetPosition(getGyroscopeRotation(), getModulePositions(), pose);
    }

    // Get the positions of the drivetrain modules
    public SwerveModulePosition[] getModulePositions() {
        return new SwerveModulePosition[] {
                frontLeftModule.getPosition(),
                frontRightModule.getPosition(),
                backLeftModule.getPosition(),
                backRightModule.getPosition()
        };
    }

    // Get a reference to the navX object
    public AHRS getNavX() {
        return navX;
    }

    // Set the swerve module states
    public void setModuleStates(SwerveModuleState[] states) {
        SwerveDriveKinematics.desaturateWheelSpeeds(states, MAX_VELOCITY_METERS_PER_SECOND);

        frontLeftModule.set(states[1].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE,
                states[1].angle.getRadians() + Math.toRadians(180));
        frontRightModule.set(states[3].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE,
                states[3].angle.getRadians());
        backLeftModule.set(states[0].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE,
                states[0].angle.getRadians() + Math.toRadians(180));
        backRightModule.set(states[2].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE,
                states[2].angle.getRadians());
    }

    public double getCompass() {
        return navX.getCompassHeading();
    }

    // Drive the drivetrain with certain chassis speeds
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

        setModuleStates(driveKinematics.toSwerveModuleStates(chassisSpeeds));
    }

    // Get the current instance of the drivetrain
    public static Drivetrain getInstance() {
        // If the drivetrain is equal to null, then an instance has not been created yet
        // If this is the case, then create an instance
        if (drivetrain == null) {
            drivetrain = new Drivetrain();
        }

        return drivetrain;
    }
}
