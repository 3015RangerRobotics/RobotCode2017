package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutonomousHopperShotRed extends CommandGroup {

    public AutonomousHopperShotRed() {
    	addParallel(new GearClawClose());
//    	addSequential(new DriveResetEncoders());
    	addParallel(new HopperExtend());
    	addParallel(new ShooterPrimeWheelSpeed());
    	addSequential(new DriveStraightWithPID(69));
//    	addSequential(new DriveStraightToDistance(-65.25, -1.0));
//    	addSequential(new DriveStraightForTime(0.3, 0.25));
    	addSequential(new DriveStrafeForTime(2.5, -1.0));
    	addParallel(new HopperAgitate());
    	addSequential(new ShooterFireNow());
    }
}
