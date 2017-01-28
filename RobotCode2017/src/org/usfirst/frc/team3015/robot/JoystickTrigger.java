package org.usfirst.frc.team3015.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * Takes a trigger axis value and turns it into a Button for Command.
 *
 */
public class JoystickTrigger extends Button {
	private Joystick m_joystick; 
	private int m_triggerAxis;
	private double m_triggerDistance;
	
	/**
	 * Select left or right triggers with names instead of 2 and 3.
	 */
	public enum Trigger {
		kLeftTrigger,
		kRightTrigger
	}
	
	/**
	 * Default construction, sets the trigger distance to .5
	 * 
	 * @param joystick 	The joystick you will be using.
	 * @param value 	Selection between the left and right trigger via enum
	 */
	public JoystickTrigger(Joystick joystick, Trigger value){
		this(joystick,value,0.5);
	}
	
	/**
	 * Construction with specified trigger distance and enumerated triggers
	 * 
	 * @param joystick 		  The joystick you will be using
	 * @param value Selection between the left and right trigger enums
	 * @param triggerDistance How far you want to push the trigger before it returns true
	 */
	public JoystickTrigger(Joystick joystick, Trigger value, double triggerDistance){
		m_joystick=joystick;
		//case structure to pull out Trigger axis from enum
		switch (value) {
			case kLeftTrigger:
				m_triggerAxis = 2;
				break;
			case kRightTrigger:
				m_triggerAxis = 3;
				break;
			default:
				//for the case that the given value is wrong
				throw new AssertionError("Illegal value: " + value);
		}
		
		//prevents unrealistic trigger distances as the value is between 0 and 1
		if(triggerDistance > 1) {
			m_triggerDistance=1;
		} else if (triggerDistance <= 0) {
			//make sure button is not always pressed
			m_triggerDistance=0.1;
		} else {
			m_triggerDistance=triggerDistance;
		}
	}
	
	/**
	 * Default version without the enum, trigger pull assumed to be .5
	 * 
	 * @param joystick 		The joystick you will be using
	 * @param triggerAxis   Selection between the left (2) and right (3) trigger
	 */
	public JoystickTrigger(Joystick joystick,int triggerAxis){
		this(joystick,triggerAxis,0.5);
	}
	
	/**
	 * Constructor without using the enum
	 * 
	 * @param joystick 			The joystick you will be using
	 * @param triggerAxis 		Selection between the left (2) and right (3) trigger
	 * @param triggerDistance 	How far you want to push the trigger before it returns true
	 */
	public JoystickTrigger (Joystick joystick,int triggerAxis, double triggerDistance) {
		m_joystick=joystick;
		if (triggerAxis >= 4 || triggerAxis <= 1) {
			throw new AssertionError("Illegal value:" + triggerAxis + " (Must be between 2-3)");
		} else {
			m_triggerAxis=triggerAxis;
		}
		
		//prevents unrealistic trigger distances as the value is between 0 and 1
		if(triggerDistance > 1) {
			m_triggerDistance=1;
		} else if (triggerDistance <= 0) {
			//make sure button is not always pressed
			m_triggerDistance=0.1;
		} else {
			m_triggerDistance=triggerDistance;
		}
	}
	
	/**
	 * Returns true if triggerAxis is greater than or equal to triggerDistance 
	 */
	@Override
	public boolean get() {
		return (m_joystick.getRawAxis(m_triggerAxis)>=m_triggerDistance);
	}
	


}

