package frc.robot.commands.autons.pathplanned;

import static frc.robot.config.constants.PhysicalConstants.Lifter.*;
import static frc.robot.config.constants.PhysicalConstants.Convey.CONVEY_SPEED;
import static frc.robot.config.constants.PhysicalConstants.Intake.*;

import com.pathplanner.lib.auto.NamedCommands;

import frc.robot.commands.actions.endeffector.Lift;
import frc.robot.commands.actions.endeffector.MoveNote;

public class BasicCommands {
    
    public static void setCommands () {
        NamedCommands.registerCommand("LifterUp", new Lift(LIFTER_UP).withTimeout(0.05));

        NamedCommands.registerCommand("LifterDown", new Lift(LIFTER_DOWN).withTimeout(0.05));

        NamedCommands.registerCommand("MoveNoteIn", new MoveNote(1, INTAKE_SPEED, CONVEY_SPEED));

        NamedCommands.registerCommand("MoveNoteStop", new MoveNote(0, 0));
    }
}

