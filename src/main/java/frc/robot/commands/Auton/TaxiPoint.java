package frc.robot.commands.Auton;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Auton.TimedDrive;

public class TaxiPoint extends SequentialCommandGroup {
    public TaxiPoint() {
        addCommands(new TimedDrive(2.5, 0, 0.5, 0));
    }
}

// IMPORTANT all of the auton stuf fin robot container is commented out make
// sure to find it line 29, 38, 38 and 68 i nrobot