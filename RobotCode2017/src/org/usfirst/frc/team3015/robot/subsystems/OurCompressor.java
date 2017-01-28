package org.usfirst.frc.team3015.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Compressor subsystem
 * Prefixed with "Our" to prevent naming conflicts
 */
public class OurCompressor extends Subsystem {
	private Compressor compressor;
	private AnalogPotentiometer pressureSensor;
	
	public OurCompressor() {
//		compressor = new Compressor(1);
//		pressureSensor = new AnalogPotentiometer(2, 250, -25);
	}
	
	public void startCompressor() {
		compressor.start();
	}
	
	public void stopCompressor() {
		compressor.stop();
	}
	
    public void initDefaultCommand() {
    	// Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
