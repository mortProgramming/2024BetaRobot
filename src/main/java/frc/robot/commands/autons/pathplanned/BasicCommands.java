package frc.robot.commands.autons.pathplanned;

import static frc.robot.config.constants.PhysicalConstants.Lifter.*;

import com.pathplanner.lib.auto.NamedCommands;

import frc.robot.commands.actions.endeffector.Lift;

public class BasicCommands {
    
    public static void setCommands () {
        NamedCommands.registerCommand("LifterUp", new Lift(LIFTER_UP).withTimeout(0.05));

        NamedCommands.registerCommand("LifterDown", new Lift(LIFTER_DOWN).withTimeout(0.05));
    }
}
