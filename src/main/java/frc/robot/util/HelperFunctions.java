package frc.robot.util;

public class HelperFunctions {
    // https://stackoverflow.com/questions/1878907/how-can-i-find-the-smallest-difference-between-two-angles-around-a-point
    public static double getSignedAngleBetween(double angle1Degrees, double angle2Degrees) {
        double innerAngle = (angle1Degrees - angle2Degrees) % 360.0;
        double outerAngle = (angle2Degrees - angle1Degrees) % 360.0;

        if (innerAngle < outerAngle) {
            return -innerAngle;
        } else {
            return outerAngle;
        }
    }
}