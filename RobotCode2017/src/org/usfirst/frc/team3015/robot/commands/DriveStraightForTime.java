package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.ControllerPower;

/**
 * Driving for a set amount of time at a set speed
 */
public class DriveStraightForTime extends CommandBase {
	private double speed;
	private double startAngle = 0;
	/**
	 * Driving for a set amount of time at a set speed
	 * @param time  Length of time to drive
	 * @param speed Speed at which it drives
	 */
    public DriveStraightForTime(double time,double speed){
        this.speed = speed * 12.5;
        this.setTimeout(time);
		requires(drive);
    }

    // Called just before this Command runs the first time
    protected void initialize(){
    	startAngle = drive.getAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute(){
    	double turnValue = 0;
    	double currentAngle = drive.getAngle();
    	if (currentAngle > startAngle + 2){
    		turnValue = 0.3;
    		
    	}
    	else if (currentAngle > startAngle + 0.5){
    		turnValue = 0.2;
    	}
    	else if (currentAngle < startAngle - 0.5){
    		turnValue = -0.2;
    	}
    	else if (currentAngle < startAngle - 2){
    		turnValue = -0.3;
    	}
    	else{
    		turnValue = 0;
    	}
    	drive.arcadeDrive(-speed / ControllerPower.getInputVoltage(), turnValue, true);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished(){
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end(){
    	drive.arcadeDrive(0, 0, true);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted(){
    	end();
    }
}
