package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeLifter;
import frc.robot.util.Operator;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static frc.robot.util.Constants.Intake.LIFTER_UP;
import static frc.robot.util.Constants.Intake.LIFTER_DOWN;

public class IntakeLifterControl extends Command {
    private static IntakeLifter intakeLifter;

    private boolean oldButton;
    private boolean newButton;
    private double targetAngle;

    public IntakeLifterControl() {
        intakeLifter = IntakeLifter.getInstance();

        addRequirements(intakeLifter);
    }

    @Override
    public void initialize() {

        oldButton = false;
        newButton = false;
        targetAngle = LIFTER_UP;

    }

    // pids for lifter

    @Override
    public void execute() {
        oldButton = newButton;
        newButton = Operator.aButton();
        if (oldButton == false && newButton == true && targetAngle == LIFTER_DOWN) {
            targetAngle = LIFTER_UP;
        } else if (oldButton == false && newButton == true && targetAngle == LIFTER_UP) {
            targetAngle = LIFTER_DOWN;
        }

        if (targetAngle == LIFTER_DOWN && intakeLifter.getLifterMotor().getEncoder().getPosition() < targetAngle) {
            intakeLifter.setMotor(0.5);

        } else if (targetAngle == LIFTER_UP && intakeLifter.getLifterMotor().getEncoder().getPosition() > targetAngle) {
            intakeLifter.setMotor(-0.5);

        } else {
            intakeLifter.setMotor(0);
        }

        // intakeLifter.setMotor(0.3 *
        // intakeLifter.getLifterPid().calculate(intakeLifter.getMotor().getEncoder().getPosition(),
        // targetAngle));
        // intakeLifter.setMotor(0.35 * -Operator.leftJoyStick());
        // SmartDashboard.putNumber("liftercalculation",
        // intakeLifter.getLifterPid().calculate(intakeLifter.getMotor().getEncoder().getPosition()));
        SmartDashboard.putNumber("lifterpid", intakeLifter.getLifterMotor().getEncoder().getPosition());

        // commented out for other stuff kanav s good at being a coder
        // intakeLifter.setMotor(intakeLifter.getLifterPid().calculate(intakeLifter.getMotor().getEncoder().getVelociy(),LIFTER_UP));
        // intakeLifter.setMotor(intakeLifter.getLifterPid().calculate(intakeLifter.getMotor().getEncoder().getVelociy(),LIFTER_DOWN));

        if (Operator.startButton() == true) {
            intakeLifter.setMotor(0.35);
        }

        if (Operator.backButton() == true) {
            intakeLifter.setMotor(-0.35);
        }
    }
}