package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.robot.Robot;



/**
 *
 */
public class DriveTurnToTargetWithGyro extends CommandBase {
	private double turnRate = 0;
	private double speedIncrement = 0.01;
	private long lastTime = 0;
	private double lastAngle = 0;
	private double startSpeed = 0.6;
	private double turnSpeed = 0;

    public DriveTurnToTargetWithGyro() {
    	requires(drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	lastTime = System.currentTimeMillis();
    	lastAngle = drive.getAngle();
    	turnSpeed = startSpeed;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	long currentTime = System.currentTimeMillis();
    	double updateRate = 1000/(currentTime-lastTime);
    	double neededTurnAmount = Math.abs(turnRate / updateRate);
    	double turnAmount = Math.abs(lastAngle - drive.getAngle());
    	if(turnAmount <= neededTurnAmount - neededTurnAmount*0.1){
    		turnSpeed += speedIncrement;
    	}else if(turnAmount >= neededTurnAmount + neededTurnAmount*0.1){
    		turnSpeed -= speedIncrement;
    	}
    	
    	if(Robot.xAngle >= 10){
    		turnRate = -15.0;
    	}else if(Robot.xAngle >= 1){
    		turnRate = -10.0;
    	}else if(Robot.xAngle >= 0.25){
    		turnRate = -5.0;
    	}else if(Robot.xAngle <= -10){
    		turnRate = 15.0;
    	}else if(Robot.xAngle <= -1){
    		turnRate = 10.0;
    	}else if(Robot.xAngle <= -0.25){
    		turnRate = 5.0;
    	}
    	else{
    		turnRate = 0;
    	}
    	
    	drive.arcadeDrive(0, (turnRate < 0) ? -turnSpeed : turnSpeed, false);
    	lastAngle = drive.getAngle();
    	lastTime = currentTime;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Math.abs(Robot.xAngle) < 0.5);
    }

    // Called once after isFinished returns true
    protected void end() {
    	drive.arcadeDrive(0, 0, true);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
