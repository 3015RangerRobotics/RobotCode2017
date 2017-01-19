package org.usfirst.frc.team3015.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

public class DPad extends Button{
	private int dPadDegree;
	private Joystick joystick;
	
	public enum Value {
		kDPadRight,
		kDPadUpRight,
		kDPadUp,
		kDPadUpLeft,
		kDPadLeft,
		kDPadDownLeft,
		kDPadDown,
		kDPadDownRight	
	}
	
	public DPad(Joystick joystick, Value value){
		this.joystick = joystick;
		switch (value){
			case kDPadRight:
				this.dPadDegree = 0;
				break;
			case kDPadUpRight:
				this.dPadDegree = 45;
				break;
			case kDPadUp:
				this.dPadDegree = 90;
				break;
			case kDPadUpLeft:
				this.dPadDegree = 135;
				break;
			case kDPadLeft:
				this.dPadDegree = 180;
				break;
			case kDPadDownLeft:
				this.dPadDegree = 225;
				break;
			case kDPadDown:
				this.dPadDegree = 270;
				break;
			case kDPadDownRight:
				this.dPadDegree = 315;
				break;
			default:
				throw new AssertionError("Illegal value: " + value);
		}
	}
	
	public DPad(Joystick joystick, int dPadDegree){
		this.dPadDegree = dPadDegree;
		this.joystick = joystick;
	}

	@Override
	public boolean get() {
		return (joystick.getPOV() == dPadDegree);
	}
}
