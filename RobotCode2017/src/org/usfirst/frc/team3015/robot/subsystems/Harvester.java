package org.usfirst.frc.team3015.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Harvester subsystem
 */
public class Harvester extends Subsystem {
	private VictorSP intake;
	private VictorSP transport;
	
	private final double HARVEST_IN_SPEED = 0.8;
	private final double HARVEST_OUT_SPEED = -0.8;
	private final double TRANSPORT_IN_SPEED = 0.9;
	private final double TRANSPORT_OUT_SPEED = -0.9;
	
    /**
     * Constructs hardware
     */
	public Harvester(){
//		intake = new VictorSP(3);
//		transport = new VictorSP(4);
	}
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	
	/**
	 * Intake fuel with roller
	 */
	public void harvest() {
		SmartDashboard.putNumber("harvestingStatus", 1);
		intake.set(HARVEST_IN_SPEED);
	}
	
	/**
	 * Reverses intake
	 */
	public void reverseHarvest() {
    	SmartDashboard.putNumber("harvestingStatus", -1);
		intake.set(HARVEST_OUT_SPEED);
	}
	
	public void transport() {
		SmartDashboard.putNumber("transportStatus", 1);
		transport.set(TRANSPORT_IN_SPEED);	
	}
	
	public void reverseTransport() {
		SmartDashboard.putNumber("transportStatus", -1);
		transport.set(TRANSPORT_OUT_SPEED);
	}
	/**
	 * Stops intake
	 */
	public void stopHarvester() {
		SmartDashboard.putNumber("harvestingStatus", 0);
		intake.set(0);
	}
	
	public void stopTransport() {
		SmartDashboard.putNumber("transportStatus", 0);
		transport.set(0);
	}
}

