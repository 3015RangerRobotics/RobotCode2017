package org.usfirst.frc.team3015.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Shooter extends Subsystem {
	private CANTalon shooterWheel;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public Shooter(){
		shooterWheel = new CANTalon(1);
//    	shooterWheel.configEncoderCodesPerRev(codesPerRev);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setPVBusMode(){
    	shooterWheel.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    }
    
    public void setVoltageMode(){
    	shooterWheel.changeControlMode(CANTalon.TalonControlMode.Voltage);
    }
    
    public void setSpeedMode(){
    	shooterWheel.changeControlMode(CANTalon.TalonControlMode.Speed);
    	shooterWheel.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    }
    public void enable(){
    	shooterWheel.enable();
    }
    public void disable(){
    	shooterWheel.disable();
    }
    
}

