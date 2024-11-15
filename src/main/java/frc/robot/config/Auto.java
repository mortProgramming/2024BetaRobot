package frc.robot.config;

import frc.robot.commands.autons.pathplanned.GetPlanned;
import frc.robot.commands.autons.timed.Taxi;
import frc.robot.mortlib.swerve.PathPlanner;
import frc.robot.subsystems.Drivetrain;
import static frc.robot.config.constants.PIDConstants.Drivetrain.*;
import static frc.robot.config.constants.PhysicalConstants.Drivetrain.*;

import com.pathplanner.lib.util.PIDConstants;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

public class Auto {

	private static Drivetrain drivetrain;

	private static SendableChooser<Command> autoChooser;
	
	public static void configure() {
		drivetrain = Drivetrain.getInstance();

        autoChooser = new SendableChooser<Command>();
		configureAutoBuilder();
		addAutoOptions();
		SmartDashboard.putData(autoChooser);
	}

	public static void configureAutoBuilder() {
		drivetrain.setGyroscopeZero(0);

		PathPlanner.configure(
			drivetrain, drivetrain.getSwerveDrive(),
			new PIDConstants(AUTON_POS_KP, AUTON_POS_KI, AUTON_POS_KD), 
			new PIDConstants(AUTON_ROTATION_KP, AUTON_ROTATION_KI, AUTON_ROTATION_KD), 
			DRIVEBASE_RADIUS_METERS
		);
	}
	
	public static void addAutoOptions () {
		autoChooser.setDefaultOption("nothing", null);

		autoChooser.addOption("Forward", new Taxi());
		autoChooser.addOption("Oval", GetPlanned.oval());
		autoChooser.addOption("Square", GetPlanned.square());
		autoChooser.addOption("T", GetPlanned.t());
	}

	public static Command getAutonomousCommand () {
		return autoChooser.getSelected();
	}
}
