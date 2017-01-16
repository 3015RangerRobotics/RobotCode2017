package org.usfirst.frc.team3015.robot.subsystems;

import org.usfirst.frc.team3015.robot.commands.DriveWithGamepad;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *This is the drive train.
 *Sample class for learning
 */
public class DriveTrain extends Subsystem {
	private VictorSP leftMotors;
	private VictorSP rightMotors;
	private CANTalon talon1;
	private CANTalon talon2;
	private AHRS imu;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public DriveTrain() {
		leftMotors = new VictorSP(0);
		rightMotors = new VictorSP(1);
		talon1 = new CANTalon(1);
		talon1.changeControlMode(TalonControlMode.PercentVbus);
		talon1.set(0);
		talon2 = new CANTalon(2);
		talon2.changeControlMode(TalonControlMode.PercentVbus);
		talon2.set(0);
		imu = new AHRS(SerialPort.Port.kUSB);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveWithGamepad());
    }
    
    public boolean isCalibrating(){
    	return imu.isCalibrating();
    }
    
    public boolean isMagneticDisturbance(){
    	return imu.isMagneticDisturbance();
    }
    
    public double getAngle(){
    	return imu.getAngle();
    }
    
    public void arcadeDrive(double moveValue, double rotateValue, boolean squaredInputs) {
        // local variables to hold the computed PWM values for the motors
    	
        double leftMotorSpeed;
        double rightMotorSpeed;

        moveValue = limit(moveValue);
        rotateValue = limit(rotateValue);

        if (squaredInputs) {
          // square the inputs (while preserving the sign) to increase fine control
          // while permitting full power
        	if (moveValue >= 0.0) {
        		moveValue = moveValue * moveValue;
        	} else {
	            moveValue = -(moveValue * moveValue);
	        }
	        if (rotateValue >= 0.0) {
	        	rotateValue = rotateValue * rotateValue;
	        } else {
	        	rotateValue = -(rotateValue * rotateValue);
	        }
        }

        if (moveValue > 0.0) {
        	if (rotateValue > 0.0) {
        		leftMotorSpeed = moveValue - rotateValue;
        		rightMotorSpeed = Math.max(moveValue, rotateValue);
	        } else {
	        	leftMotorSpeed = Math.max(moveValue, -rotateValue);
	        	rightMotorSpeed = moveValue + rotateValue;
	        }
	    } else {
	    	if (rotateValue > 0.0) {
	    		leftMotorSpeed = -Math.max(-moveValue, rotateValue);
	    		rightMotorSpeed = moveValue + rotateValue;
	        } else {
	        	leftMotorSpeed = moveValue - rotateValue;
	        	rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
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
        talon1.set(leftOutput);
        talon2.set(rightOutput);
    }
    
    /**
     * Creates a limit for the inserted number between -1 and 1
     * @param num
     * @return double
     */
    protected static double limit(double num) {
        if (num > 1.0) {
        	return 1.0;
        }
        if (num < -1.0) {
        	return -1.0;
        }
        return num;
    }
}