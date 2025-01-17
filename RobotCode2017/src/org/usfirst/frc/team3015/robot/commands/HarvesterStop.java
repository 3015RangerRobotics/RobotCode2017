package org.usfirst.frc.team3015.robot.commands;


/**
 *
 */
public class HarvesterStop extends CommandBase {

    public HarvesterStop() {
        // Use requires() here to declare subsystem dependencies
        requires(harvester);
        this.setTimeout(1.0);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	harvester.stopHarvester();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	harvester.stopTransport();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
