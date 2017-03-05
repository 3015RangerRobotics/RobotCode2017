package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousHopperShotBlue extends CommandGroup {

    public AutonomousHopperShotBlue() {
    	addSequential(new DriveResetEncoders());
    	addParallel(new ShooterPrimeWheelSpeed());
    	addSequential(new DriveStraightToDistance(-630.0, -1.0));
    	addSequential(new DriveStrafeForTime(2.75, 1.0));
    	addSequential(new ShooterFireNow());
    }
}
