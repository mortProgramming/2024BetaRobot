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
        public static final int FRONT_LEFT_MODULE_DRIVE_MOTOR = 2;
        public static final int FRONT_LEFT_MODULE_STEER_MOTOR = 1;
        public static final int FRONT_LEFT_MODULE_STEER_ENCODER = 11;
        public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(42.8 + 90); // 43.15-45
        // Front Right
        public static final int FRONT_RIGHT_MODULE_DRIVE_MOTOR = 4;
        public static final int FRONT_RIGHT_MODULE_STEER_MOTOR = 3;
        public static final int FRONT_RIGHT_MODULE_STEER_ENCODER = 10;
        public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(323.9 + 85); // 231.3+140+180+90
        // Back Left
        public static final int BACK_LEFT_MODULE_DRIVE_MOTOR = 8;
        public static final int BACK_LEFT_MODULE_STEER_MOTOR = 7;
        public static final int BACK_LEFT_MODULE_STEER_ENCODER = 9;
        public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(127.8 + 85); // 125.7-45
        // Back Right
        public static final int BACK_RIGHT_MODULE_DRIVE_MOTOR = 6;
        public static final int BACK_RIGHT_MODULE_STEER_MOTOR = 5;
        public static final int BACK_RIGHT_MODULE_STEER_ENCODER = 13;
        public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(207.3 + 90); // 208.2-45
        // Intake Stuff REMEMBER we switched CAN ID of lifter with conveyor:ID 18
        public static final int INTAKE_LIFTER_MOTOR = 19;
        public static final int INTAKE_ROLLER_MOTOR = 12;
        // switched intake roller andconveyor ID rfoller should be 12
        public static final int LEFT_SHOOTER = 16;
        public static final int RIGHT_SHOOTER = 17;
        public static final int CONVEYOR_MOTOR = 18;
        public static final int LEFT_CLIMBER = 0;
        public static final int RIGHT_CLIMBER = 0;
        // 0 until you make the number something
        public static final double LIFTER_PID_P = 0.0001;
        public static final double LIFTER_PID_I = 0;
        public static final double LIFTER_PID_D = 0;
        public static final double LIFTER_UP = 0;
        public static final double LIFTER_DOWN = 0;
    
    }

    public static final class DrivetrainSpecs {
        // The left-to-right distance between the drivetrain wheels measured from center
        // to center.
        public static final double DRIVETRAIN_TRACKWIDTH_METERS = Units.inchesToMeters(22.625);
        // The front-to-back distance between the drivetrain wheels measured from center
        // to center.
        public static final double DRIVETRAIN_WHEELBASE_METERS = Units.inchesToMeters(22.625);

        public static final double MAX_VOLTAGE = 12.0;

        public static final double MAX_VELOCITY_METERS_PER_SECOND = 5636 / 60.0
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

    // intake stuff
    public static final class Intake {
        public static final int ROLL_MOTOR = 0;
        public static int LIFT_MOTOR = 0;
        public static int CONVEYOR_MOTOR = 0;
        public static int CLIMBER_MOTOR = 0;

    }

}
