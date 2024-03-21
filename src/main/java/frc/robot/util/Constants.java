package frc.robot.util;

import com.swervedrivespecialties.swervelib.SdsModuleConfigurations;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.units.Unit;

public final class Constants {
    // Class that contains all constants relating to the controllers
    public static final class Control {
        public static final int JOYSTICK_PORT = 0;
        public static final int CONTROLLER_PORT = 2;

        public static final int RESET_GYRO_BUTTON = 1; // on joystick 0
    }

    // Class tha contains all constants relating to the drivetrain
    public static final class Drivetrain {
        // Front Left
        public static final int FRONT_LEFT_MODULE_DRIVE_MOTOR = 2;
        public static final int FRONT_LEFT_MODULE_STEER_MOTOR = 1;
        public static final int FRONT_LEFT_MODULE_STEER_ENCODER = 11;
        public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(42.8 + 90);

        // Front Right
        public static final int FRONT_RIGHT_MODULE_DRIVE_MOTOR = 4;
        public static final int FRONT_RIGHT_MODULE_STEER_MOTOR = 3;
        public static final int FRONT_RIGHT_MODULE_STEER_ENCODER = 10;
        public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(323.9 + 85);

        // Back Left
        public static final int BACK_LEFT_MODULE_DRIVE_MOTOR = 8;
        public static final int BACK_LEFT_MODULE_STEER_MOTOR = 7;
        public static final int BACK_LEFT_MODULE_STEER_ENCODER = 9;
        public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(127.8 + 85);

        // Back Right
        public static final int BACK_RIGHT_MODULE_DRIVE_MOTOR = 6;
        public static final int BACK_RIGHT_MODULE_STEER_MOTOR = 5;
        public static final int BACK_RIGHT_MODULE_STEER_ENCODER = 13;
        public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(207.3 + 90);

        public static final double DRIVETRAIN_WHEEL_CIRCUMFERENCE = Math.PI * 4; // 2 * PI * RADIUS || PI * DIAMETER

        // The left-to-right distance between the drivetrain wheels measured from center to center.
        public static final double DRIVETRAIN_TRACKWIDTH_METERS = Units.inchesToMeters(22.625);

        // The front-to-back distance between the drivetrain wheels measured from center to center.
        public static final double DRIVETRAIN_WHEELBASE_METERS = Units.inchesToMeters(22.625);

        public static final double MAX_VOLTAGE = 12.0;

        public static final double MAX_VELOCITY_METERS_PER_SECOND = 5636 / 60.0
                * SdsModuleConfigurations.MK4I_L2.getDriveReduction()
                * SdsModuleConfigurations.MK4I_L2.getWheelDiameter() * Math.PI;
        public static final double MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND = 0.9 * (MAX_VELOCITY_METERS_PER_SECOND
                / Math.hypot(DRIVETRAIN_TRACKWIDTH_METERS / 2.0, DRIVETRAIN_WHEELBASE_METERS / 2.0));
    }

    // Class containing all constants relating to the climber
    public static final class Climber {
        // Motor IDs
        public static final int LEFT_CLIMBER_MOTOR_ID = 15;
        public static final int RIGHT_CLIMBER_MOTOR_ID = 14;

        // PID values
        public static final double RIGHT_CLIMBER_PID_P = 0.0001;
        public static final double RIGHT_CLIMBER_PID_I = 0;
        public static final double RIGHT_CLIMBER_PID_D = 0;

        public static final double LEFT_CLIMBER_PID_P = 0.0001;
        public static final double LEFT_CLIMBER_PID_I = 0;
        public static final double LEFT_CLIMBER_PID_D = 0;

        // Climber encoder ranges *** These values are being ignored for now
        public static final double LEFT_CLIMBER_MAX = 83;
        public static final double LEFT_CLIMBER_MIN = 0;
        public static final double RIGHT_CLIMBER_MAX = 107;
        public static final double RIGHT_CLIMBER_MIN = 185;
    }

    // Class containing all constants relating to the intake
    public static final class Intake {
        // Motor IDs
        public static final int LIFTER_MOTOR_ID = 19;
        public static final int ROLLER_MOTOR_ID = 12;
        public static final int CONVEYOR_MOTOR_ID = 18;

        // PID values
        public static final double LIFTER_PID_P = 0.0001;
        public static final double LIFTER_PID_I = 0;
        public static final double LIFTER_PID_D = 0;

        // Intake encoder position values
        public static final double LIFTER_UP = -24;
        public static final double LIFTER_DOWN = 0;
    }

    // Class containing all constants relating to the shooter
    public static final class Shooter {
        // Motor IDs
        public static final int LEFT_SHOOTER_MOTOR_ID = 16;
        public static final int RIGHT_SHOOTER_MOTOR_ID = 17;
    }
}
