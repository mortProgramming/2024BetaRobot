package frc.robot.commands.Auton;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

import frc.robot.commands.Auton.TimedDrive;
import frc.robot.commands.Auton.TimedShooter;
import frc.robot.commands.Auton.TimedConveyor;

public class RightSubAuto extends SequentialCommandGroup {
    public RightSubAuto() {
        addCommands(
                new SequentialCommandGroup(

                        new ParallelCommandGroup(
                                new TimedShooter(1, -0.88)),
                        new ParallelCommandGroup(
                                new TimedShooter(2, -1),
                                new TimedConveyor(3, -0.5))));
    }
}

// IMPORTANT all of the auton stuf fin robot container is commented out make
// sure to find it line 29, 38, 38 and 68 i nrobot