package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

import frc.robot.commands.TimedDrive;
import frc.robot.commands.TimedShooter;
import frc.robot.commands.TimedConveyor;
 
public class ShooterAut extends SequentialCommandGroup {
    public ShooterAut() {
        addCommands(
            new SequentialCommandGroup(
               
    
                new ParallelCommandGroup(
                    new TimedDrive(1.1, 0, 1, 0),           
                    new TimedShooter(3, -0.88)
                ),
                new ParallelCommandGroup(
                    new TimedShooter(2, -1),
                    new TimedConveyor(3, -0.5)
                )
            )
        );
    }
}

// IMPORTANT all of the auton stuf fin robot container is commented out make
// sure to find it line 29, 38, 38 and 68 i nrobot