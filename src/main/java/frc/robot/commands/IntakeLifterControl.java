package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeLifter;
import frc.robot.util.Operator;
//pids import
import edu.wpi.first.math.controller.PIDController;
import static frc.robot.util.Constants.DrivetrainMotors.LIFTER_UP;
import static frc.robot.util.Constants.DrivetrainMotors.LIFTER_DOWN;



public class IntakeLifterControl extends Command {
    private static IntakeLifter intakeLifter;
    private static boolean oldButton;
    private static boolean newButton;
    private static double targetAngle;

    public IntakeLifterControl() {
        intakeLifter = IntakeLifter.getInstance();

        addRequirements(intakeLifter);
    }

    @Override
    public void initialize() {

oldButton = false;
newButton = false;
targetAngle =LIFTER_UP;

    }

    

    //pids for lifter




    @Override
    public void execute() {
        oldButton = newButton;
        newButton = Operator.aButton();
        if(oldButton == false && newButton == true && targetAngle == LIFTER_DOWN){
        targetAngle = LIFTER_UP;
        }
        else if(oldButton == false && newButton == true && targetAngle == LIFTER_UP){
        targetAngle = LIFTER_DOWN;
        }
        intakeLifter.setMotor(intakeLifter.getLifterPid().calculate(intakeLifter.getMotor().getEncoder().getPosition(), targetAngle));
        // intakeLifter.setMotor(0.35 * -Operator.leftJoyStick());



//commented out for other stuff kanav s good at being a coder
        // intakeLifter.setMotor(intakeLifter.getLifterPid().calculate(intakeLifter.getMotor().getEncoder().getVelociy(),LIFTER_UP));
        // intakeLifter.setMotor(intakeLifter.getLifterPid().calculate(intakeLifter.getMotor().getEncoder().getVelociy(),LIFTER_DOWN));

            if(Operator.startButton() == true){
             intakeLifter.setMotor(0.35);
            }
    
            if(Operator.backButton() == true){
             intakeLifter.setMotor(-0.35);
            }
        }
}