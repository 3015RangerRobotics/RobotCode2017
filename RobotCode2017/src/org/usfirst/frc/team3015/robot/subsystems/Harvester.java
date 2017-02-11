package org.usfirst.frc.team3015.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

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
		intake = new VictorSP(3);
		transport = new VictorSP(4);
	}
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	
	/**
	 * Intake fuel with roller
	 */
	public void harvest() {
		intake.set(HARVEST_IN_SPEED);
	}
	
	/**
	 * Reverses intake
	 */
	public void reverseHarvest() {
		intake.set(HARVEST_OUT_SPEED);
	}
	
	public void transport() {
		transport.set(TRANSPORT_IN_SPEED);	
	}
	
	public void reverseTransport() {
		transport.set(TRANSPORT_OUT_SPEED);
	}
	/**
	 * Stops intake
	 */
	public void stopHarvester() {
		intake.set(0);
	}
	
	public void stopTransport() {
		transport.set(0);
	}
	
    
}

