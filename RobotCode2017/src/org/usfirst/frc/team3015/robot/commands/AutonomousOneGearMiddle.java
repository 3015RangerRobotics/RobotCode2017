package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutonomousOneGearMiddle extends CommandGroup {

    public AutonomousOneGearMiddle() {
    	addSequential(new GearClawClose());
    	addParallel(new GearTiltUp());
        addSequential(new DriveStraightToDistance(-84.0, -0.7));
        addSequential(new DriveStraightToDistance(-9.0, -0.4));
        addSequential(new GearClawOpen());
        addSequential(new WaitCommand(0.5));
        addSequential(new DriveStraightToDistance(9.0, 0.5));
    }
}
