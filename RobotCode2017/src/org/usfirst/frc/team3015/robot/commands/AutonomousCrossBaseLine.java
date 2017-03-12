package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * just does the baseline
 * this is jen's fault.
 */
public class AutonomousCrossBaseLine extends CommandGroup {

    public AutonomousCrossBaseLine() {
    	addSequential(new DriveResetEncoders());
        addSequential(new DriveStraightToDistance(-96, -0.6));
    }
}
