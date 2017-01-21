package org.usfirst.frc.team3015.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * Takes a DPad input direction and converts it to a Button for Commands
 * 
 * @author rangerrobots
 *
 */
public class DPad extends Button{
	private int dPadDegree;
	private Joystick joystick;
	
	/**
	 * DPad directions in words
	 * @author rangerrobots
	 *
	 */
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
	
	/**
	 * DPad constructor based on enums
	 * 
	 * @param joystick 		The gamepad we are referencing
	 * @param value 		uses enumeration names to select the 
	 * 						DPad direction
	 */
	public DPad(Joystick joystick, Value value){
		this.joystick = joystick;
		//pass the dpad direction in degrees, based on the enum
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
	
	/**
	 * Simple DPad constructor
	 * 
	 * @param joystick 		The gamepad we are referencing
	 * @param dPadDegree 	the direction on the DPad in degrees
	 */
	public DPad(Joystick joystick, int dPadDegree){
		this.dPadDegree = dPadDegree;
		this.joystick = joystick;
	}

	/**
	 * outputs true if joystick POV matches specified DPad degree
	 */
	@Override
	public boolean get() {
		return (joystick.getPOV() == dPadDegree);
	}
}
