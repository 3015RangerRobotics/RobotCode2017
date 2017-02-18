package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutonomousGearAndHopperShotBlue extends CommandGroup {

    public AutonomousGearAndHopperShotBlue() {
    	addSequential(new GearClawClose());
    	addParallel(new GearTiltUp());
        addSequential(new DriveStraightToDistance(-110.0, -1));
        addSequential(new DriveTurnToAngle(60));
        addSequential(new DriveStraightToDistance(-12.0, -1));
        addSequential(new GearClawOpen());
        addParallel(new ShooterPrimeWheelSpeed());
        addSequential(new WaitCommand(0.25));
        addSequential(new DriveStraightToDistance(20.0, 1));
        addSequential(new DriveTurnToAngle(-120));
        addSequential(new DriveStraightToDistance(-160.0, -1));
        addSequential(new DriveBrokenStrafeIntoHopper(false));
        addSequential(new ShooterFireNow());
    }
}
