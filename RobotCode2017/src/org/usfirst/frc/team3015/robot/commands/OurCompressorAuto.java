package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.robot.subsystems.ShooterWheel;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Runs the compressor
 * Stops if lower than set match time or voltage
 */
public class OurCompressorAuto extends CommandBase {
	private final double CUTOFF_VOLTAGE = 8.5;
	//WARNINGNISNFOA
	//CHANGE DIS TO TWENTIE BEOFRE DA COMPITETION
	private final int STOP_TIME = -2;
	private boolean fillCompressor = true;
	
    public OurCompressorAuto() {
        requires(ourCompressor);
        this.setRunWhenDisabled(true);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	ourCompressor.startCompressor();
    	fillCompressor = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("pressureSensor", Math.round(ourCompressor.getPressure()));
//    	System.out.println(DriverStation.getInstance().getMatchTime());
//    	If teleop match time is less than alloted time, stop the compressor
    	if(DriverStation.getInstance().isAutonomous() || DriverStation.getInstance().getMatchTime() <= STOP_TIME ||
    			DriverStation.getInstance().getBatteryVoltage() <= CUTOFF_VOLTAGE || ShooterWheel.isWheelOn) {
    		ourCompressor.stopCompressor();
    	}else{
    		if(fillCompressor){
    			ourCompressor.startCompressor();
    			if(ourCompressor.getPressure() >= 118){
    				fillCompressor = false;
    			}
    		}else{
    			ourCompressor.stopCompressor();
    			if(ourCompressor.getPressure() <= 60){
    				fillCompressor = true;
    			}
    		}
    	}
//    	System.out.println("pressure: " + Math.round(ourCompressor.getPressure()));
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
