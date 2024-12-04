package frc.robot.config.constants;

import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;

public final class PIDConstants {
    
    public final class Drivetrain {
		public final static double TO_POS_KP = 0.5;
		public final static double TO_POS_KI = 0;
		public final static double TO_POS_KD = 0;
		public static final Constraints TO_POS_CONSTRAINTS = new Constraints(10, 10);
		public final static double TO_POS_POS_TOLERANCE = 0.05;
		
		public final static double TO_ANGLE_KP = 0.07;
		public final static double TO_ANGLE_KI = 0;
		public final static double TO_ANGLE_KD = 0.001;
		public static final Constraints TO_ANGLE_CONSTRAINTS = new Constraints(300, 300);
		public final static double TO_ANGLE_POS_TOLERANCE = 3;
		public final static double TO_ANGLE_VEL_TOLERANCE = 30;

		//0.1, 0, 0

        public static final double AUTON_POS_KP = 0.315;
		public static final double AUTON_POS_KI = 0;
		public static final double AUTON_POS_KD = 0.001;

		//0.01, 0, 0.0001
	
		public static final double AUTON_ROTATION_KP = 1.45;
		public static final double AUTON_ROTATION_KI = 0;
		public static final double AUTON_ROTATION_KD = 0;

		//ks 0.667, kv 2, ka 0.2
    }

	public static final class Lifter {
		public static final double TO_POS_KP = 0.04;
		public static final double TO_POS_KI = 0;
		public static final double TO_POS_KD = 0;
		public static final Constraints TO_POS_CONSTRAINTS = new Constraints(50, 20);

		public static final double TO_POS_KG = 0.3;
	}
}
