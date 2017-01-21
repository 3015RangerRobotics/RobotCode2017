package org.usfirst.frc.team3015.robot.subsystems;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 * This is where methods for controlling this subsystem go
 */
public class ShooterWheel extends Subsystem {
	private CANTalon shooterWheel;
//	private VictorSP ballFeeder;
	
	private Encoder shooterWheelEncoder;
//	private Encoder ballFeederEncoder;
	private static boolean isPrimed = false;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
/**
 *  Constructing the shooter and declaring shooterWheel
 */
	public ShooterWheel(){
//		shooterWheel = new CANTalon(1);
//    	shooterWheel.configEncoderCodesPerRev(codesPerRev)
//		ballFeeder = new VictorSP(3);
//		shooterWheelEncoder = new Encoder(6, 7);
//		ballFeederEncoder = new Encoder(8, 9);
		
		
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setShooterWheel(double speed){
    	shooterWheel.set(speed);
    }
    
    public static boolean isPrimed(){
    	
    	return isPrimed;
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
    	shooterWheel.changeControlMode(CANTalon.TalonControlMode.Speed);
    	shooterWheel.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
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

