package org.usfirst.frc.team3015.robot.subsystems;

import org.usfirst.frc.team3015.robot.commands.OurCompressorAuto;
import org.usfirst.frc.team3015.robot.commands.OurCompressorOn;

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
		compressor = new Compressor();
		pressureSensor = new AnalogPotentiometer(0, 250, -25);
	}
	
	/**
	 * Make the compressor run based on the state of the pressure switch.
	 */
	public void startCompressor() {
		compressor.start();
	}
	
	/**
	 * Stop the compressor from running
	 */
	public void stopCompressor() {
		compressor.stop();
	}
	
	/**
	 * Get the pressure sensor's reading
	 * @return Pressure reading in PSI
	 */
	public double getPressure() {
		return pressureSensor.get();
	}
	
    public void initDefaultCommand() {
    	// Set the default command for a subsystem here.
        setDefaultCommand(new OurCompressorAuto());
    }
}
