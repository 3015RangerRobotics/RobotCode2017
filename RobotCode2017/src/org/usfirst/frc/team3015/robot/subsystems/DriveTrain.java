package org.usfirst.frc.team3015.robot.subsystems;

import org.usfirst.frc.team3015.robot.commands.DriveWithGamepad;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Code for our skid-steer drop-H drive train
 */
public class DriveTrain extends Subsystem {
	private VictorSP leftMotors;
	private VictorSP rightMotors;
	private VictorSP hMotors;
	private Solenoid hWheelSolenoid;
	private Solenoid frontOmnis;
	private Solenoid backOmnis;
	private AHRS imu;
	private double turnToAngleTurnSpeed = 0;
	private double turnToAngleIncrement = 0.001;
	private double lastAngle = 0;
	private long lastTime = 0;
	
	/**
	 * Constructs the drive train
	 */
	public DriveTrain() {
		leftMotors = new VictorSP(0);
		rightMotors = new VictorSP(1);
		hMotors = new VictorSP(2);
		hWheelSolenoid = new Solenoid(0);
		frontOmnis = new Solenoid(1);
		backOmnis = new Solenoid(2);
		imu = new AHRS(I2C.Port.kOnboard);
	}
	
	/**
	 * Sets default command to DriveWithGamepad 
	 */
    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithGamepad());
    }
    
    public void turnToAngle(double angle, boolean usesGyro){
    	long currentTime = System.currentTimeMillis();
    	if(currentTime - lastTime <= 0){
    		lastTime = currentTime;
    		return;
    	}
    	double updateRate = 1000/(currentTime-lastTime);
    	double turnRate;
    	if(angle >= 10){
    		turnRate = -15.0;
    	}else if(angle >= 1){
    		turnRate = -10.0;
    	}else if(angle >= 0.25){
    		turnRate = -5.0;
    	}else if(angle <= -10){
    		turnRate = 15.0;
    	}else if(angle <= -1){
    		turnRate = 10.0;
    	}else if(angle <= -0.25){
    		turnRate = 5.0;
    	}
    	else{
    		turnRate = 0;
    	}
    	
    	double neededTurnAmount = Math.abs(turnRate / updateRate);
    	double turnAmount = Math.abs(lastAngle - (usesGyro ? getAngle() : Vision.xAngleToTarget));
    	if(turnAmount <= neededTurnAmount - neededTurnAmount*0.1){
    		turnToAngleTurnSpeed += turnToAngleIncrement;
    	}else if(turnAmount >= neededTurnAmount + neededTurnAmount*0.1){
    		turnToAngleTurnSpeed -= turnToAngleIncrement;
    	}
    	System.out.println("Update Rate: " + updateRate);
    	System.out.println("Turn Amount: " + turnAmount);
    	System.out.println("Needed turn amount: " + neededTurnAmount);
    	System.out.println("Angle: " + getAngle());
    	System.out.println("Magnetic Disturbance: " + isMagneticDisturbance());
    	System.out.println("Turn Speed: " + turnToAngleTurnSpeed);
    	
    	arcadeDrive(0, (turnRate < 0) ? -turnToAngleTurnSpeed : turnToAngleTurnSpeed, false);
    	lastAngle = usesGyro ? getAngle() : Vision.xAngleToTarget;
    	lastTime = currentTime;
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
    	SmartDashboard.putNumber("gyro",imu.getYaw());
    	return imu.getYaw();
    }
    
    public void setHWheelDeployed(){
    	hWheelSolenoid.set(true);
    }
    
    public void setHWheelRetracted(){
    	hWheelSolenoid.set(false);
    }
    
    public boolean getHWheelSolenoid(){
    	return hWheelSolenoid.get();
    }
    
    public void setFrontOmnisDeployed(){
    	frontOmnis.set(true);
    }
    
    public void setFrontOmnisRetracted(){
    	frontOmnis.set(false);
    }
    
    public void setBackOmnisDeployed(){
    	backOmnis.set(true);
    }
    
    public void setBackOmnisRetracted(){
    	backOmnis.set(false);
    }
    
    public boolean isFrontOmnisDeployed(){
    	return frontOmnis.get();
    }
    
    public boolean isBackOmnisDeployed(){
    	return backOmnis.get();
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