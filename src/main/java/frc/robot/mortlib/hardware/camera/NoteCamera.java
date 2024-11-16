package frc.robot.mortlib.hardware.camera;

import frc.robot.mortlib.hardware.brands.limelight.LimelightNoteCamera;

public class NoteCamera implements NoteCameraIntf {

    public NoteCameraIntf camera;

    public NoteCameraTypeEnum cameraType;

    public String cameraName;

    public NoteCamera(NoteCameraTypeEnum cameraType, String cameraName) {
        this.cameraType = cameraType;
        this.cameraName = cameraName;

        switch (cameraType) {
            // case PhotonVision:
            //     camera = new PhotonVisionTagCamera(cameraName);
            //     break;
            case LimeLight:
                camera = new LimelightNoteCamera(cameraName);
                break;
        }
    }

    public void setLights(int input) {
		camera.setLights(input);
	}

   public double[] getPicturePosition() {
        return camera.getPicturePosition();
    }

    public String getCameraName() {
        return camera.getCameraName();
    }
}
