package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.robot.subsystems.ShooterWheel;


/**
 *
 */
public class ShooterFireWhenReady extends CommandBase {
	private boolean isPrimed = false;

    public ShooterFireWhenReady() {
    	requires(singulator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(ShooterWheel.isPrimed) {
    		singulator.startFeeder();
    		singulator.rotate();
    	}else{
    		singulator.stopFeeder();
    		singulator.stop();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	singulator.stopFeeder();
    	singulator.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}