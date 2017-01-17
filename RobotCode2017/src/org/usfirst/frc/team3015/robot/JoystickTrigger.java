package org.usfirst.frc.team3015.robot;
//import org.usfirst.frc.team3015.

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

public class JoystickTrigger extends Button {
	private Joystick m_joystick; 
	private int m_triggerAxis;
	private double m_triggerDistance;
	public JoystickTrigger (Joystick joystick,int triggerAxis, double triggerDistance) {
		m_joystick=joystick;
		m_triggerAxis=triggerAxis;
		m_triggerDistance=triggerDistance;
	}
	@Override
	public boolean get() {
		return (m_joystick.getRawAxis(m_triggerAxis)>=m_triggerDistance);
	}

}
