package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutonomousTwoGearBlue extends CommandGroup {

    public AutonomousTwoGearBlue() {
    	addSequential(new GearClawClose());
    	addParallel(new GearTiltUp());
        addSequential(new DriveStraightToDistance(-110.0, -1));
        addSequential(new DriveTurnToAngle(-60));
        addSequential(new DriveStraightToDistance(-12.0, -0.6));
        addSequential(new GearClawOpen());
        addSequential(new WaitCommand(0.25));
        addSequential(new DriveStraightToDistance(12.0, 0.6));
        addSequential(new DriveTurnToAngle(60));
        addSequential(new DriveStraightToDistance(-520.0, -1));
        addSequential(new DriveTurnToAngle(45));
        addSequential(new DriveStraightToDistance(-20.0, -0.8));
        addSequential(new WaitCommand(2));
        addSequential(new GearClawClose());
        addSequential(new DriveStraightToDistance(20.0, 0.8));
        addSequential(new DriveTurnToAngle(-45));
        addSequential(new DriveStraightToDistance(520.0, 1));
        addSequential(new DriveTurnToAngle(-60));
        addSequential(new DriveStraightToDistance(-12.0, -0.6));
        addSequential(new GearClawOpen());
        addSequential(new WaitCommand(0.25));
        addSequential(new DriveStraightToDistance(-12.0, -0.6));
    }
}
