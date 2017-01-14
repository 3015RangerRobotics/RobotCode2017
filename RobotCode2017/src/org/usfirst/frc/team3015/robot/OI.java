package org.usfirst.frc.team3015.robot;

import edu.wpi.first.wpilibj.buttons.Button;

import org.usfirst.frc.team3015.robot.commands.ExampleCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

import org.usfirst.frc.team3015.robot.commands.DriveStraightForTime;
import org.usfirst.frc.team3015.robot.commands.ExampleCommand;

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
		driverA1.whenPressed(new DriveStraightForTime(.5,10));
	}
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	/** Return the joystick axis**/
	
	public double getDriverLeftX(){
		return driver.getRawAxis(0);
	}
	
	public double getDriverLeftY(){
		return driver.getRawAxis(1) * -1;
	}
}
