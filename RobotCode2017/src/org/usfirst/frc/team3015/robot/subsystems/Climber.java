package org.usfirst.frc.team3015.robot.subsystems;


import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Climber Subsystem
 */
public class Climber extends Subsystem {
	private CANTalon climbMotor;
	public static boolean isClimbing = false;
	
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
		isClimbing = false;
	}
	
	/**
	 * Climb up 
	 */
	public void climbUp() {
		SmartDashboard.putBoolean("isClimbing", true);
		climbMotor.set(-1.0);
		isClimbing = true;
	}
	
	/**
	 * Gets the current
	 */
	public double getCurrent(){
    	return climbMotor.getOutputCurrent();
    }
    

}
