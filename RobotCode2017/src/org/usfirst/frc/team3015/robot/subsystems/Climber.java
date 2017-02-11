package org.usfirst.frc.team3015.robot.subsystems;


import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Climber Subsystem
 */
public class Climber extends Subsystem {
	private CANTalon climbMotor;

	
	/**
	 * Constructs Hardware
	 */
	public Climber() {
		climbMotor = new CANTalon(3);
	}
	
	@Override
	protected void initDefaultCommand() {
	}
	
	/**
	 * Stop Climbing
	 */
	public void stopClimb(){
		climbMotor.set(0);
	}
	
	/**
	 * Climb up 
	 */
	public void climbUp() {
		climbMotor.set(.8);
	}
	
	/**
	 * Gets the current
	 */
	public double getCurrent(){
    	return climbMotor.getOutputCurrent();
    }
    

}
