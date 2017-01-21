package org.usfirst.frc.team3015.robot;
//import org.usfirst.frc.team3015.

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
/**
 * Takes trigger axis values and turns them into true/false buttons.
 * @author rangerrobots
 *
 */
public class JoystickTrigger extends Button {
	private Joystick m_joystick; 
	private int m_triggerAxis;
	private double m_triggerDistance;
	
	public enum Trigger {
		kLeftTrigger,
		kRightTrigger
	}
	/**
	 * Default construction with no trigger value
	 * @param joystick
	 * @param value
	 */
	public JoystickTrigger(Joystick joystick, Trigger value){
		this(joystick,value,0.5);
	}
	
	public JoystickTrigger(Joystick joystick, Trigger value, double triggerDistance){
		m_joystick=joystick;
		switch (value) {
			case kLeftTrigger:
				m_triggerAxis = 2;
				break;
			case kRightTrigger:
				m_triggerAxis = 3;
				break;
			default:
				throw new AssertionError("Illegal value: " + value);
		}
		if(triggerDistance > 1) {
			m_triggerDistance=1;
		} else if (triggerDistance <= 0) {
			m_triggerDistance=0.1;
		} else {
			m_triggerDistance=triggerDistance;
		}
	}

	public JoystickTrigger(Joystick joystick,int triggerAxis){
		this(joystick,triggerAxis,0.5);
	}
	
	public JoystickTrigger (Joystick joystick,int triggerAxis, double triggerDistance) {
		m_joystick=joystick;
		if (triggerAxis >= 4 || triggerAxis <= 1) {
			throw new AssertionError("Illegal value:" + triggerAxis + " (Must be between 2-3)");
		} else {
			m_triggerAxis=triggerAxis;
		}
		if(triggerDistance > 1) {
			m_triggerDistance=1;
		} else if (triggerDistance <= 0) {
			m_triggerDistance=0.1;
		} else {
			m_triggerDistance=triggerDistance;
		}
	}
	@Override
	public boolean get() {
		return (m_joystick.getRawAxis(m_triggerAxis)>=m_triggerDistance);
	}
	


}

