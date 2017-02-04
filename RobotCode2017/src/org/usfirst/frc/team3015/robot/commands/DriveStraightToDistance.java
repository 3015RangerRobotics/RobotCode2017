package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightToDistance extends CommandBase {
	private double distance;
	private double speed;
	private boolean isReverse;
	private double startAngle;
	
    public DriveStraightToDistance(double distance, double speed) {
        this.distance = distance;
        this.speed = speed;
        requires(drive);
    
        if (speed < 0) isReverse = true;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startAngle = drive.getAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double turnValue;
    	double currentAngle = drive.getAngle();
    	if (currentAngle > startAngle + 10){
    		turnValue = 0.5;
    		
    	}
    	else if (currentAngle > startAngle + 0.5){
    		turnValue = 0.3;
    	}
    	else if (currentAngle < startAngle - 0.5){
    		turnValue = -0.3;
    	}
    	else if (currentAngle < startAngle - 10){
    		turnValue = -0.5;
    	}
    	else{
    		turnValue = 0;
    	}
    	
    	
    	drive.arcadeDrive(speed, turnValue, false);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (isReverse){
        	if(drive.getLeftEncoder() < distance || drive.getRightEncoder() < distance){
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
