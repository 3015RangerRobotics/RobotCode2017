package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutonomousHopperShotRed extends CommandGroup {

    public AutonomousHopperShotRed() {
    	addParallel(new ShooterPrimeWheelSpeed());
    	addSequential(new DriveStraightToDistance(170.0, 1.0));
    	addSequential(new DriveStrafeToDistance(70.0, 1.0));
    	addSequential(new ShooterFireNow());
    }
}
