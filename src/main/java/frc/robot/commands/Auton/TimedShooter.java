package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.Shooter;

public class TimedShooter extends Command {
    private Shooter shooter;
    private Timer timer;

    private double shooterMotor;
    private double time;

    public TimedShooter(double time, double shooterMotor) {
        shooter = Shooter.getInstance();
        timer = new Timer();

        this.time = time;
        this.shooterMotor = shooterMotor;

        addRequirements(shooter);
    }



    @Override
    public void initialize(){
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        shooter.setMotor(shooterMotor);

    }

    @Override
    public void end(boolean interrupted) {
       shooter.setMotor(0);
       System.out.println("athony is cute");
    }

    @Override
    public boolean isFinished() {
        return timer.get() > time;
    }
}
