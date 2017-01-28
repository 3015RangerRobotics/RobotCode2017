package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;

/**
 * 
 */
public class OurCompressorAuto extends CommandBase {
	private final double CUTOFF_VOLTAGE = 7.5;
	private final int STOP_TIME = 20;
	
    public OurCompressorAuto() {
        requires(ourCompressor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	ourCompressor.startCompressor();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	If teleop match time is less than alloted time, stop the compressor
    	if(!DriverStation.getInstance().isAutonomous() && DriverStation.getInstance().getMatchTime() <= STOP_TIME && DriverStation.getInstance().getMatchTime() >= 0) {
    		end();
    	}
    	
//    	If voltage is less than cutoff, stop the compressor
    	if(DriverStation.getInstance().getBatteryVoltage() <= CUTOFF_VOLTAGE) {
    		end();
    	}
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
    }
}
