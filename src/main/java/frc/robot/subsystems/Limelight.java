package frc.robot.subsystems;

import frc.robot.util.Constants.LimelightPipeline;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {
	private static Limelight limelight;
	private static NetworkTable llTable;

	private Limelight() {
		llTable = NetworkTableInstance.getDefault().getTable("limelight");
	}

	public void setPipeline(LimelightPipeline pipe) {
		llTable.getEntry("pipeline").setNumber(pipe.id());
	}

	public LimelightPipeline getPipeline() {
		int id = (int) llTable.getEntry("pipeline").getInteger(0);

		if (id == 0) {
			return LimelightPipeline.APRILTAG;
		} else if (id == 1) {
			return LimelightPipeline.REFLECTIVE;
		} else if (id == 2) {
			return LimelightPipeline.DRIVER;
		}

		return LimelightPipeline.DRIVER;
	}

	public boolean hasTarget() {
		return llTable.getEntry("tv").getInteger(0) == 1;
	}

	public double getTargetArea() {
		return llTable.getEntry("ta").getDouble(0);
	}

	// Translation (x,y,z) Rotation(pitch,yaw,roll)
	public Number[] getCamTran() {
		return llTable.getEntry("camtran").getNumberArray(new Number[0]);
	}

	public double getX() {
		return (double) getCamTran()[0];
	}

	public double getY() {
		return (double) getCamTran()[1];
	}

	public double getZ() {
		return (double) getCamTran()[2];
	}

	public double getPitch() {
		return (double) getCamTran()[3];
	}

	public double getYaw() {
		return (double) getCamTran()[4];
	}

	public double getRoll() {
		return (double) getCamTran()[5];
	}

	public Number[] getPose() {
		return llTable.getEntry("botpose").getNumberArray(null);
	}

	public static Limelight getInstance() {
		if (limelight == null) {
			limelight = new Limelight();
		}
		return limelight;
	}
}
