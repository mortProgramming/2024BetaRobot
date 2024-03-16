package frc.robot.commands.Auton;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.IntakeConveyor;

//change "ConveyorMotor" to "conveyorMotorSpeed"

public class TimedConveyor extends Command {
    private IntakeConveyor intakeConveyor;

    private double conveyorMotorSpeed;
    private double time;
    private Timer timer;

    public TimedConveyor(double time, double conveyorMotorSpeed) {
        intakeConveyor = IntakeConveyor.getInstance();
        timer = new Timer();
        this.time = time;
        this.conveyorMotorSpeed = conveyorMotorSpeed;
        addRequirements(intakeConveyor);

    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        intakeConveyor.setMotor(conveyorMotorSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        System.out.print("broken conveyor");
        intakeConveyor.setMotor(0);
    }

    @Override
    public boolean isFinished() {
        return timer.get() > time;
    }

}