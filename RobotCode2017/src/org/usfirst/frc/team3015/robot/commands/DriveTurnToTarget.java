package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTurnToTarget extends CommandBase {

    public DriveTurnToTarget() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.xAngle >= 10){
    		drive.arcadeDrive(0.4, 0, true);
    	}else if(Robot.xAngle >= 1){
    		drive.arcadeDrive(0.3, 0, true);
    	}else if(Robot.xAngle <= -10){
    		drive.arcadeDrive(-0.4, 0, true);
    	}else if(Robot.xAngle <= -1){
    		drive.arcadeDrive(-0.3, 0, true);
    	}else{
    		drive.arcadeDrive(0, 0, true);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Math.abs(Robot.xAngle) < 2);
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
