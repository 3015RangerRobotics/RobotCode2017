package org.usfirst.frc.team3015.robot.commands;


/**
 * Sends manipulator inside the frame
 */
public class GearTiltUp extends CommandBase {

    public GearTiltUp() {
        requires(gearManipulator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	gearManipulator.tiltUp();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	gearManipulator.tiltStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
