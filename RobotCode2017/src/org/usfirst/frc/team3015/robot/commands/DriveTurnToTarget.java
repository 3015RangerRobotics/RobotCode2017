package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.robot.Robot;



/**
 *
 */
public class DriveTurnToTarget extends CommandBase {
	private double speedOffset = 0.01;
	private int stuckCounter = 0;
	private double lastAngle = 0;

    public DriveTurnToTarget() {
    	requires(drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.xAngle >= 10){
    		drive.arcadeDrive(-0.5, 0, false);
    	}else if(Robot.xAngle >= 1){
    		drive.arcadeDrive(-0.5, 0, false);
    	}else if(Robot.xAngle >= 0.25){
    		drive.arcadeDrive(-0.4, 0, false);
    	}else if(Robot.xAngle <= -10){
    		drive.arcadeDrive(0.5, 0, false);
    	}else if(Robot.xAngle <= -1){
    		drive.arcadeDrive(0.5, 0, false);
    	}else if(Robot.xAngle <= -0.25){
    		drive.arcadeDrive(0.4, 0, false);
    	}
    	else{
    		drive.arcadeDrive(0, 0, false);
    	}
    	
//    	if(Math.abs(lastAngle - Robot.xAngle) < )
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
