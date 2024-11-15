package frc.robot.subsystems;

import frc.robot.mortlib.hardware.camera.TagCamera;
import frc.robot.mortlib.hardware.camera.TagCameraTypeEnum;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Vision extends SubsystemBase {

    private static Vision vision;

    public TagCamera frontCamera;

	private AprilTagFieldLayout tagLayout;

    private Vision() {
		frontCamera = new TagCamera(TagCameraTypeEnum.LimeLight, "front");

		tagLayout = AprilTagFieldLayout.loadField(AprilTagFields.k2024Crescendo);
	}

	public Pose2d getTagPosition (int tagID) {
		return tagLayout.getTagPose(tagID).get().toPose2d();
	}
    
    public static Vision getInstance() {
		if (vision == null) {
			vision = new Vision();
		}
		return vision;
	}
}
