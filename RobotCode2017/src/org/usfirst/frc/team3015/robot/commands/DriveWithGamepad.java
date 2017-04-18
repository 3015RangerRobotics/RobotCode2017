package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.ControllerPower;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveWithGamepad extends CommandBase {

    public DriveWithGamepad() {
        requires(drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	drive.setFrontOmnisRetracted();
    	drive.setHWheelAndBackRetracted();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double turnSpeed;
    	if(drive.targetLock){
    		double angle = Vision.xAngleToTarget;
        	if(angle >= 7){
        		turnSpeed = (-0.6 * 12.5)/ControllerPower.getInputVoltage();
        	}else if(angle <= -7){
        		turnSpeed = (0.6 * 12.5)/ControllerPower.getInputVoltage();
        	}else if(angle >= 2){
        		turnSpeed = (-0.35 * 12.5)/ControllerPower.getInputVoltage();
        	}else if(angle <= -2){
        		turnSpeed = (0.35 * 12.5)/ControllerPower.getInputVoltage();
        	}else if(angle < 0){
        		turnSpeed = (0.35 * 12.5)/ControllerPower.getInputVoltage();
        	}else if(angle > 0){
        		turnSpeed = (-0.35 * 12.5)/ControllerPower.getInputVoltage();
        	}else{
        		turnSpeed = 0;
        	}
    	}else{
    		turnSpeed = oi.getDriverLeftX();
    	}
    	drive.arcadeDrive(-oi.getDriverLeftY(), turnSpeed, true);
    	System.out.println(drive.getAngle());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	drive.arcadeDrive(0, 0, false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
