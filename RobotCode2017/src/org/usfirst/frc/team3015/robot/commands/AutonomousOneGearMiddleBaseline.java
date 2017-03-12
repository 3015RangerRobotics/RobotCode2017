package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutonomousOneGearMiddleBaseline extends CommandGroup {

    public AutonomousOneGearMiddleBaseline() {
    	addParallel(new GearClawClose());
    	addSequential(new DriveResetEncoders());
    	addSequential(new DriveStraightToDistance(-60,-.5));
    	addSequential(new DriveTurnToAngle(115,0));
    	addSequential(new DriveToSpoinger(.5,4.5));
    	addParallel(new GearOpenTiltDown(),1);
    	addSequential(new WaitCommand(.25));
    	addSequential(new DriveResetEncoders());
    	addSequential(new DriveStraightToDistance(15,.4));
    	addSequential(new DriveTurnToAngle(3,.5));
    	addSequential(new DriveResetEncoders());
    	addSequential(new DriveStraightToDistance(10,.5));
    }

}
