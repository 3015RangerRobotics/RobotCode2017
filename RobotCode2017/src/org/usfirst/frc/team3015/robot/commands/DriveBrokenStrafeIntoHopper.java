package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class DriveBrokenStrafeIntoHopper extends CommandBase {
	private Timer timer = new Timer();
	private boolean isRedAlliance;
	private final double TURN_SPEED = 0.6;
	private final double STRAFE_SPEED = 1;

    public DriveBrokenStrafeIntoHopper(boolean isRedAlliance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(drive);
    	this.isRedAlliance = isRedAlliance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	drive.setHWheelAndBackDeployed();
    	drive.setFrontOmnisRetracted();
    	timer.reset();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(timer.get() >= 0.3){
    		if(isRedAlliance){
    			drive.arcadeDrive(0, -TURN_SPEED, false);
    			drive.hDrive(-STRAFE_SPEED);
    		}else{
    			drive.arcadeDrive(0, TURN_SPEED, false);
    			drive.hDrive(STRAFE_SPEED);
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get() >= 1;
    }

    // Called once after isFinished returns true
    protected void end() {
    	drive.arcadeDrive(0, 0, false);
    	drive.hDrive(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
