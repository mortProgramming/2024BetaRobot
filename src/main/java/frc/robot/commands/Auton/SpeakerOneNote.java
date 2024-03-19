package frc.robot.commands.Auton;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class SpeakerOneNote extends SequentialCommandGroup {
    public SpeakerOneNote() {
        addCommands(
                new ParallelCommandGroup(
                        new TimedDrive(1.1, 0, 1, 0),
                        new TimedShooter(3, -0.88)),
                new ParallelCommandGroup(
                        new TimedShooter(2, -1),
                        new TimedConveyor(3, -0.5)));
    }
}