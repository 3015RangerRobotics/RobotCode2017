package org.usfirst.frc.team3015.robot.commands;


/**
 *
 */
public class DriveStrafeForTime extends CommandBase {
	private double speed;
	
    public DriveStrafeForTime(double time, double speed) {
        this.speed = speed; 
        this.setTimeout(time);
        requires(drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	drive.hDrive(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	drive.hDrive(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
