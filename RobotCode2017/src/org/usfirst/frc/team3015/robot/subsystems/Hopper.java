package org.usfirst.frc.team3015.robot.subsystems;

import org.usfirst.frc.team3015.robot.commands.HopperStop;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.ControllerPower;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Hopper for feeding our wheel shooter 1 ball at a time.
 * Contains a anti-jamming system.
 */
public class Hopper extends Subsystem {

    private CANTalon rotation;
    private VictorSP ballFeeder;
//    private final double ROTATION_SPEED = -.5;
//    private final double REVERSE_ROTATION_SPEED = 0.35;
    private final double ROTATION_SPEED = -1*12.25;//0.7
    private final double REVERSE_ROTATION_SPEED = 0.35*12.25;
    private final double START_BALLFEEDER_SPEED = 1*12.25;//0.8
    private final double REVERSE_BALLFEEDER_SPEED = -1*12.25;
    
    /**
     * Constructs hardware
     */
    public Hopper() {
    	rotation = new CANTalon(2);
    	rotation.changeControlMode(TalonControlMode.Voltage);
		ballFeeder = new VictorSP(6);
    }
    
    public void initDefaultCommand() {
//       this.setDefaultCommand(new HopperStop());
    }
    
    public void rotate() {
    	SmartDashboard.putNumber("hopperStatus", -1);
    	rotation.set(ROTATION_SPEED);
    }
    
    public void reverse() {
    	rotation.set(REVERSE_ROTATION_SPEED);
    }
    
    public void stop() {
    	rotation.set(0);
    }
    
    public double getCurrent(){
    	return rotation.getOutputCurrent();
    }
    
    public double getSpeed(){
    	return rotation.getSpeed();
    }
    
    public void startFeeder(){
    	SmartDashboard.putNumber("feederStatus", 1);
    	ballFeeder.set(START_BALLFEEDER_SPEED/ControllerPower.getInputVoltage());
    }
    
    public void reverseFeeder(){
    	SmartDashboard.putNumber("feederStatus", -1);
    	ballFeeder.set(REVERSE_BALLFEEDER_SPEED/ControllerPower.getInputVoltage());
    }
    
    public void stopFeeder(){
    	SmartDashboard.putNumber("feederStatus", 0);
    	ballFeeder.set(0);
    }
}

