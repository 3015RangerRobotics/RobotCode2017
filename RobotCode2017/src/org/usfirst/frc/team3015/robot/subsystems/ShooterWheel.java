package org.usfirst.frc.team3015.robot.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This is where methods for controlling this subsystem go
 */
public class ShooterWheel extends Subsystem {
	private CANTalon shooterWheel;
	private static boolean isPrimed = false;
	private double targetSpeed = 21850;//25500
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
/**
 *  Constructing the shooter and declaring shooterWheel
 */
	public ShooterWheel(){
		shooterWheel = new CANTalon(1);
		shooterWheel.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		shooterWheel.enableBrakeMode(false);
//    	shooterWheel.configEncoderCodesPerRev(codesPerRev);
		
		
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    /**
     * Sets the speed of the shooter wheel
     * @param speed speed of the wheel
     */
    public void startShooterWheelVoltage(){
    	shooterWheel.set(6.5);//7.2
    }
    /**
     * Returns if the shooter wheel is primed
     * @return isPrimed
     */
    public boolean isPrimed(){
    	return isPrimed;
    }
    
    public void startShooterWheelSpeed(){
    	SmartDashboard.putBoolean("isShooting", true);
    	shooterWheel.set(targetSpeed);
//    	System.out.println(shooterWheel.getSpeed());
    }
    
    public void stopShooterWheel(){
    	SmartDashboard.putBoolean("isShooting", false);
    	shooterWheel.set(0);
    }
    
    public double getWheelEncoder(){
    	return shooterWheel.getEncVelocity();
    }
    
    public static void setIsPrimed(boolean myIsPrimed) {
    	isPrimed = myIsPrimed;
    }
    
    /**
     * Converts the talon to percent voltage mode
     */
    public void setPVBusMode(){
    	shooterWheel.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    }
    
    /**
     * Converts the talon to absolute voltage mode
     */
    public void setVoltageMode(){
    	shooterWheel.changeControlMode(CANTalon.TalonControlMode.Voltage);
    }
    
    /**
     * changes talon to speed mode 
     * sets feedback device to quad encoder
     */
    public void setSpeedMode(){
    	shooterWheel.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	shooterWheel.reverseSensor(true);
    	shooterWheel.configNominalOutputVoltage(+0.0f, -0.0f);
    	shooterWheel.configPeakOutputVoltage(+12.0f, -12.0f);
//    	shooterWheel.setNominalClosedLoopVoltage(12.0);
    	shooterWheel.setProfile(0);
    	shooterWheel.setF(0.0255);//0.025675
    	shooterWheel.setP(0.08);//0.03
    	shooterWheel.setI(0.0); 
    	shooterWheel.setD(7);
    	shooterWheel.enableBrakeMode(false);
    	shooterWheel.changeControlMode(CANTalon.TalonControlMode.Speed);
    }
    
    /**
     * enables closed loop shooterWheel for speed and voltage
     */
    public void enable(){
    	shooterWheel.enable();
    }
    
    /**
     * disables closed loop shooterWheel for speed and voltage
     */
    public void disable(){
    	shooterWheel.disable();
    }
    
}

