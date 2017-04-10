package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class DriveCheckDriveTrain extends CommandBase {
	Timer timer = new Timer();
	boolean isFinished = false;

    public DriveCheckDriveTrain() {
         requires(drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.reset();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	drive.arcadeDrive(0.3, 0, false);
    	if(timer.get() >= 1){
    		if(drive.getAngle() == 180.0){
        		vision.speakAddMessage("Gyro error...");
        	}
    		if(drive.getLeftEncoder() == 0.0){
    			vision.speakAddMessage("Left drive encoder error...");
    		}
    		if(drive.getRightEncoder() == 0.0){
    			vision.speakAddMessage("Right drive encoder error...");
    		}
    		isFinished = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	drive.arcadeDrive(0, 0, false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
