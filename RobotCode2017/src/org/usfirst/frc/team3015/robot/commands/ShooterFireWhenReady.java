package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.robot.subsystems.ShooterWheel;


/**
 *
 */
public class ShooterFireWhenReady extends CommandBase {

    public ShooterFireWhenReady() {
    	requires(shooterFeeder);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(ShooterWheel.isPrimed()) {
    		shooterFeeder.setBallFeeder(1);
    		
    	}
    	else {
    		shooterFeeder.setBallFeeder(0);
    		
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	shooterFeeder.setBallFeeder(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}