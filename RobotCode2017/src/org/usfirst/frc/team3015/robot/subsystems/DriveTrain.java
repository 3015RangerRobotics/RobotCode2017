package org.usfirst.frc.team3015.robot.subsystems;

import org.usfirst.frc.team3015.robot.commands.DriveWithGamepad;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.I2C;
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
		imu = new AHRS(I2C.Port.kOnboard);
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
    	return imu.getYaw();
    }
    
    public void setHWheelSolenoid(DoubleSolenoid.Value value){
    	hWheelSolenoid.set(value);
    }
    
    public DoubleSolenoid.Value getHWheelSolenoid(){
    	return hWheelSolenoid.get();
    }
  
    /**
     * Single stick driving
     * @param moveValue		
     * @param rotateValue		
     * @param squaredInputs	
     */
    public void arcadeDrive(double moveValue, double rotateValue, boolean squaredInputs) {
        // local variables to hold the computed PWM values for the motors
        double leftMotorSpeed;
        double rightMotorSpeed;

        rotateValue = limit(rotateValue);
        moveValue = limit(moveValue);

        if (squaredInputs) {
          // square the inputs (while preserving the sign) to increase fine control
          // while permitting full power
        	if (rotateValue >= 0.0) {
        		rotateValue = rotateValue * rotateValue;
        	} else {
	            rotateValue = -(rotateValue * rotateValue);
	        }
	        if (moveValue >= 0.0) {
	        	moveValue = moveValue * moveValue;
	        } else {
	        	moveValue = -(moveValue * moveValue);
	        }
        }

        if (rotateValue > 0.0) {
        	if (moveValue > 0.0) {
        		leftMotorSpeed = rotateValue - moveValue;
        		rightMotorSpeed = Math.max(rotateValue, moveValue);
	        } else {
	        	leftMotorSpeed = Math.max(rotateValue, -moveValue);
	        	rightMotorSpeed = rotateValue + moveValue;
	        }
	    } else {
	    	if (moveValue > 0.0) {
	    		leftMotorSpeed = -Math.max(-rotateValue, moveValue);
	    		rightMotorSpeed = rotateValue + moveValue;
	        } else {
	        	leftMotorSpeed = rotateValue - moveValue;
	        	rightMotorSpeed = -Math.max(-rotateValue, -moveValue);
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