package frc.robot.commands.Auton;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class RightSubAuto extends SequentialCommandGroup {
    public RightSubAuto() {
        addCommands(
                new TimedShooter(2, -0.85),
                new TimedConveyor(1, -0.5),
                new TimedShooter(1, 0),
                new TimedDrive(3, 0, 0.8, 0));
    }
}