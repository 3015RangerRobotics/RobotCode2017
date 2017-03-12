package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.robot.subsystems.ShooterWheel;


/**
 *
 */
public class ShooterStopWheel extends CommandBase {
	

    public ShooterStopWheel() {
    	requires(shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	shooter.stopShooterWheel();
    	shooter.disable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	hopper.startFeeder();
//		hopper.rotate();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
//    	hopper.stopFeeder();
//    	hopper.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}