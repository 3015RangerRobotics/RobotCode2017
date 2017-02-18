package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.robot.subsystems.Harvester;

import edu.wpi.first.wpilibj.Timer;

/**
 * Rotates the hopper
 */
public class HopperReverseFeeder extends CommandBase {
//	Timer timer = new Timer();
//	boolean isReversing;
	
    public HopperReverseFeeder() {
        requires(hopper);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	hopper.rotate();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	hopper.reverseFeeder();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	hopper.stopFeeder();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }


}
