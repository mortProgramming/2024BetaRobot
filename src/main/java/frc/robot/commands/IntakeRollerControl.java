package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;

//All conveyor stuff in this command is commented out

import frc.robot.subsystems.IntakeRoller;
import frc.robot.util.Operator;
//import frc.robot.subsystems.IntakeConveyor;

public class IntakeRollerControl extends Command {
    private static IntakeRoller intakeRoller;
    //private static IntakeConveyor intakeConveyor;

    public IntakeRollerControl() {
        intakeRoller = IntakeRoller.getInstance();
        // intakeConveyor = IntakeConveyor.getInstance();

        //removed intake conveyor add requirement in this command only in conveyor ommand now
        addRequirements(intakeRoller);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        // Based on the button pressed, run the roller and conveyor motors
        if (Operator.bButton()) {
            intakeRoller.setMotor(-0.50);
            //intakeConveyor.setMotor(-0.50);
        } else if (Operator.yButton()) {
            intakeRoller.setMotor(0.50);
            // intakeConveyor.setMotor(0.50);
        } else {
            intakeRoller.setMotor(0);
            //intakeConveyor.setMotor(0);
        }
    }

    public void end() {
        intakeRoller.setMotor(0);
        //  intakeConveyor.setMotor(0);
    }
}
