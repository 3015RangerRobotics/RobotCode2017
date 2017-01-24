package org.usfirst.frc.team3015.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Harvester subsystem
 */
public class Harvester extends Subsystem {
	private VictorSP intake;
	 
    /**
     * Constructs hardware
     */
	public void Harvester(){
//		intake = new VictorSP()
	}
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	
	/**
	 * Intake fuel
	 */
	public void harvest() {
		intake.set(0.8);
	}
	/**
	 * Reverses intake
	 */
	public void reverseHarvest() {
		intake.set(-0.8);
	}
	/**
	 * Stops intake
	 */
	public void stopHarvester() {
		intake.set(0);
	}
	
    
}

