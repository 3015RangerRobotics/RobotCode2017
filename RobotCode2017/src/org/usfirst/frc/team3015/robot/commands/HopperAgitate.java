package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class HopperAgitate extends CommandBase {
	Timer timer = new Timer();
	
    public HopperAgitate() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(hopper);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.reset();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(timer.get() < 5){
    		hopper.extend();
    	}else if(timer.get() < 5.25){
    		hopper.retract();
    	}else{
    		timer.reset();
    		timer.start();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
