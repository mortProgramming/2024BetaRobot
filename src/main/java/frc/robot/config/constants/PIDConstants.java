package frc.robot.config.constants;

public final class PIDConstants {
    
    public final class Drivetrain {

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
}
