package frc.robot;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PS5Controller;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.commands.DriveControl;
import frc.robot.commands.ShooterControl;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.IntakeLifter;
import frc.robot.subsystems.Shooter;
import frc.robot.commands.IntakeRollerControl;
import frc.robot.subsystems.IntakeRoller;
import frc.robot.commands.IntakeLifterControl;
import frc.robot.subsystems.IntakeConveyor;
import frc.robot.commands.IntakeConveyorControl;
import frc.robot.commands.Auton.Taxi;
import frc.robot.commands.Auton.RightSubAuto;
import frc.robot.commands.Auton.SpeakerOneNote;
import frc.robot.commands.Auton.SpeakerTwoNote;
import frc.robot.subsystems.Climber;
import frc.robot.commands.ClimberControl;


import static frc.robot.util.Constants.Control.CONTROLLER_PORT;
import static frc.robot.util.Constants.Control.JOYSTICK_PORT;
import static frc.robot.util.Constants.Control.PS5_CONTROLLER_PORT;
import static frc.robot.util.Constants.Drivetrain.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND;
import static frc.robot.util.Constants.Drivetrain.MAX_VELOCITY_METERS_PER_SECOND;

import java.security.CodeSource;

public class RobotContainer {
    private final Drivetrain drivetrain = Drivetrain.getInstance();
    private final Shooter shooter = Shooter.getInstance();
    private final IntakeRoller intakeRoller = IntakeRoller.getInstance();
    private final IntakeLifter intakeLifter = IntakeLifter.getInstance();
    private final IntakeConveyor intakeConveyor = IntakeConveyor.getInstance();
    private final Climber climber = Climber.getInstance();

    private SendableChooser<Command> autoChooser = new SendableChooser<Command>();

    private Joystick joystick = new Joystick(JOYSTICK_PORT);
    private XboxController controller = new XboxController(CONTROLLER_PORT);
    private PS5Controller psController = new PS5Controller(PS5_CONTROLLER_PORT);

    private DigitalInput sensor;
//fix the constructor might just redo it completely
    public RobotContainer() {
        // Set the subsystem control commands
        drivetrain.setDefaultCommand(new DriveControl(
                () -> -modifyAxis(psController.getLeftX(), joystick.getThrottle()) * MAX_VELOCITY_METERS_PER_SECOND,
                () -> -modifyAxis(-psController.getLeftY(),joystick.getThrottle()) * MAX_VELOCITY_METERS_PER_SECOND,
                () -> -modifyAxis(joystick.getThrottle())
                        * -MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND));
        shooter.setDefaultCommand(new ShooterControl());
        intakeRoller.setDefaultCommand(new IntakeRollerControl());
        intakeLifter.setDefaultCommand(new IntakeLifterControl());
        intakeConveyor.setDefaultCommand(new IntakeConveyorControl());
        climber.setDefaultCommand(new ClimberControl());

        // Make the joystick trigger zero the gyroscope when pressed
        new Trigger(joystick::getTrigger).whileTrue(new InstantCommand(drivetrain::zeroGyroscope));

        new Trigger(() -> joystick.getRawButton(3)).whileTrue(
                new InstantCommand(() -> drivetrain.drive(new ChassisSpeeds(SmartDashboard.getNumber("vx", 0),
                        SmartDashboard.getNumber("vy", 0), SmartDashboard.getNumber("omega", 0)))));

        drivetrain.resetPose(new Pose2d(0, 0, new Rotation2d(0, 0)));
        sensor = new DigitalInput(0);

        // Zero the drivetrain gyroscope
        // drivetrain.zeroGyroscope();

        // Add the autonomous mode options to smart dashboard
        autoChooser.setDefaultOption("None", null);
        autoChooser.addOption("Taxi", new Taxi());
        autoChooser.addOption("Center Speaker One Note", new SpeakerOneNote());
        autoChooser.addOption("Right Sub", new RightSubAuto());
        autoChooser.addOption("Center Speaker Two Note", new SpeakerTwoNote());

        SmartDashboard.putData(autoChooser);
    }

    public void displaySmartDashboard() {
        SmartDashboard.putNumber("compass", drivetrain.getCompass());
        SmartDashboard.putNumber("angle", drivetrain.getGyroscopeRotation().getDegrees());
        SmartDashboard.putNumber("yaw", drivetrain.getNavX().getYaw());
        SmartDashboard.putNumber("pitch", drivetrain.getNavX().getPitch());
        SmartDashboard.putNumber("roll", drivetrain.getNavX().getRoll());
        SmartDashboard.putBoolean("ir sensor", sensor.get());
    }

    public Command getAutonomousCommand() {
        return autoChooser.getSelected();
    }

    private static double deadband(double value, double deadband) {
        if (Math.abs(value) > deadband) {
            if (value > 0.0) {
                return (value - deadband) / (1.0 - deadband);
            } else {
                return (value + deadband) / (1.0 - deadband);
            }
        } else {
            return 0.0;
        }
    }

    private static double modifyAxis(double value, double throttleValue) {
        // Deadband
        value = deadband(value, 0.1);

        // Square the axis
        value = Math.copySign(value * value, value);

        // takes the throttle value and takes it from [-1, 1] to [0.2, 1], and multiplies it by the value
        return value * (throttleValue * -0.4 + 0.6);
    }

}
