package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCrossBaseLine extends CommandGroup {

    public AutonomousCrossBaseLine() {
        addSequential(new DriveStraightToDistance(96, 0.6));
    }
}
