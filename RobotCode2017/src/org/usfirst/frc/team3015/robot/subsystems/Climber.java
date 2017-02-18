package org.usfirst.frc.team3015.robot.subsystems;


import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
		SmartDashboard.putBoolean("isClimbing", false);
		climbMotor.set(0);
	}
	
	/**
	 * Climb up 
	 */
	public void climbUp() {
		SmartDashboard.putBoolean("isClimbing", true);
		climbMotor.set(-1.0);
	}
	
	/**
	 * Gets the current
	 */
	public double getCurrent(){
    	return climbMotor.getOutputCurrent();
    }
    

}
