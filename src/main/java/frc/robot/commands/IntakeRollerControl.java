package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeRoller;
import frc.robot.util.Operator;
import frc.robot.subsystems.IntakeConveyor;
/*  
/** Add your docs here. */
public class IntakeRollerControl extends Command{


    private static IntakeRoller intakeRoller;

    private static IntakeConveyor intakeConveyor;
    /** Creates a new IntakeLifterControl. */
    public IntakeRollerControl() {
      // Use addRequirements() here to declare subsystem dependencies.
  
      intakeRoller = IntakeRoller.getInstance();
      addRequirements(intakeRoller);
     
      intakeConveyor = IntakeConveyor.getInstance();
      addRequirements(intakeConveyor);
      
}
@Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override   
  public  void execute() {

    if (Operator.bButton() == true){
      IntakeRoller.setMotor(-0.50);
    IntakeConveyor.setMotor(-0.50);
    }
    else {
      IntakeRoller.setMotor(0);
      IntakeConveyor.setMotor(0);
      if (Operator.yButton() == true){
      IntakeRoller.setMotor(0.50);
    IntakeConveyor.setMotor(0.50);
    }
    else {
      IntakeRoller.setMotor(0);
      IntakeConveyor.setMotor(0);
}
  } 
}
}

