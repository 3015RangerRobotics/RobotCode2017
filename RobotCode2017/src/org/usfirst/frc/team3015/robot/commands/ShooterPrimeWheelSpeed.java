package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.robot.subsystems.ShooterWheel;

/**
 *
 */
public class ShooterPrimeWheelSpeed extends CommandBase {

    public ShooterPrimeWheelSpeed() {
    	requires(shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	shooter.disable();
    	shooter.setSpeedMode();
//    	shooter.enable();
    	shooter.startShooterWheelSpeed();
    	this.setTimeout(1.5);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	shooter.startShooterWheelSpeed();
    	System.out.println(shooter.getSpeed());
    	if (isTimedOut()){
    		ShooterWheel.setIsPrimed(true);
    	}
    	else {
    		ShooterWheel.setIsPrimed(false);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	shooter.stopShooterWheel();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
