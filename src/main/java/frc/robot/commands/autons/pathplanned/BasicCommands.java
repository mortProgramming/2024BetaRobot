package frc.robot.commands.autons.pathplanned;

import static frc.robot.config.constants.PhysicalConstants.Lifter.*;
import static frc.robot.config.constants.PhysicalConstants.Shooter.FAST_SHOOT;
import static frc.robot.config.constants.PhysicalConstants.Shooter.SLOW_SHOOT;
import static frc.robot.config.constants.PhysicalConstants.Convey.CONVEY_SPEED;
import static frc.robot.config.constants.PhysicalConstants.Intake.*;

import com.pathplanner.lib.auto.NamedCommands;

import frc.robot.commands.actions.endeffector.Lift;
import frc.robot.commands.actions.endeffector.MoveNote;
import frc.robot.commands.actions.endeffector.Shoot;

public class BasicCommands {
    
    public static void setCommands () {
        NamedCommands.registerCommand("LifterUp", new Lift(LIFTER_UP).withTimeout(0.02));
        NamedCommands.registerCommand("LifterDown", new Lift(LIFTER_DOWN).withTimeout(0.02));

        NamedCommands.registerCommand("MoveNoteIn", new MoveNote(INTAKE_SPEED, CONVEY_SPEED));
        NamedCommands.registerCommand("MoveNoteStop", new MoveNote(0, 0));

        NamedCommands.registerCommand("ShooterStart", new Shoot(SLOW_SHOOT));
        NamedCommands.registerCommand("ShooterFast", new Shoot(FAST_SHOOT));
        NamedCommands.registerCommand("ShooterStop", new Shoot(0));
    }
}
