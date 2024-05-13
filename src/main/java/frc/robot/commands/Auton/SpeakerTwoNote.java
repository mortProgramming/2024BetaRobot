package frc.robot.commands.Auton;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import static frc.robot.util.Constants.Intake.LIFTER_DOWN;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class SpeakerTwoNote extends SequentialCommandGroup {
    public SpeakerTwoNote() {
        addCommands(
                new ParallelCommandGroup(
                        new TimedDrive(1, 0, 0.8, 0),
                        new TimedShooter(1.75, -0.85)),
                new ParallelCommandGroup(
                        new TimedShooter(1, -0.85),
                        new TimedConveyor(1, -0.5)),
                        new TimedShooter(1, 0),
                       
                     new ParallelCommandGroup( 
                        new SetLifter(LIFTER_DOWN),
                        new TimedDrive(0.3, 0, 0.5,0)),
                        new TimedRoller(2, -0.3),
                        
                new ParallelCommandGroup(
                     new TimedDrive(1, 0, -0.5, 0),
                     new TimedShooter(1, -0.85)),
                new ParallelCommandGroup(
                        new TimedShooter(1, -0.85),
                        new TimedConveyor(1, -0.5)));
    }
}
