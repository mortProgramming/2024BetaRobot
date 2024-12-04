package frc.robot.config.constants;

import edu.wpi.first.math.util.Units;

public final class PhysicalConstants {

	public final static double VOLTAGE = 12;

	public static final class Convey {
		public static final double CONVEY_SPEED = 0.2;
	}
    public final static class Drivetrain {
        // The left-to-right distance between the drivetrain wheels measured from center
		// to center.
		public static final double DRIVETRAIN_TRACKWIDTH_METERS = Units.inchesToMeters(22.625);
		// The front-to-back distance between the drivetrain wheels measured from center
		// to center.
		public static final double DRIVETRAIN_WHEELBASE_METERS = Units.inchesToMeters(22.625);

		public static final double DRIVEBASE_RADIUS_METERS = Math.hypot(
			DRIVETRAIN_TRACKWIDTH_METERS / 2.0, DRIVETRAIN_WHEELBASE_METERS / 2.0
		);

		public static final int FRONT_LEFT_OFFSET = 270;
		public static final int FRONT_RIGHT_OFFSET = 87;
		public static final int BACK_LEFT_OFFSET = 272;
		public static final int BACK_RIGHT_OFFSET = 74;

		public static final int IMU_TO_ROBOT_FRONT_ANGLE = 0;
    }

	public static final class Intake {
		public static final double INTAKE_SPEED = 0.45;
	}

	public static final class Lifter {
		public static final double LIFTER_ARM_OFFSET_DEG = -5;

		public static final double LIFTER_GEAR_RATIO = 100;

		public static final double LIFTER_UP = 80;
		public static final double LIFTER_DOWN = -2;
	}

	public static final class Shooter {
		public static final double SHOOTER_SPEED = 0.45;
	}
}
