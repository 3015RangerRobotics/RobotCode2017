package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Starts the compressor
 */
public class OurCompressorOn extends CommandBase {

    public OurCompressorOn() {
        requires(ourCompressor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("pressureSensor", Math.round(ourCompressor.getPressure()));
    	System.out.println("Pressure: " + ourCompressor.getPressure());
    	ourCompressor.startCompressor();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	ourCompressor.stopCompressor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
