package org.usfirst.frc.team3015.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Harvester subsystem
 */
public class Harvester extends Subsystem {
	private VictorSP intake;
	 
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public void Harvester(){
//		intake = new VictorSP()
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
	public void end() {
		intake.set(0);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

