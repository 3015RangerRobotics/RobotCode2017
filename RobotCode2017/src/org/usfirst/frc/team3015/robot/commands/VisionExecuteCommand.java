package org.usfirst.frc.team3015.robot.commands;

public class VisionExecuteCommand extends CommandBase {
	private String text;

    public VisionExecuteCommand(String command) {
    	requires(vision);
    	this.text = command;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	vision.executeCommand(text);
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
    	end();
    }
}
