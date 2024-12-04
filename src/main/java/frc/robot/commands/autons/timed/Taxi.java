package frc.robot.commands.autons.timed;

import frc.robot.commands.actions.drivetrain.TimedDrive;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Taxi extends SequentialCommandGroup {
  /** Move the robot forward, far enough to gain taxi points. */
  public Taxi() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new TimedDrive(5, 0, 0.5, 0)
      
    );
  }
}
