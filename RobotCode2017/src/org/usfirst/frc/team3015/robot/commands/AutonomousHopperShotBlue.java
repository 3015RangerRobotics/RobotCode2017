package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutonomousHopperShotBlue extends CommandGroup {

    public AutonomousHopperShotBlue() {
    	addSequential(new DriveResetEncoders());
    	addParallel(new ShooterPrimeWheelSpeed());
    	addSequential(new DriveStraightToDistance(-68, -1.0));
    	addSequential(new DriveStrafeForTime(2.5, 1.0));
    	addSequential(new ShooterFireNow());
    }
}
