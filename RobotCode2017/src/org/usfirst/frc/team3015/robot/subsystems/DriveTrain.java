package org.usfirst.frc.team3015.robot.subsystems;

import org.usfirst.frc.team3015.robot.commands.DriveWithGamepad;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Code for our skid-steer drop-H drive train
 */




//DO NOT DELETE THIS COMMENT
//As of right now the transGearRedu and transGrearReduH are not accurate values!!
//DO NOT DELETE THIS COMMENT








public class DriveTrain extends Subsystem {
	private VictorSP leftMotors;
	private VictorSP rightMotors;
	private Encoder leftEncoder; 
	private Encoder rightEncoder;
	private Encoder hEncoder;
	private VictorSP hMotors;
	private DoubleSolenoid hWheelSolenoid;
	private DoubleSolenoid frontOmnis;
	private DoubleSolenoid backOmnis;
	private AHRS imu;
	private double turnToAngleTurnSpeed = 0;
	private double turnToAngleIncrement = 0.001;
	private double lastAngle = 0;
	private long lastTime = 0;
	private double transGearRedu = 30/44;
	private double transGearReduH = 30/44;
	private int clicksPerRotation = 20;
	private double wheelCirc = 4 * Math.PI;
	private double hDriveCPI = ((clicksPerRotation * transGearReduH)/(wheelCirc));
	private double driveCPI = ((clicksPerRotation * transGearRedu)/(wheelCirc));
	
	/**
	 * Constructs the drive train
	 */
	public DriveTrain() {
		leftMotors = new VictorSP(0);
		rightMotors = new VictorSP(1);
//		hMotors = new VictorSP(2);
//		hWheelSolenoid = new DoubleSolenoid(0, 1);
//		frontOmnis = new DoubleSolenoid(2, 3);
//		backOmnis = new DoubleSolenoid(4, 5);
//		leftEncoder = new Encoder(0,1);
//		rightEncoder = new Encoder(2,3);
//		hEncoder = new Encoder(4,5); 
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
    
    public double getLeftDriveEncoderInches(){
    	return leftEncoder.getDistance() / driveCPI;
    }
    public double getRightDriveEncoderInches(){
    	return rightEncoder.getDistance() / driveCPI;
    }
    public double getHDriveEncoderInches(){
    	return hEncoder.getDistance() / hDriveCPI;
    }
    public void zeroDriveEncoder(){
    	leftEncoder.reset();
    	rightEncoder.reset();
    }
    public void zeroHDriveEncoder(){
    	hEncoder.reset();
    }
    public double getAngle(){
    	SmartDashboard.putNumber("gyro",imu.getAngle());
    	return imu.getAngle();
    }
    public void zeroAngle(){
    	imu.zeroYaw();
    }
    
    public void setHWheelDeployed(){
    	hWheelSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    public void setHWheelRetracted(){
    	hWheelSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    public boolean isHWheelSolenoidDeployed(){
    	return hWheelSolenoid.get() == DoubleSolenoid.Value.kForward;
    }
    
    public void setFrontOmnisDeployed(){
    	frontOmnis.set(DoubleSolenoid.Value.kForward);
    }
    
    public void setFrontOmnisRetracted(){
    	frontOmnis.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void setBackOmnisDeployed(){
    	backOmnis.set(DoubleSolenoid.Value.kForward);
    }
    
    public void setBackOmnisRetracted(){
    	backOmnis.set(DoubleSolenoid.Value.kReverse);
    }
    
    public boolean isFrontOmnisDeployed(){
    	return frontOmnis.get() == DoubleSolenoid.Value.kForward;
    }
    
    public boolean isBackOmnisDeployed(){
    	return backOmnis.get() == DoubleSolenoid.Value.kForward;
    }
  
    /**
     * Single stick driving
     * @param rotateValue		
     * @param moveValue		
     * @param squaredInputs	
     */
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
    
    public double getLeftEncoder() {
		return leftEncoder.getDistance();
	}

	public double getRightEncoder() {
		return rightEncoder.getDistance();
	}

	public double gethEncoder() {
		return hEncoder.getDistance();
	}
}