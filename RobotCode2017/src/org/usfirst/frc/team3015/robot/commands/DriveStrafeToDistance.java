package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class DriveStrafeToDistance extends CommandBase {
	private double distance;
	private double speed;
	private boolean isReverse;
	private Timer timer = new Timer();
	
    public DriveStrafeToDistance(double distanceInInches, double speed) {
        this.distance = distanceInInches;
        this.speed = speed;
        requires(drive);
    
        if (speed < 0) isReverse = true;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.reset();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (timer.get()>0.3) {
			drive.hDrive(speed);
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (isReverse){
        	if(drive.getLeftDriveEncoderInches() < distance || drive.getRightDriveEncoderInches() < distance){
        		return true;
        	}
        }else{
        	if(drive.getLeftEncoder() > distance || drive.getRightEncoder() > distance){
        		return true;
        	}
        }
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
