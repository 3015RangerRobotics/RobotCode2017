package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutonomousGearAndHopperShotRed extends CommandGroup {

    public AutonomousGearAndHopperShotRed() {
    	//Wall to 1st turn:125 
    	//1st turn to peg:
    	//peg to 2nd turn:90
    	addParallel(new GearClawClose());
    	addSequential(new DriveResetEncoders());
    	addSequential(new DriveStraightToDistance(-58.0, -1.0));
    	addSequential(new DriveTurnToAngle(115, -0.6));
    	addSequential(new DriveStraightForTime(0.35, -0.6));
    	addSequential(new GearOpenTiltDown(), 0.5);
    	addSequential(new DriveStraightForTime(0.2, 1));
    	addSequential(new DriveTurnToAngle(95, 0.6));
    	addSequential(new DriveResetEncoders());
    	addParallel(new ShooterPrimeWheelSpeed());
    	addSequential(new DriveStraightToDistance(42, 1.0));
    	addSequential(new DriveTurnToAngle(180, 0));
    	addSequential(new DriveResetEncoders());
    	addSequential(new DriveStrafeForTime(1.5, -1.0));
    	addSequential(new ShooterFireNow());
//    	addSequential(new DriveStraightToDistance(90.0 - overshoot, 1.0));
//    	addSequential(new DriveTurnToAngle(180, 0));
//    	addSequential(new DriveStrafeForTime(2.75, -1.0));
    }
}
