package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Get the gear on the left pls gooby
 */
public class AutonomousOneGearLeft extends CommandGroup {

    public AutonomousOneGearLeft() {
    	addParallel(new GearClawClose());
    	addSequential(new DriveResetEncoders());
    	addSequential(new DriveStraightToDistance(-60,-.5));
    	addSequential(new DriveTurnToAngle(245,0));
    	addSequential(new DriveToSpoinger(.5,4.5));
    	addParallel(new GearOpenTiltDown(),1);
    	addSequential(new WaitCommand(.25));
    	addSequential(new DriveResetEncoders());
    	addSequential(new DriveStraightToDistance(10,.4));
    }
}
