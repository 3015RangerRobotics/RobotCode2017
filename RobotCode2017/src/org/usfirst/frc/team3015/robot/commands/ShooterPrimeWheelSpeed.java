package org.usfirst.frc.team3015.robot.commands;



/**
 *
 */
public class ShooterPrimeWheelSpeed extends CommandBase {

    public ShooterPrimeWheelSpeed() {
    	requires(shooterWheel);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	shooterWheel.setSpeedMode();
    	shooterWheel.enable();
    	shooterWheel.setShooterWheel(1);
    	this.setTimeout(2);
    	
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	shooterWheel.setShooterWheel(1);
    	if (isTimedOut()){
    		shooterWheel.setIsPrimed(true);
    	}
    	else {
    		shooterWheel.setIsPrimed(false);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	shooterWheel.setShooterWheel(0);
    	shooterWheel.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
