package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutonomousOneGearLeft extends CommandGroup {

    public AutonomousOneGearLeft() {
    	addSequential(new GearClawClose());
    	addParallel(new GearTiltUp());
        addSequential(new DriveStraightToDistance(-110.0, -0.7));
        addSequential(new DriveTurnToAngle(60, 0));
        addSequential(new DriveStraightToDistance(-12.0, -0.4));
        addSequential(new GearClawOpen());
        addSequential(new WaitCommand(0.5));
        addSequential(new DriveStraightToDistance(12.0, 0.5));
    }
}
