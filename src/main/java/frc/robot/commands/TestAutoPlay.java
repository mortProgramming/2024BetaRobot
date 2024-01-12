package frc.robot.commands;

import java.io.IOException;
import java.nio.file.Path;

import frc.robot.subsystems.Drivetrain;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class TestAutoPlay extends SequentialCommandGroup {
	private Drivetrain drivetrain;

	public TestAutoPlay() {
		drivetrain = Drivetrain.getInstance();
		addRequirements(drivetrain);

		Trajectory testTrajectory = openTrajectoryFile("Test4.wpilib.json");
		addCommands(new DriveAtPath(testTrajectory, new Rotation2d(90))
		// new RotateToAngle(90, true)
		);
	}

	public Trajectory openTrajectoryFile(String name) {
		try {
			Trajectory t = new Trajectory();
			Path path = Filesystem.getDeployDirectory().toPath().resolve("pathplanner/generatedJSON/" + name);
			t = TrajectoryUtil.fromPathweaverJson(path);
			return t;
		} catch (IOException ex) {
			DriverStation.reportError("Unable to open trajectory: " + name, ex.getStackTrace());
			return null;
		}
	}
}
