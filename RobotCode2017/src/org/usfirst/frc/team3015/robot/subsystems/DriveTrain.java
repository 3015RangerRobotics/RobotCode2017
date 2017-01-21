package org.usfirst.frc.team3015.robot.subsystems;

import org.usfirst.frc.team3015.robot.commands.DriveWithGamepad;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Code for our skid-steer drop-H drive train
 */
public class DriveTrain extends Subsystem {
	private VictorSP leftMotors;
	private VictorSP rightMotors;
	private VictorSP hMotors;
	private DoubleSolenoid hWheelSolenoid;
	private AHRS imu;
	
	/**
	 * Constructs the drive train
	 */
	public DriveTrain() {
		leftMotors = new VictorSP(0);
		rightMotors = new VictorSP(1);
		hMotors = new VictorSP(2);
		hWheelSolenoid = new DoubleSolenoid(0, 1);
		imu = new AHRS(SerialPort.Port.kUSB);
	}
	
	/**
	 * Sets default command to DriveWithGamepad 
	 */
    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithGamepad());
    }
    
    /**
     * Checks to see if the NavX is calibrating
     * @return If the NavX is calibrating
     */
    public boolean isCalibrating(){
    	return imu.isCalibrating();
    }
    
    /**
     * Checks to see if NavX is getting magnetic interference
     * @return If the NavX is getting magnetic interference
     */
    public boolean isMagneticDisturbance(){
    	return imu.isMagneticDisturbance();
    }
    
    /**
     * Gets the angle from the NavX
     * @return NavX angle
     */
    public double getAngle(){
    	return imu.getAngle();
    }
    
    public void setHWheelSolenoid(DoubleSolenoid.Value value){
    	hWheelSolenoid.set(value);
    }
    
    public DoubleSolenoid.Value getHWheelSolenoid(){
    	return hWheelSolenoid.get();
    }
    
    /**
     * Single stick driving
     * @param turnValue		
     * @param moveValue		
     * @param squaredInputs	
     */
    public void arcadeDrive(double turnValue, double moveValue, boolean squaredInputs) {
        // local variables to hold the computed PWM values for the motors
        double leftMotorSpeed;
        double rightMotorSpeed;

        turnValue = limit(turnValue);
        moveValue = limit(moveValue);

        if (squaredInputs) {
          // square the inputs (while preserving the sign) to increase fine control
          // while permitting full power
        	if (turnValue >= 0.0) {
        		turnValue = turnValue * turnValue;
        	} else {
	            turnValue = -(turnValue * turnValue);
	        }
	        if (moveValue >= 0.0) {
	        	moveValue = moveValue * moveValue;
	        } else {
	        	moveValue = -(moveValue * moveValue);
	        }
        }

        if (turnValue > 0.0) {
        	if (moveValue > 0.0) {
        		leftMotorSpeed = turnValue - moveValue;
        		rightMotorSpeed = Math.max(turnValue, moveValue);
	        } else {
	        	leftMotorSpeed = Math.max(turnValue, -moveValue);
	        	rightMotorSpeed = turnValue + moveValue;
	        }
	    } else {
	    	if (moveValue > 0.0) {
	    		leftMotorSpeed = -Math.max(-turnValue, moveValue);
	    		rightMotorSpeed = turnValue + moveValue;
	        } else {
	        	leftMotorSpeed = turnValue - moveValue;
	        	rightMotorSpeed = -Math.max(-turnValue, -moveValue);
	        }
	    }	
	    setLeftRightMotorOutputs(leftMotorSpeed, rightMotorSpeed);
    }
    
    public void setLeftRightMotorOutputs(double leftOutput, double rightOutput) {
        if (leftMotors == null || rightMotors == null) {
        	throw new NullPointerException("Null motor provided");
        }

        if (leftMotors != null) {
        	leftMotors.set(limit(leftOutput));
        }
        

        if (rightMotors != null) {
        	rightMotors.set(-limit(rightOutput));
        }
    }
    
    protected static double limit(double num) {
        if (num > 1.0) {
        	return 1.0;
        }
        if (num < -1.0) {
        	return -1.0;
        }
        return num;
    }
    
    public void hDrive(double speed){
    	hMotors.set(speed);
    }
}