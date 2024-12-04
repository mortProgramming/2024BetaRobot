package frc.robot.commands.actions.endeffector;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class MoveNote extends ParallelCommandGroup {
    public MoveNote(double intakeSpeed, double conveyorSpeed) {
        addCommands(
            new Intake(intakeSpeed),
            new Convey(conveyorSpeed)
        );
    }

    public MoveNote(double time, double intakeSpeed, double conveyorSpeed) {
        addCommands(
            new Intake(time, intakeSpeed),
            new Convey(time, conveyorSpeed)
        );
    }
}
