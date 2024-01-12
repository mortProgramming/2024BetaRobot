package frc.robot.util;

import com.swervedrivespecialties.swervelib.SdsModuleConfigurations;

import edu.wpi.first.math.util.Units;



public final class Constants {
	public static final class OperatorConstants {
		public static final int JOYSTICK_PORT = 0;
		public static final int CONTROLLER_PORT = 2;

		public static final int RESET_GYRO_BUTTON = 1; // on joystick 0
	}

	public static final class DrivetrainMotors {
		// Front Left
		public static final int FRONT_LEFT_MODULE_DRIVE_MOTOR = 5;
		public static final int FRONT_LEFT_MODULE_STEER_MOTOR = 7;
		public static final int FRONT_LEFT_MODULE_STEER_ENCODER = 8;
		public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(335);
		// Front Right
		public static final int FRONT_RIGHT_MODULE_DRIVE_MOTOR = 6;
		public static final int FRONT_RIGHT_MODULE_STEER_MOTOR = 3;
		public static final int FRONT_RIGHT_MODULE_STEER_ENCODER = 9;
		public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(134);
		// Back Left
		public static final int BACK_LEFT_MODULE_DRIVE_MOTOR = 4;
		public static final int BACK_LEFT_MODULE_STEER_MOTOR = 0;
		public static final int BACK_LEFT_MODULE_STEER_ENCODER = 10;
		public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(18);
		// Back Right
		public static final int BACK_RIGHT_MODULE_DRIVE_MOTOR = 2;
		public static final int BACK_RIGHT_MODULE_STEER_MOTOR = 1;
		public static final int BACK_RIGHT_MODULE_STEER_ENCODER = 11;
		public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(306);
	}

	public static final class DrivetrainSpecs {
		// The left-to-right distance between the drivetrain wheels measured from center
		// to center.
		public static final double DRIVETRAIN_TRACKWIDTH_METERS = Units.inchesToMeters(20.75);
		// The front-to-back distance between the drivetrain wheels measured from center
		// to center.
		public static final double DRIVETRAIN_WHEELBASE_METERS = Units.inchesToMeters(20.75);

		public static final double MAX_VOLTAGE = 12.0;

		public static final double MAX_VELOCITY_METERS_PER_SECOND = 6380.0 / 60.0
				* SdsModuleConfigurations.MK4I_L2.getDriveReduction()
				* SdsModuleConfigurations.MK4I_L2.getWheelDiameter() * Math.PI;
		public static final double MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND = MAX_VELOCITY_METERS_PER_SECOND
				/ Math.hypot(DRIVETRAIN_TRACKWIDTH_METERS / 2.0, DRIVETRAIN_WHEELBASE_METERS / 2.0);
	}

	public static final class VisionConstants {
		public static final double LIMELIGHT_PITCH = Units.degreesToRadians(15);
		public static final double NODE_APRILTAG_HEIGHT = Units.inchesToMeters(27.5);
		public static final double LIMELIGHT_HEIGHT = Units.inchesToMeters(15.2);

		public static final double DISTANCE_AWAY = Units.inchesToMeters(43);

	}

	public enum LimelightPipeline {
		APRILTAG(1), REFLECTIVE(2), DRIVER(3);

		private final int id;

		LimelightPipeline(int id) {
			this.id = id;
		}

		public int id() {
			return id;
		}
	}
}
