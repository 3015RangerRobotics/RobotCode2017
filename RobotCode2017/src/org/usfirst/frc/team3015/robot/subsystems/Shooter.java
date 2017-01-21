package org.usfirst.frc.team3015.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 * This is where methods for controlling this subsystem go
 */
public class Shooter extends Subsystem {
	private CANTalon shooterWheel;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
/**
 *  Constructing the shooter and declaring shooterWheel
 */
	public Shooter(){
		shooterWheel = new CANTalon(1);
//    	shooterWheel.configEncoderCodesPerRev(codesPerRev);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
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

