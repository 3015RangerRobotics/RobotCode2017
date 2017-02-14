package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutonomousHopperShotBlue extends CommandGroup {

    public AutonomousHopperShotBlue() {
    	addSequential(new DriveStraightToDistance(-70.0, -1.0));
    	
        addParallel(new DriveStrafeToDistance(-70.0, -1.0));
        addSequential(new ShooterPrimeWheelSpeed());
        
    	//addSequential(new DriveStrafeToDistance (-70.0, -1.0));
    	
    	addSequential(new WaitCommand(1));
    	
    	//addParallel()
        //addParallel(new Command1());
        //addSequential(new Command2());

    	
    	addParallel(new HopperRotate());
    	
    	addSequential(new HopperStop());
    }
}
