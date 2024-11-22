package frc.robot.commands.actions.endeffector;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class MoveNote extends SequentialCommandGroup {
    public MoveNote(double intakeSpeed, double conveyorSpeed) {
        addCommands(
            new Intake(intakeSpeed),
            new Convey(conveyorSpeed)
        );
    }
}
