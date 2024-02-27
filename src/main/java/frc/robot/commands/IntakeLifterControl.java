package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeLifter;
import frc.robot.util.Operator;

public class IntakeLifterControl extends Command {

  private static IntakeLifter intakeLifter;
  /** Creates a new IntakeLifterControl. */
  public IntakeLifterControl() {
    // Use addRequirements() here to declare subsystem dependencies.
    intakeLifter = IntakeLifter.getInstance();  
    addRequirements(intakeLifter);
 
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public  void execute() {
    // if (Operator.xButton() == true){
    //   IntakeLifter.setMotor(1);
    // }
    // else {
    //   IntakeLifter.setMotor(0);
    // }
     IntakeLifter.setMotor(0.15* Operator.leftJoyStick());

  }
}

//commentedf out excecute for the lifter button