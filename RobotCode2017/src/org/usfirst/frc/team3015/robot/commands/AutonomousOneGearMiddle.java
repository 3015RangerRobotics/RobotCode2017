package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Delivers a gear
 */
public class AutonomousOneGearMiddle extends CommandGroup {

    public AutonomousOneGearMiddle() {
    	addSequential(new DriveResetEncoders());
    	addParallel(new GearClawClose());
    	addSequential(new DriveStraightToDistance(-50,-.5));
    	addSequential(new DriveToSpoinger(-.4,4.5));
    	addParallel(new GearOpenTiltDown(),1);
    	addSequential(new WaitCommand(.25));
    	addSequential(new DriveResetEncoders());
    	addSequential(new DriveStraightToDistance(10,.4));
    }
}
