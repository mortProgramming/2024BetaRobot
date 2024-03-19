package frc.robot.commands.Auton;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class Taxi extends SequentialCommandGroup {
    public Taxi() {
        addCommands(new TimedDrive(2.5, 0, 0.5, 0));
    }
}