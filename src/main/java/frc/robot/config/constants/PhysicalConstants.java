package frc.robot.config.constants;

import edu.wpi.first.math.util.Units;

public final class PhysicalConstants {
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
		public static final int FRONT_RIGHT_OFFSET = 88;
		public static final int BACK_LEFT_OFFSET = 268;
		public static final int BACK_RIGHT_OFFSET = 332;

		public static final int IMU_TO_ROBOT_FRONT_ANGLE = 0;
    }
}
