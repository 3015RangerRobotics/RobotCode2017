package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class DriveStrafeForTime extends CommandBase {
	private double speed;
	private Timer timer = new Timer();
	private double startAngle;
	
    public DriveStrafeForTime(double time, double speed) {
        this.speed = speed; 
        this.setTimeout(time);
        requires(drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	drive.zeroAngle();
//    	startAngle = drive.getAngle();
    	drive.setHWheelAndBackDeployed();
    	drive.setFrontOmnisDeployed();
    	timer.reset();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	double currentAngle = drive.getAngle();
    	if (timer.get()>0.3) {
			drive.hDrive(speed);
		}
//    	if(timer.get() > 0.6){
//    		if(currentAngle > startAngle + 1){
//        		drive.arcadeDrive(0, 0.5, false);
//        	}else if(currentAngle < startAngle - 1){
//        		drive.arcadeDrive(0, -0.5, false);
//        	}else{
//        		drive.arcadeDrive(0, 0, false);
//        	}
//    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	drive.setHWheelAndBackRetracted();
    	drive.setFrontOmnisRetracted();
    	drive.hDrive(0);
    	drive.arcadeDrive(0, 0, false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
