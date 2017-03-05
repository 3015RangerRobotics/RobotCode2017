package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutonomousHopperShotRed extends CommandGroup {

    public AutonomousHopperShotRed() {
    	double overshoot = 30;
    	addSequential(new DriveResetEncoders());
    	addParallel(new ShooterPrimeWheelSpeed());
    	addSequential(new DriveStraightToDistance(-91.0 + overshoot, -1.0));
    	addSequential(new DriveStrafeForTime(2.75, -1.0));
    	addSequential(new ShooterFireNow());
    }
}
