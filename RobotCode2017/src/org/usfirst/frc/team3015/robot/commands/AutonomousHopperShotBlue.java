package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousHopperShotBlue extends CommandGroup {

    public AutonomousHopperShotBlue() {
    	
    	addParallel(new ShooterPrimeWheelSpeed());
    	addSequential(new DriveStraightToDistance(170.0, 1.0));
//    	addSequential(new DriveBrokenStrafeIntoHopper(false));
        addSequential(new DriveStrafeToDistance(-70.0, -1.0));
        addSequential(new ShooterFireNow());
    }
}
