package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeConveyor;
import frc.robot.util.Operator;


/** Add your docs here. */
public class IntakeConveyorControl extends Command{


    private static IntakeConveyor intakeConveyor;
    /** Creates a new IntakeLifterControl. 
     * */
    public void IntakeConveyor() {
      // Use addRequirements() here to declare subsystem dependencies.
  
      intakeConveyor = IntakeConveyor.getInstance();
      addRequirements(intakeConveyor);
      
}
@Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override   
  public  void execute() {

    //if (Operator.yButton() == true){
      //IntakeConveyor.setMotor(-0.25);
    //}
    //else {
      //IntakeConveyor.setMotor(0);
}
  }  
//}