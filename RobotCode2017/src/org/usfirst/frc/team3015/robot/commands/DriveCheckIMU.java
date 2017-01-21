package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveCheckIMU extends CommandBase {

    public DriveCheckIMU() {
        // Use requires() here to declare subsystem dependencies
         requires(drive);
         requires(vision);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(drive.getAngle() == 0){
    		vision.speak("Gyro not found in USB port 2.");
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
