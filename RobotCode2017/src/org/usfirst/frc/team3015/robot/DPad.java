package org.usfirst.frc.team3015.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

public class DPad extends Button{
	private int dPadDegree;
	private Joystick joystick;
	
	public DPad(Joystick joystick, int dPadDegree){
		this.dPadDegree = dPadDegree;
		this.joystick = joystick;
	}

	@Override
	public boolean get() {
		return (joystick.getPOV() == dPadDegree);
	}

}
