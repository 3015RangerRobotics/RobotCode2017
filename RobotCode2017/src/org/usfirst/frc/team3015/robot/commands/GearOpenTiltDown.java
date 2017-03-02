package org.usfirst.frc.team3015.robot.commands;


/**
 * Tilt down gear harvester outside frame
 */
public class GearOpenTiltDown extends CommandBase {

    public GearOpenTiltDown() {
        requires(gearManipulator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	gearManipulator.openClaw();
    	gearManipulator.openClaw2();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	gearManipulator.tiltDown();
    	
//    	gearManipulator.openClaw();
//    	gearManipulator.openClaw2();
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
