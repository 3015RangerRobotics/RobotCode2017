package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.robot.subsystems.ShooterWheel;


/**
 *
 */
public class ShooterFireWhenReady extends CommandBase {
	private boolean isPrimed = false;

    public ShooterFireWhenReady() {
    	requires(hopper);
    	requires(shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	shooter.setSpeedMode();
    	shooter.enable();
    	shooter.startShooterWheel();
    	if(shooter.isPrimed()){
    		isPrimed = true;
    	}else{
    		this.setTimeout(3.0);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	shooter.startShooterWheel();
    	if(isPrimed) {
    		hopper.startFeeder();
    		hopper.rotate();
    	}
    	else {
    		if(isTimedOut()){
    			isPrimed = true;
    		}
    		hopper.stopFeeder();
    		hopper.stop();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	hopper.stopFeeder();
    	hopper.stop();
    	shooter.stopShooterWheel();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}