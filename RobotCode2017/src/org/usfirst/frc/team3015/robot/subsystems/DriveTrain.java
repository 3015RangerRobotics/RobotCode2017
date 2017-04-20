package org.usfirst.frc.team3015.robot.subsystems;

import org.usfirst.frc.team3015.robot.commands.DriveWithGamepad;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.ControllerPower;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Code for our skid-steer drop-H drive train
 */

public class DriveTrain extends Subsystem {
	public VictorSP leftMotors;
	public VictorSP rightMotors;
	public Encoder leftEncoder; 
	public Encoder rightEncoder;
	private Encoder hEncoder;
	private VictorSP hMotors;
	private DoubleSolenoid hWheelAndBack;
	private DoubleSolenoid frontOmnis;
	private AHRS imu;
	private double turnToAngleTurnSpeed = 0.1;
	private double turnToAngleIncrement = 0.001;
	private double lastAngle = 0;
	private long lastTime = 0;
	private double turnRate = 0;
	public boolean targetLock = false;
	private PIDController leftController;
	private PIDController rightController;
	
	/**
	 * Constructs the drive train
	 */
	public DriveTrain() {
		double dpp = 0.1029573493872;
		leftMotors = new VictorSP(0);
		rightMotors = new VictorSP(1);
		hMotors = new VictorSP(2);
		hWheelAndBack = new DoubleSolenoid(2, 3);//0,1
		frontOmnis = new DoubleSolenoid(0, 1);//2,3
		leftEncoder = new Encoder(0,1);
		leftEncoder.setDistancePerPulse(dpp);
		rightEncoder = new Encoder(2,3);
		rightEncoder.setDistancePerPulse(dpp);
		hEncoder = new Encoder(4,5); 
		imu = new AHRS(I2C.Port.kMXP);
		System.out.println(dpp);
	}
	/**
	 * Sets default command to DriveWithGamepad 
	 */
    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithGamepad());
    }
    
    public void turnToAngle(double angle, double moveSpeed, boolean usesGyro){
    	if (angle != lastAngle) {
			long currentTime = System.currentTimeMillis();
			if (currentTime - lastTime <= 0) {
				lastTime = currentTime;
				return;
			}
			double error = 0;
			if (usesGyro) {
				error = angle - getAngle();
			} else {
				error = angle - Vision.xAngleToTarget;
			}
			double updateRate = 1000 / (currentTime - lastTime);
			if (error >= 10) {
				double newTurnRate = -65.0;
				if(!usesGyro) newTurnRate = -30;
				if(newTurnRate != turnRate){
					turnRate = newTurnRate;
					turnToAngleTurnSpeed = 0.3;
				}
			} else if (error >= 1) {
				double newTurnRate = -25.0;
				if(!usesGyro) newTurnRate = -15;
				if(newTurnRate != turnRate){
					turnRate = newTurnRate;
					turnToAngleTurnSpeed = 0.25;
				}
			} else if (error >= 0.5) {
				double newTurnRate = -15.0;
				if(!usesGyro) newTurnRate = -10;
				if(newTurnRate != turnRate){
					turnRate = newTurnRate;
					turnToAngleTurnSpeed = 0.2;
				}
			} else if (error <= -10) {
				double newTurnRate = 65.0;
				if(!usesGyro) newTurnRate = 30;
				if(newTurnRate != turnRate){
					turnRate = newTurnRate;
					turnToAngleTurnSpeed = 0.3;
				}
			} else if (error <= -1) {
				double newTurnRate = 25.0;
				if(!usesGyro) newTurnRate = 15;
				if(newTurnRate != turnRate){
					turnRate = newTurnRate;
					turnToAngleTurnSpeed = 0.25;
				}
			} else if (error <= -0.5) {
				double newTurnRate = 15.0;
				if(!usesGyro) newTurnRate = 10;
				if(newTurnRate != turnRate){
					turnRate = newTurnRate;
					turnToAngleTurnSpeed = 0.2;
				}
			} else {
				double newTurnRate = 0;
				if(newTurnRate != turnRate){
					turnRate = newTurnRate;
					turnToAngleTurnSpeed = 0;
				}
			}
			if (!usesGyro) {
				if (error <= 0) {
					turnRate = 15.0;
				} else {
					turnRate = -15.0;
				}
			}
			if (usesGyro && (getAngle() + 180 <= angle || getAngle() - 180 >= angle)) {
				turnRate *= -1;
			}
			double neededTurnAmount = Math.abs(turnRate / updateRate);
			double turnAmount = Math.abs(lastAngle - (usesGyro ? getAngle() : Vision.xAngleToTarget));
			if (turnAmount <= neededTurnAmount - neededTurnAmount * 0.33) {
				turnToAngleTurnSpeed += turnToAngleIncrement * 5;
			} else if (turnAmount <= neededTurnAmount - neededTurnAmount * 0.15) {
				turnToAngleTurnSpeed += turnToAngleIncrement;
			} else if (turnAmount >= neededTurnAmount + neededTurnAmount * 0.33) {
				turnToAngleTurnSpeed -= turnToAngleIncrement * 5;
			} else if (turnAmount >= neededTurnAmount + neededTurnAmount * 0.15) {
				turnToAngleTurnSpeed -= turnToAngleIncrement;
			}
//			System.out.println("Update Rate: " + updateRate);
//			System.out.println("Turn Amount: " + turnAmount);
//			System.out.println("Needed turn amount: " + neededTurnAmount);
//			System.out.println("Angle: " + Vision.xAngleToTarget);
//    	    System.out.println("Magnetic Disturbance: " + isMagneticDisturbance());
//			System.out.println("Turn Speed: " + turnToAngleTurnSpeed);
			double turnSpeed = (turnToAngleTurnSpeed * 12.5) / ControllerPower.getInputVoltage();
			double currAngle = usesGyro ? getAngle() : Vision.xAngleToTarget;
			if (usesGyro) {
				arcadeDrive(moveSpeed, (angle < currAngle) ? turnSpeed : -turnSpeed, false);
			} else {
				arcadeDrive(moveSpeed, (angle < currAngle) ? -turnSpeed : turnSpeed, false);
			}
			lastAngle = usesGyro ? getAngle() : Vision.xAngleToTarget;
			lastTime = currentTime;
		}
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
    
    public void zeroDriveEncoder(){
    	leftEncoder.reset();
    	rightEncoder.reset();
    }
    
    public void zeroHDriveEncoder(){
    	hEncoder.reset();
    }
    
    public double getAngle(){
    	SmartDashboard.putNumber("gyro",imu.getYaw());
    	return imu.getYaw() + 180;
    }
    
    public void zeroAngle(){
    	imu.zeroYaw();
    }
    
    public void setHWheelAndBackDeployed(){
    	hWheelAndBack.set(DoubleSolenoid.Value.kForward);
    }
    
    public void setHWheelAndBackRetracted(){
    	hWheelAndBack.set(DoubleSolenoid.Value.kReverse);
    }
    
    public boolean isHWheelAndBackDeployed(){
    	return hWheelAndBack.get() == DoubleSolenoid.Value.kForward;
    }
    
    public void setFrontOmnisDeployed(){
    	frontOmnis.set(DoubleSolenoid.Value.kForward);
    }
    
    public void setFrontOmnisRetracted(){
    	frontOmnis.set(DoubleSolenoid.Value.kReverse);
    }
    
    public boolean isFrontOmnisDeployed(){
    	return frontOmnis.get() == DoubleSolenoid.Value.kForward;
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
    	SmartDashboard.putBoolean("isHDriving", true);
    	hMotors.set(speed);
    }
    
    public double getLeftEncoder() {
		return -leftEncoder.getDistance();
	}

	public double getRightEncoder() {
		return rightEncoder.getDistance();
	}

	public double gethEncoder() {
		return hEncoder.getDistance();
	}
}