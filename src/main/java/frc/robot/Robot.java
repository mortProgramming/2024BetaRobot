package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.cameraserver.CameraServer;

public class Robot extends TimedRobot {
    private RobotContainer robotContainer;

    private Command autonomousCommand;

    @Override
    public void robotInit() {
        robotContainer = new RobotContainer();

        CameraServer.startAutomaticCapture();

        SmartDashboard.putNumber("vx", 0);
        SmartDashboard.putNumber("vy", 0);
        SmartDashboard.putNumber("omega", 0);
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
        robotContainer.displaySmartDashboard();
    }

    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
    }

    @Override
    public void autonomousInit() {
        // Get the selected autonomous command
        autonomousCommand = robotContainer.getAutonomousCommand();

        // If the command is not equal to null, then run it
        if (autonomousCommand != null) {
            autonomousCommand.schedule();
        }
    }

    @Override
    public void autonomousPeriodic() {
    }

    @Override
    public void teleopInit() {
        // Once the teleop period starts, disable the autonomous command that was chosen
        if (autonomousCommand != null) {
            autonomousCommand.cancel();
        }
    }

    @Override
    public void teleopPeriodic() {
    }

    @Override
    public void testInit() {
        // Cancels all running commands at the start of test mode
        CommandScheduler.getInstance().cancelAll();
    }

    @Override
    public void testPeriodic() {
    }
}
