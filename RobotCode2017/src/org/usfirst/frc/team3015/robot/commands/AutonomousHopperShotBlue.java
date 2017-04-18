package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutonomousHopperShotBlue extends CommandGroup {

    public AutonomousHopperShotBlue() {
    	addParallel(new GearClawClose());
    	addSequential(new DriveResetEncoders());
    	addParallel(new HopperExtend());
    	addParallel(new ShooterPrimeWheelSpeed());
    	addSequential(new DriveStraightToDistance(-65.5, -1.0));
    	addSequential(new DriveStraightForTime(0.3, 0.25));
    	addSequential(new DriveStrafeForTime(2.5, 1.0));
    	addParallel(new HopperAgitate());
    	addSequential(new ShooterFireNow());
    }
}
