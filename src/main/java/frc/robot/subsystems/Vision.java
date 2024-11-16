package frc.robot.subsystems;

import frc.robot.mortlib.hardware.camera.NoteCamera;
import frc.robot.mortlib.hardware.camera.NoteCameraTypeEnum;
import frc.robot.mortlib.hardware.camera.TagCamera;
import frc.robot.mortlib.hardware.camera.TagCameraTypeEnum;

import static frc.robot.config.constants.PortConstants.Vision.*;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Vision extends SubsystemBase {

    private static Vision vision;

	private NoteCamera noteCamera;
    private TagCamera tagCamera;

	private AprilTagFieldLayout tagLayout;

    private Vision() {
		noteCamera = new NoteCamera(NoteCameraTypeEnum.LimeLight, NOTE_CAMERA);
		tagCamera = new TagCamera(TagCameraTypeEnum.LimeLight, TAG_CAMERA);

		tagLayout = AprilTagFieldLayout.loadField(AprilTagFields.k2024Crescendo);
	}

	public Pose2d getTagPosition(int tagID) {
		return tagLayout.getTagPose(tagID).get().toPose2d();
	}

	public NoteCamera getNoteCamera() {
		return noteCamera;
	}

	public TagCamera getTagCamera() {
		return tagCamera;
	}
    
    public static Vision getInstance() {
		if (vision == null) {
			vision = new Vision();
		}
		return vision;
	}
}
