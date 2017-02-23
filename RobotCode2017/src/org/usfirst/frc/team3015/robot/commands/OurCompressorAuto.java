package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Runs the compressor
 * Stops if lower than set match time or voltage
 */
public class OurCompressorAuto extends CommandBase {
	private final double CUTOFF_VOLTAGE = 7.0;
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
    	SmartDashboard.putNumber("pressureSensor", ourCompressor.getPressure());
//    	If teleop match time is less than alloted time, stop the compressor
    	if(!DriverStation.getInstance().isAutonomous() && DriverStation.getInstance().getMatchTime() <= STOP_TIME && DriverStation.getInstance().getMatchTime() >= 0) {
    		ourCompressor.stopCompressor();
    	}
//    	If voltage is less than cutoff, stop the compressor
    	else if(DriverStation.getInstance().getBatteryVoltage() <= CUTOFF_VOLTAGE) {
    		ourCompressor.stopCompressor();
    	}else{
    		ourCompressor.startCompressor();
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
    	end();
    }
}
