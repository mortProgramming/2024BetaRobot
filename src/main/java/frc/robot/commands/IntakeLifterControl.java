package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeLifter;
import frc.robot.util.Operator;

public class IntakeLifterControl extends Command {
    private static IntakeLifter intakeLifter;

    public IntakeLifterControl() {
        intakeLifter = IntakeLifter.getInstance();

        addRequirements(intakeLifter);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        // if (Operator.xButton() == true){
        // IntakeLifter.setMotor(1);
        // }
        // else {
        // IntakeLifter.setMotor(0);
        // }
        
        IntakeLifter.setMotor(0.35 * -Operator.leftJoyStick());
    }
}