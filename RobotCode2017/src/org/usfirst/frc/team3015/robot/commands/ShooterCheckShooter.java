package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class ShooterCheckShooter extends CommandBase {
	Timer timer = new Timer();
	boolean isFinished = false;

    public ShooterCheckShooter() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.reset();
    	timer.start();
    	shooter.setSpeedMode();
    	shooter.startShooterWheelSpeed();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	shooter.startShooterWheelSpeed();
    	if(timer.get() >= 1){
    		if(shooter.getSpeed() < 10000){
    			vision.speakAddMessage("Shooter error...");
    		}
    		isFinished = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
