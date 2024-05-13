package frc.robot.commands.Auton;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class SpeakerOneNote extends SequentialCommandGroup {
    public SpeakerOneNote() {
        addCommands(
                new ParallelCommandGroup(
                        new TimedDrive(1, 0, 0.8, 0),
                        new TimedShooter(2, -0.85)),
                new ParallelCommandGroup(
                        new TimedShooter(1, -0.85),
                        new TimedConveyor(1, -0.5)),
                new ParallelCommandGroup(
                    new TimedDrive(1, 0, 0.5, 0)));
    }
}