package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveAntiDefense extends CommandBase {

    public DriveAntiDefense() {
        // Use requires() here to declare subsystem dependencies
         requires(drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	drive.setHWheelAndBackDeployed();
    	drive.setFrontOmnisDeployed();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
//    	drive.setHWheelAndBackRetracted();
    	drive.setFrontOmnisRetracted();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
