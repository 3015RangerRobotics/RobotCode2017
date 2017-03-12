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
//    	drive.zeroAngle();
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
//    	SmartDashboard.putNumber("gyro", drive.getAngle());
//    	System.out.println(drive.getAngle());
//    	System.out.println(drive.getLeftEncoder() + ", " + drive.getRightEncoder());
//    	if(!drive.isCalibrating()){
//    		System.out.println("IMU Angle: " + drive.getAngle());
//    		System.out.println("Is calibrating: " + drive.isCalibrating());
//    		System.out.println("Magnetic Disturbance: " + drive.isMagneticDisturbance());
//    		System.out.println("IMU Magnetic Disturbance: " + drive.isMagneticDisturbance());
//    	}
//    	System.out.println(drive.getLeftEncoder() + ", " + drive.getRightEncoder());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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
