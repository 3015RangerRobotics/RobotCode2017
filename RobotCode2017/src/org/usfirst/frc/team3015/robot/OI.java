package org.usfirst.frc.team3015.robot;

import edu.wpi.first.wpilibj.buttons.Button;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

import org.usfirst.frc.team3015.robot.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	Joystick driver = new Joystick(0);
	Button driverA1 = new JoystickButton(driver, 1);
	Joystick coDriver = new Joystick(1);
	
	public OI() {
//		driverA1.whenPressed(new DriveStraightForTime(.5,10));
	}
	
	public double getDriverLeftX(){
		return driver.getRawAxis(0);
	}
	
	public double getDriverLeftY(){
		return driver.getRawAxis(1) * -1;
	}
}
