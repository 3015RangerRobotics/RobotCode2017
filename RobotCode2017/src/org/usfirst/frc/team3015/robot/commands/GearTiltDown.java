package org.usfirst.frc.team3015.robot.commands;


/**
 * Tilt down gear harvester outside frame
 */
public class GearTiltDown extends CommandBase {

    public GearTiltDown() {
        requires(gearManipulator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	gearManipulator.tiltDown();
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
