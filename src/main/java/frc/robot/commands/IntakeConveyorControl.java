// commented everything out to try to import roller into timed conveyor 
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeConveyor;
import frc.robot.util.Operator;

/** Add your docs here. */
public class IntakeConveyorControl extends Command {

    private static IntakeConveyor intakeConveyor;
    public IntakeConveyorControl() {
        // Use addRequirements() here to declare subsystem dependencies.

        intakeConveyor = IntakeConveyor.getInstance();
        addRequirements(intakeConveyor);

    }

    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
       if (Operator.bButton()){
           intakeConveyor.setMotor(-0.50);
         } 
         else if(Operator.yButton()){
                intakeConveyor.setMotor(0.4);
            }
         else {
           intakeConveyor.setMotor(0);
         }
     }
    }      