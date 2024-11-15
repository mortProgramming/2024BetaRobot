package frc.robot.commands.autons.pathplanned;

import frc.robot.commands.autons.pathplanned.paths.Rest;

import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.wpilibj2.command.Command;

public class GetPlanned {

    public static Command square() {
        Rest.setCommands();

        return new PathPlannerAuto("Square");
    }

    public static Command oval() {
        Rest.setCommands();

        return new PathPlannerAuto("Oval");
    }

    public static Command t() {
        Rest.setCommands();

        return new PathPlannerAuto("T");
    }
}
