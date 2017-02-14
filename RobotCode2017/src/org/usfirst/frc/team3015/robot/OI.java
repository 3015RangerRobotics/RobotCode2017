package org.usfirst.frc.team3015.robot;

import org.usfirst.frc.team3015.robot.commands.*;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//Commands & Inputs for Driver
	Joystick driver = new Joystick(0);
	Button driverA1 = new JoystickButton(driver, 1);
	Button driverB2 = new JoystickButton(driver, 2);
	Button driverX3 = new JoystickButton(driver, 3);
	Button driverY4 = new JoystickButton(driver, 4);
	Button driverLB5 = new JoystickButton(driver, 5);
	Button driverRB6 = new JoystickButton(driver, 6);
	Button driverSEL7 = new JoystickButton(driver, 7);
	Button driverSTART8 = new JoystickButton(driver, 8);
	Button driverLS9 = new JoystickButton(driver, 9);
	Button driverRS10 = new JoystickButton(driver, 10);
	Button driverDLeft = new DPad(driver, DPad.Value.kDPadLeft);
	Button driverDUp = new DPad(driver, DPad.Value.kDPadUp);
	Button driverDDown = new DPad(driver, DPad.Value.kDPadDown);
	Button driverDRight = new DPad(driver, DPad.Value.kDPadRight);
	Button driverLTrig = new JoystickTrigger(driver,JoystickTrigger.Trigger.kLeftTrigger,.05);
	Button driverRTrig = new JoystickTrigger(driver,JoystickTrigger.Trigger.kRightTrigger,.05);
	//Commands & Inputs for coDriver
	Joystick coDriver = new Joystick(1);
	Button coDriverA1 = new JoystickButton(coDriver, 1);
	Button coDriverB2 = new JoystickButton(coDriver, 2);
	Button coDriverX3 = new JoystickButton(coDriver, 3);
	Button coDriverY4 = new JoystickButton(coDriver, 4);
	Button coDriverLB5 = new JoystickButton(coDriver, 5);
	Button coDriverRB6 = new JoystickButton(coDriver, 6);
	Button coDriverSEL7 = new JoystickButton(coDriver, 7);
	Button coDriverSTART8 = new JoystickButton(coDriver, 8);
	Button coDriverLS9 = new JoystickButton(coDriver, 9);
	Button coDriverRS10 = new JoystickButton(coDriver, 10);
	Button coDriverDLeft = new DPad(coDriver, DPad.Value.kDPadLeft);
	Button coDriverDUp = new DPad(coDriver, DPad.Value.kDPadUp);
	Button coDriverDDown = new DPad(coDriver, DPad.Value.kDPadDown);
	Button coDriverDRight = new DPad(coDriver, DPad.Value.kDPadRight);
	Button coDriverLTrig = new JoystickTrigger(coDriver,JoystickTrigger.Trigger.kLeftTrigger); 
	Button coDriverRTrig = new JoystickTrigger(coDriver,JoystickTrigger.Trigger.kRightTrigger);
	
	
	public OI() {
//		driverA1.whileHeld(new HarvesterHarvest());
//		driverB2.whileHeld(new HarvesterReverseHarvest());
//		driverA1.whenPressed(new StreamFrontCamera());
//		driverB2.whenPressed(new StreamBackCamera());
//		driverX3.whileHeld(new GearIntake());
//		driverY4.whileHeld(new GearOuttake());
//		driverLB5.whenPressed(new DriveTurnToTarget(true));
//		driverRB6.whileHeld(new DriveAntiDefense());
		driverLTrig.whenPressed(new DriveStrafeWithGamepad());
		driverRTrig.whenPressed(new DriveStrafeWithGamepad());
		
//		coDriverA1.whenPressed(new GearTiltDown());
//		coDriverB2.whenPressed(new HarvesterStop());
//		coDriverX3.whileHeld(new HarvesterHarvest());
//		coDriverY4.whenPressed(new GearTiltUp());
//		coDriverLB5.whenPressed(new GearClawClose());
//		coDriverRB6.whenPressed(new GearClawOpen());
//		coDriverDUp.whileHeld(new ClimberClimbUp());
//		coDriverDUp.whenReleased(new ClimberClimbStop());
	}
	
	public double getDriverLeftX(){
		return -driver.getRawAxis(0);
	}
	
	public double getDriverLeftY(){
		return -driver.getRawAxis(1);
	}
	
	public double getDriverSumTriggers(){
		return driver.getRawAxis(3)-driver.getRawAxis(2);
	}
	
	/**
	 * Rumble driver's right side of the joypad
	 * In the case of Xbox joypads, this is a softer rumble
	 * @param value Strength of rumble
	 */
	public void driverRightRumble(double value) {
		driver.setRumble(RumbleType.kRightRumble, value);
	}
	
	/**
	 * Rumble driver's left side of the joypad
	 * In the case of Xbox joypads, this is a stronger rumble
	 * @param value Strength of rumble
	 */
	public void driverLeftRumble(double value) {
		driver.setRumble(RumbleType.kLeftRumble, value);
	}
	
	/**
	 * Rumble codriver's right side of the joypad
	 * In the case of Xbox joypads, this is a softer rumble
	 * @param value Strength of rumble
	 */
	public void coDriverRightRumble(double value) {
		coDriver.setRumble(RumbleType.kRightRumble, value);
	}
	
	/**
	 * Rumble codriver's left side of the joypad
	 * In the case of Xbox joypads, this is a stronger rumble
	 * @param value Strength of rumble
	 */
	public void coDriverLeftRumble(double value) {
		coDriver.setRumble(RumbleType.kLeftRumble, value);
	}
	
	/**
	 * Stops rumble on right side of driver's joypad
	 */
	public void driverStopRightRumble(){
		driver.setRumble(RumbleType.kRightRumble, 0);
	}
	
	/**
	 * Stops rumble on left side of driver's joypad
	 */
	public void driverStopLeftRumble(){
		driver.setRumble(RumbleType.kLeftRumble, 0);
	}
	
	/**
	 * Stops rumble on right side of codriver's joypad
	 */
	public void coDriverStopRightRumble(){
		coDriver.setRumble(RumbleType.kRightRumble, 0);
	}
	
	/**
	 * Stops rumble on left side of codriver's joypad
	 */
	public void coDriverStopLeftRumble(){
		coDriver.setRumble(RumbleType.kLeftRumble, 0);
	}
}
