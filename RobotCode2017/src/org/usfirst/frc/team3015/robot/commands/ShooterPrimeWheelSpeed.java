package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterPrimeWheelSpeed extends CommandBase {

    public ShooterPrimeWheelSpeed() {
    	requires(shooter);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	shooter.setSpeedMode();
    	shooter.enable();
    	shooter.setBallFeeder(1);
    	this.setTimeout(2);
    	
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	shooter.setShooterWheel(1);
    	if (isTimedOut()){
    		shooter.setIsPrimed(true);
    	}
    	else {
    		shooter.setIsPrimed(false);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	shooter.setShooterWheel(0);
    	shooter.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
